/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.worldpay.innovation.wpwithin;

/**
 *
 * @author worldpay
 */
public class WPWithinGeneralException extends RuntimeException {
	private String msg;
    public WPWithinGeneralException(String msg) {
    	this.msg = msg;
    };
    
    @Override
    public String getMessage() {
    	return this.msg;
    }
}
