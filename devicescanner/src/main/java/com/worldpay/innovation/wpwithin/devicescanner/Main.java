/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worldpay.innovation.wpwithin.devicescanner;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    
    public static void main(String[] args) {
        
        DeviceScanner sc = new DeviceScanner();
        
        while(true) {
            sc.scanOnce();
            try {
                Thread.sleep(3);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
    }


}
