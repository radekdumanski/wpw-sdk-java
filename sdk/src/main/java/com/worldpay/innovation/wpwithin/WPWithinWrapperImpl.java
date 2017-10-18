/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worldpay.innovation.wpwithin;

import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

import com.worldpay.innovation.wpwithin.eventlistener.EventListener;
import com.worldpay.innovation.wpwithin.eventlistener.EventServer;
import com.worldpay.innovation.wpwithin.rpc.WPWithin;
import com.worldpay.innovation.wpwithin.rpc.launcher.Architecture;
import com.worldpay.innovation.wpwithin.rpc.launcher.Launcher;
import com.worldpay.innovation.wpwithin.rpc.launcher.Listener;
import com.worldpay.innovation.wpwithin.rpc.launcher.OS;
import com.worldpay.innovation.wpwithin.rpc.launcher.PlatformConfig;
import com.worldpay.innovation.wpwithin.rpc.types.ServiceDeliveryToken;
import com.worldpay.innovation.wpwithin.thriftadapter.DeviceAdapter;
import com.worldpay.innovation.wpwithin.thriftadapter.HCECardAdapter;
import com.worldpay.innovation.wpwithin.thriftadapter.PaymentResponseAdapter;
import com.worldpay.innovation.wpwithin.thriftadapter.PriceAdapter;
import com.worldpay.innovation.wpwithin.thriftadapter.ServiceAdapter;
import com.worldpay.innovation.wpwithin.thriftadapter.ServiceDeliveryTokenAdapter;
import com.worldpay.innovation.wpwithin.thriftadapter.ServiceDetailsAdapter;
import com.worldpay.innovation.wpwithin.thriftadapter.ServiceMessageAdapter;
import com.worldpay.innovation.wpwithin.thriftadapter.TotalPriceResponseAdapter;
import com.worldpay.innovation.wpwithin.types.WWDevice;
import com.worldpay.innovation.wpwithin.types.WWHCECard;
import com.worldpay.innovation.wpwithin.types.WWPaymentResponse;
import com.worldpay.innovation.wpwithin.types.WWPrice;
import com.worldpay.innovation.wpwithin.types.WWService;
import com.worldpay.innovation.wpwithin.types.WWServiceDeliveryToken;
import com.worldpay.innovation.wpwithin.types.WWServiceDetails;
import com.worldpay.innovation.wpwithin.types.WWServiceMessage;
import com.worldpay.innovation.wpwithin.types.WWTotalPriceResponse;

/**
 *
 * @author worldpay
 */
public class WPWithinWrapperImpl implements WPWithinWrapper {

	private static final Logger logger = LoggerFactory.getLogger(WPWithinWrapperImpl.class.getName());

	private String hostConfig;
	private Integer portConfig;
	private WPWithin.Client cachedClient;
	private EventServer eventServer;
	private Launcher launcher;

	public WPWithinWrapperImpl(String host, Integer port, boolean startRPCAgent, Listener launcherListener,
			String rpcAgentLogFile) {

		this(host, port, startRPCAgent, null, 0, launcherListener, rpcAgentLogFile);
	}

	public WPWithinWrapperImpl(String rpcHost, Integer rpcPort, boolean startRPCAgent, EventListener eventListener,
			int rpcCallbackPort, Listener launcherListener, String rpcAgentLogFile) {
		shutdownOnSigterm();
		this.hostConfig = rpcHost;
		this.portConfig = rpcPort;
		if (eventListener != null) {

			if (rpcCallbackPort <= 0 || rpcCallbackPort > 65535) {

				throw new WPWithinGeneralException("Callback port must be >0 and <65535");
			}

			eventServer = new EventServer();

			eventServer.start(eventListener, rpcCallbackPort);

			System.out.printf("Did setup and start event server on port: %d\n", rpcCallbackPort);
		} else {

			rpcCallbackPort = 0;
		}

		if (startRPCAgent) {

			startRPCAgent(rpcPort, rpcCallbackPort, launcherListener, rpcAgentLogFile);
		}

		setClientIfNotSet();
	}

	private void setClientIfNotSet() {
		if (this.cachedClient == null) {
			this.cachedClient = openRpcListener();
		}
	}

	private WPWithin.Client getClient() {
		setClientIfNotSet();
		return this.cachedClient;
	}

	private WPWithin.Client openRpcListener() {

		TTransport transport = new TSocket(hostConfig, portConfig);

		try {
			transport.open();
		} catch (TTransportException ex) {
			logger.error("Could not open transport socket");
			throw new WPWithinGeneralException("Could not open transport socket", ex);
		}

		TProtocol protocol = new TBinaryProtocol(transport);
		WPWithin.Client client = new WPWithin.Client(protocol);

		return client;
	}

	private void shutdownOnSigterm() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				if (launcher != null) {
					if (launcher.getProcessHandle().isAlive()) {
						stopRPCAgent();
					}
				}
			}
		});
	}

	@Override
	public void setup(String name, String description) throws WPWithinGeneralException {
		try {
			getClient().setup(name, description);
		} catch (TException ex) {
			logger.error("Failure to setup in the wrapper", ex);
			throw new WPWithinGeneralException("Failure to setup in the wrapper", ex);
		}
	}

	@Override
	public void addService(WWService theService) throws WPWithinGeneralException {

		logger.info("About to add service");
		try {
			getClient().addService(ServiceAdapter.convertWWService(theService));
		} catch (TException ex) {
			logger.error("Add service to producer failed with Rpc call to the SDK lower level");
			throw new WPWithinGeneralException("Add service to producer failed with Rpc call to the SDK lower level", ex);
		}
		logger.info("Should have successfully added service");

	}

	@Override
	public void removeService(WWService svc) throws WPWithinGeneralException {
		try {
			getClient().removeService(ServiceAdapter.convertWWService(svc));
		} catch (TException ex) {
			logger.error("Removal of service failed in the wrapper");
			throw new WPWithinGeneralException("Removal of service failed in the wrapper", ex);
		}
	}

	@Override
	public void initConsumer(String scheme, String hostname, Integer port, String urlPrefix, String serverId,
			WWHCECard hceCard, Map<String, String> pspConfig) throws WPWithinGeneralException {
		try {
			getClient().initConsumer(scheme, hostname, port, urlPrefix, serverId,
					HCECardAdapter.convertWWHCECard(hceCard), pspConfig);
		} catch (TException ex) {
			logger.error("Initiating the consumer failed in the wrapper");
			throw new WPWithinGeneralException("Initiating the consumer failed in the wrapper", ex);
		}
	}

	@Override
	public void initProducer(Map<String, String> pspConfig) throws WPWithinGeneralException {
		try {
			getClient().initProducer(pspConfig);
		} catch (TException ex) {
			logger.error("Initiating the producer failed in the wrapper");
			throw new WPWithinGeneralException("Initiating the producer failed in the wrapper", ex);
		}
	}

	@Override
	public WWDevice getDevice() throws WPWithinGeneralException {
		try {
			return DeviceAdapter.convertDevice(getClient().getDevice());
		} catch (TException ex) {
			logger.error("Get device in wrapper failed");
			throw new WPWithinGeneralException("Get device in wrapper failed", ex);
		}
	}

	@Override
	public void startServiceBroadcast(Integer timeoutMillis) throws WPWithinGeneralException {
		try {
			getClient().startServiceBroadcast(timeoutMillis);
		} catch (TException ex) {
			logger.error("Start service broadcast in wrapper failed");
			throw new WPWithinGeneralException("Start service broadcast in wrapper failed", ex);
		}
	}

	@Override
	public void stopServiceBroadcast() throws WPWithinGeneralException {
		try {
			getClient().stopServiceBroadcast();
		} catch (TException ex) {
			logger.error("Stop service broadcast failed");
			throw new WPWithinGeneralException("Stop service broadcast failed", ex);
		}
	}

	@Override
	public Set<WWServiceMessage> deviceDiscovery(Integer timeoutMillis) throws WPWithinGeneralException {
		try {
			return ServiceMessageAdapter.convertServiceMessages(getClient().deviceDiscovery(timeoutMillis));
		} catch (TException ex) {
			logger.error("Failed device discovery in wrapper");
			throw new WPWithinGeneralException("Failed device discovery in wrapper", ex);
		}
	}
	
	@Override
	public WWServiceMessage searchForDevice(Integer timeoutMillis, String deviceName) throws WPWithinGeneralException {
		try {
			return ServiceMessageAdapter.convertServiceMessage(getClient().searchForDevice(timeoutMillis, deviceName));
		} catch (TException ex) {
			logger.error("Failed search for device in wrapper");
			throw new WPWithinGeneralException("Failed search for device in wrapper", ex);
		}
	}

	@Override
	public Set<WWServiceDetails> requestServices() throws WPWithinGeneralException {

		try {
			return ServiceDetailsAdapter.convertServiceDetails(getClient().requestServices());
		} catch (TException ex) {
			logger.error("Request Services failed in wrapper");
			throw new WPWithinGeneralException("Request Services failed in wrapper", ex);
		}

	}

	@Override
	public Set<WWPrice> getServicePrices(Integer serviceId) throws WPWithinGeneralException {
		try {
			return PriceAdapter.convertServicePrices(getClient().getServicePrices(serviceId));
		} catch (TException ex) {
			logger.error("Get Service Prices failed in wrapper");
			throw new WPWithinGeneralException("Get Service Prices failed in wrapper", ex);
		}
	}

	@Override
	public WWTotalPriceResponse selectService(Integer serviceId, Integer numberOfUnits, Integer priceId)
			throws WPWithinGeneralException {
		try {
			return TotalPriceResponseAdapter
					.convertTotalPriceResponse(getClient().selectService(serviceId, numberOfUnits, priceId));
		} catch (TException ex) {
			logger.error("Select service failed in wrapper");
			throw new WPWithinGeneralException("Select service failed in wrapper", ex);
		}
	}

	@Override
	public WWPaymentResponse makePayment(WWTotalPriceResponse request) throws WPWithinGeneralException {

		try {
			return PaymentResponseAdapter.convertPaymentResponse(
					getClient().makePayment(TotalPriceResponseAdapter.convertWWTotalPriceResponse(request)));
		} catch (TException ex) {
			logger.error("Failed to make payment in the wrapper");
			throw new WPWithinGeneralException("Failed to make payment in the wrapper", ex);
		}
	}

	@Override
	public WWServiceDeliveryToken beginServiceDelivery(int serviceId, WWServiceDeliveryToken serviceDeliveryToken,
			Integer unitsToSupply) throws WPWithinGeneralException {
		try {
			ServiceDeliveryToken sdt = getClient().beginServiceDelivery(serviceId,
					ServiceDeliveryTokenAdapter.convertWWServiceDeliveryToken(serviceDeliveryToken), unitsToSupply);
			return ServiceDeliveryTokenAdapter.convertServiceDeliveryToken(sdt);
		} catch (TException ex) {
			logger.error("Failed to begin Service Delivery in the wrapper");
			throw new WPWithinGeneralException("Failed to begin Service Delivery in the wrapper", ex);
		}
	}

	@Override
	public WWServiceDeliveryToken endServiceDelivery(int serviceId, WWServiceDeliveryToken serviceDeliveryToken,
			Integer unitsReceived) throws WPWithinGeneralException {
		try {
			ServiceDeliveryToken sdt = getClient().endServiceDelivery(serviceId,
					ServiceDeliveryTokenAdapter.convertWWServiceDeliveryToken(serviceDeliveryToken), unitsReceived);
			return ServiceDeliveryTokenAdapter.convertServiceDeliveryToken(sdt);
		} catch (TException ex) {
			logger.error("Failed to end Service Delivery in the wrapper");
			throw new WPWithinGeneralException("Failed to end Service Delivery in the wrapper", ex);
		}
	}

	@Override
	public void stopRPCAgent() {
		try {
			getClient().CloseRPCAgent();
		} catch (TException te) {
			try {
				throw (te.getCause());
			} catch (SocketException se) {
				if (launcher != null) {
					if (launcher.getProcessHandle().isAlive()) {
						launcher.stopProcess();
						logger.info("RPC Agent Killed");
					}
				}
				logger.info("RPC Agent Closed");
			} catch (Throwable e) {
				logger.info("RPC Agent Killed");
				if (launcher != null) {
					launcher.stopProcess();
				}
			}
		}
	}

	private void startRPCAgent(int port, int callbackPort, Listener launcherListener, String flagLogfile) {

		String flagLogLevels = "debug,error,info,warn,fatal,panic";
		String flagCallbackPort = callbackPort > 0 ? "-callbackport=" + callbackPort : "";
		String binBase = System.getenv("WPW_HOME") == null ? "../iot-core-component/bin"
				: String.format("%s/bin", System.getenv("WPW_HOME"));

		launcher = new Launcher();

		Map<OS, PlatformConfig> launchConfig = new HashMap<>(3);

		PlatformConfig winConfig = new PlatformConfig();
		winConfig.setCommand(Architecture.IA32,
				String.format("%s/rpc-agent-windows-386 -port=%d -logfile=%s -loglevel=%s %s", binBase, port,
						flagLogfile, flagLogLevels, flagCallbackPort));
		winConfig.setCommand(Architecture.X86_64,
				String.format("%s/rpc-agent-windows-amd64 -port=%d -logfile=%s -loglevel=%s %s", binBase, port,
						flagLogfile, flagLogLevels, flagCallbackPort));
		launchConfig.put(OS.WINDOWS, winConfig);

		PlatformConfig linuxConfig = new PlatformConfig();
		linuxConfig.setCommand(Architecture.IA32,
				String.format("%s/rpc-agent-linux-386 -port=%d -logfile=%s -loglevel=%s %s", binBase, port, flagLogfile,
						flagLogLevels, flagCallbackPort));
		linuxConfig.setCommand(Architecture.X86_64,
				String.format("%s/rpc-agent-linux-amd64 -port=%d -logfile=%s -loglevel=%s %s", binBase, port,
						flagLogfile, flagLogLevels, flagCallbackPort));
		linuxConfig.setCommand(Architecture.ARM32,
				String.format("%s/rpc-agent-linux-arm32 -port=%d -logfile=%s -loglevel=%s %s", binBase, port,
						flagLogfile, flagLogLevels, flagCallbackPort));
		linuxConfig.setCommand(Architecture.ARM64,
				String.format("%s/rpc-agent-linux-arm64 -port=%d -logfile=%s -loglevel=%s %s", binBase, port,
						flagLogfile, flagLogLevels, flagCallbackPort));
		launchConfig.put(OS.LINUX, linuxConfig);

		PlatformConfig macConfig = new PlatformConfig();
		macConfig.setCommand(Architecture.IA32,
				String.format("%s/rpc-agent/rpc-agent-darwin-386 -port=%d -logfile=%s -loglevel=%s %s", binBase, port,
						flagLogfile, flagLogLevels, flagCallbackPort));
		macConfig.setCommand(Architecture.X86_64,
				String.format("%s/rpc-agent-darwin-amd64 -port=%d -logfile=%s -loglevel=%s %s", binBase, port,
						flagLogfile, flagLogLevels, flagCallbackPort));
		launchConfig.put(OS.MAC, macConfig);

		launcher.startProcess(launchConfig, launcherListener);
	}
}