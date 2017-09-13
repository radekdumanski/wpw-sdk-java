package com.worldpay.innovation.wpwithin.producerex;


import java.util.HashMap;
import java.util.Map;

import com.worldpay.innovation.wpwithin.PSPConfig;
import com.worldpay.innovation.wpwithin.WPWithinGeneralException;
import com.worldpay.innovation.wpwithin.WPWithinWrapper;
import com.worldpay.innovation.wpwithin.WPWithinWrapperImpl;
import com.worldpay.innovation.wpwithin.rpc.launcher.Listener;
import com.worldpay.innovation.wpwithin.types.WWPrice;
import com.worldpay.innovation.wpwithin.types.WWPricePerUnit;
import com.worldpay.innovation.wpwithin.types.WWService;

public class Main {

    public static void main(String[] args) {

        try {

            System.out.println("WorldpayWithin Sample Producer...");

            // define log file name for the rpc agent,
            // e.g. "rpc-within-producerex.log";
            String[] splitedPkgName = Main.class.getPackage().getName().split("\\.");
            String rpcLogFile = "rpc-within-" + splitedPkgName[splitedPkgName.length-1] + ".log";
            
            WPWithinWrapper wpw = new WPWithinWrapperImpl("127.0.0.1", 10000, true, rpcAgentListener, rpcLogFile);

            wpw.setup("Producer Example", "Example WorldpayWithin producer");

            WWService svc = new WWService();
            svc.setName("Car charger");
            svc.setDescription("Can charge your hybrid / electric car");
            svc.setId(1);

            WWPrice ccPrice = new WWPrice();
            ccPrice.setId(1);
            ccPrice.setDescription("Kilowatt-hour");
            ccPrice.setUnitDescription("One kilowatt-hour");
            ccPrice.setUnitId(1);
            WWPricePerUnit ppu = new WWPricePerUnit();
            ppu.setAmount(25);
            ppu.setCurrencyCode("GBP");
            ccPrice.setPricePerUnit(ppu);

            HashMap<Integer, WWPrice> prices = new HashMap<>(1);
            prices.put(ccPrice.getId(), ccPrice);

            svc.setPrices(prices);

            wpw.addService(svc);

            Map<String, String> pspConfig = new HashMap<>();

            // Worldpay Online Payments
//            pspConfig.put(PSPConfig.PSP_NAME, PSPConfig.WORLDPAY_ONLINE_PAYMENTS);
//            pspConfig.put(PSPConfig.HTE_PUBLIC_KEY, "T_C_03eaa1d3-4642-4079-b030-b543ee04b5af");
//            pspConfig.put(PSPConfig.HTE_PRIVATE_KEY, "T_S_f50ecb46-ca82-44a7-9c40-421818af5996");
//            pspConfig.put(PSPConfig.API_ENDPOINT, "https://api.worldpay.com/v1");
//            pspConfig.put(PSPConfig.MERCHANT_CLIENT_KEY, "T_C_03eaa1d3-4642-4079-b030-b543ee04b5af");
//            pspConfig.put(PSPConfig.MERCHANT_SERVICE_KEY, "T_S_f50ecb46-ca82-44a7-9c40-421818af5996");

            // Worldpay Total US / SecureNet
            pspConfig.put(PSPConfig.PSP_NAME, PSPConfig.SECURE_NET);
            pspConfig.put(PSPConfig.API_ENDPOINT, "https://gwapi.demo.securenet.com/api");
            pspConfig.put(PSPConfig.HTE_PUBLIC_KEY, "8c0ce953-455d-4c12-8d14-ff20d565e485");
            pspConfig.put(PSPConfig.HTE_PRIVATE_KEY, "KZ9kWv2EPy7M");
            pspConfig.put(PSPConfig.DEVELOPER_ID, "12345678");
            pspConfig.put(PSPConfig.APP_VERSION, "0.1");
            pspConfig.put(PSPConfig.PUBLIC_KEY, "8c0ce953-455d-4c12-8d14-ff20d565e485");
            pspConfig.put(PSPConfig.SECURE_KEY, "KZ9kWv2EPy7M");
            pspConfig.put(PSPConfig.SECURE_NET_ID, "8008609");

            wpw.initProducer(pspConfig);

            wpw.startServiceBroadcast(0);

        } catch (WPWithinGeneralException e) {

            e.printStackTrace();
        }
    }

    private static final Listener rpcAgentListener = new Listener() {
        @Override
        public void onApplicationExit(int exitCode, String stdOutput, String errOutput) {

            System.out.printf("RPC Agent process did exit.");
            System.out.printf("ExitCode: %d", exitCode);
            System.out.printf("stdout: \n%s\n", stdOutput);
            System.out.printf("stderr: \n%s\n", errOutput);
        }
    };
}
