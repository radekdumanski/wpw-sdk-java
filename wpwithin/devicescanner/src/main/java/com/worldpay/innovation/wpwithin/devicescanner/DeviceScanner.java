/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worldpay.innovation.wpwithin.devicescanner;

import com.worldpay.innovation.wpwithin.WPWithinGeneralException;
import com.worldpay.innovation.wpwithin.WPWithinWrapper;
import com.worldpay.innovation.wpwithin.WPWithinWrapperImpl;
import com.worldpay.innovation.wpwithin.rpc.launcher.Listener;
import com.worldpay.innovation.wpwithin.types.WWDevice;
import com.worldpay.innovation.wpwithin.types.WWServiceMessage;
import java.util.Set;

/**
 *
 * @author worldpay
 */
public class DeviceScanner {
    WPWithinWrapper wpw;
    WWDevice wpwDevice;

    private static final Listener rpcAgentListener = (int exitCode, String stdOutput, String errOutput) -> {
        System.out.printf("RPC Agent process did exit.");
        System.out.printf("ExitCode: %d", exitCode);
        System.out.printf("stdout: \n%s\n", stdOutput);
        System.out.printf("stderr: \n%s\n", errOutput);
    };
        
    public void scanOnce() {
        Integer flagScanTimeout = 9000;
        System.out.println("Starting Device Scanner Written in Java.");
        
        String[] splitedPkgName = DeviceScanner.class.getPackage().getName().split("\\.");
        String rpcLogFile = "rpc-within-" + splitedPkgName[splitedPkgName.length-1] + ".log";
        if(wpw == null)
            wpw = new WPWithinWrapperImpl("127.0.0.1", 10001, true, rpcAgentListener, rpcLogFile);

        try {
            wpw.setup("kevingwp-java-scanner", "Kevin Gordons Java DEVICE SCANNER");
            wpwDevice = wpw.getDevice();
            if(wpwDevice != null) {
                  
                Set<WWServiceMessage> devices = wpw.deviceDiscovery(flagScanTimeout);
                if(devices.size() > 0) {
                    System.out.println("------------------------------------------------------------");
                    System.out.println("Found " + devices.size() + " devices\n");

                    for (WWServiceMessage svcMsg : devices) {
                        System.out.println("DeviceName:[ + svcMsg.getDeviceName() + ] [" + svcMsg.getServerId() + "] " + svcMsg.getDeviceDescription() + " @ " + svcMsg.getHostname() + ":" + svcMsg.getPortNumber() + "" + svcMsg.getUrlPrefix() + "\n");
                    }
                }
            }
    
        } catch(WPWithinGeneralException e) {
            System.out.println("There was a problem hopefully rpcagent was closed too");
        }
    }
}
