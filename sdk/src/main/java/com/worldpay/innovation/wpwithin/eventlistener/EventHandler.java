package com.worldpay.innovation.wpwithin.eventlistener;

import com.worldpay.innovation.wpwithin.WPWithinGeneralException;
import com.worldpay.innovation.wpwithin.rpc.WPWithinCallback;
import com.worldpay.innovation.wpwithin.rpc.types.ServiceDeliveryToken;
import com.worldpay.innovation.wpwithin.rpc.types.TotalPriceResponse;
import com.worldpay.innovation.wpwithin.types.WWServiceDeliveryToken;
import com.worldpay.innovation.wpwithin.types.WWTotalPriceResponse;

import org.apache.thrift.TException;

class EventHandler implements WPWithinCallback.Iface {

	private EventListener listener;

	public EventHandler(EventListener listener) {

		this.listener = listener;
	}

	@Override
	public void beginServiceDelivery(int serviceId, int servicePriceID, ServiceDeliveryToken serviceDeliveryToken, int unitsToSupply)
			throws TException {

		try {

			WWServiceDeliveryToken sdt = new WWServiceDeliveryToken();
			sdt.setKey(serviceDeliveryToken.getKey());
			sdt.setIssued(serviceDeliveryToken.getIssued());
			sdt.setExpiry(serviceDeliveryToken.getExpiry());
			sdt.setRefundOnExpiry(serviceDeliveryToken.isRefundOnExpiry());
			sdt.setSignature(serviceDeliveryToken.getSignature());

			listener.onBeginServiceDelivery(serviceId, servicePriceID, sdt, unitsToSupply);

		} catch (WPWithinGeneralException e) {

			e.printStackTrace();

			throw new TException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public void endServiceDelivery(int serviceId, ServiceDeliveryToken serviceDeliveryToken, int unitsReceived)
			throws TException {

		try {

			WWServiceDeliveryToken sdt = new WWServiceDeliveryToken();
			sdt.setKey(serviceDeliveryToken.getKey());
			sdt.setIssued(serviceDeliveryToken.getIssued());
			sdt.setExpiry(serviceDeliveryToken.getExpiry());
			sdt.setRefundOnExpiry(serviceDeliveryToken.isRefundOnExpiry());
			sdt.setSignature(serviceDeliveryToken.getSignature());

			listener.onEndServiceDelivery(serviceId, sdt, unitsReceived);

		} catch (WPWithinGeneralException e) {

			e.printStackTrace();

			throw new TException(e.getMessage(), e.getCause());
		}
	}

	@Override
	public void makePaymentEvent(int totalPrice, String orderCurrency, String clientToken, String orderDescription,
			String uuid) throws TException {
		listener.onMakePaymentEvent(totalPrice, orderCurrency, clientToken, orderDescription, uuid);
	}

	@Override
	public void errorEvent(String msg) throws TException {
		listener.onErrorEvent(msg);
	}

	@Override
	public void serviceDiscoveryEvent(String remoteAddr) throws TException {
		listener.onServiceDiscoveryEvent(remoteAddr);
	}

	@Override
	public void servicePricesEvent(String remoteAddr, int serviceId) throws TException {
		listener.onServicePricesEvent(remoteAddr, serviceId);
	}

	@Override
	public void serviceTotalPriceEvent(String remoteAddr, int serviceId, TotalPriceResponse totalPriceResp) throws TException {
		try {

			WWTotalPriceResponse priceResponse = new WWTotalPriceResponse();
			priceResponse.setClientId(totalPriceResp.clientId);
			priceResponse.setCurrencyCode(totalPriceResp.getCurrencyCode());
			priceResponse.setMerchantClientKey(totalPriceResp.getMerchantClientKey());
			priceResponse.setPaymentReferenceId(totalPriceResp.getPaymentReferenceId());
			priceResponse.setPriceId(totalPriceResp.getPriceId());
			priceResponse.setServerId(totalPriceResp.getServerId());
			priceResponse.setTotalPrice(totalPriceResp.getTotalPrice());
			priceResponse.setUnitsToSupply(totalPriceResp.getUnitsToSupply());

			listener.onServiceTotalPriceEvent(remoteAddr, serviceId, priceResponse);

		} catch (WPWithinGeneralException wp) {

			wp.printStackTrace();

			throw new TException(wp.getMessage(), wp.getCause());
		}
	}
}
