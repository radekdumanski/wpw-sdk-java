/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.worldpay.innovation.wpwithin;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author worldpay
 */
public class WPWithinGeneralException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	private static final Logger logger = LoggerFactory.getLogger(WPWithinGeneralException.class.getName());
	
    public WPWithinGeneralException(String msg) {
    	logger.error(msg);
    	this.msg = msg;
    };
    public WPWithinGeneralException(String msg, TException te) {
    	logger.error(msg, te);
    	this.msg = msg;
	}
    
    @Override
    public String getMessage() {
    	return this.msg;
    }
}
