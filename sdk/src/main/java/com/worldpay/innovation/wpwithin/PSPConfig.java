package com.worldpay.innovation.wpwithin;

import java.util.HashMap;

/**
 * Configuration items to be set for PSP Configuration
 */
public class PSPConfig {
	//
	// private static String pspName = "worldpayonlinepayments";
	// private static String apiEndpoint = "https://api.worldpay.com/v1";
	// private String htePublicKey;
	// private String htePrivateKey;
	// private String merchantClientKey;
	// private String merchantServiceKey;
	// public static final String SECURE_NET = "securenet";
	// public static final String WORLDPAY_ONLINE_PAYMENTS =
	// "worldpayonlinepayments";
	//
	// public static final String PSP_NAME = "psp_name";
	// public static final String HTE_PUBLIC_KEY = "hte_public_key";
	// public static final String HTE_PRIVATE_KEY = "hte_private_key";
	// public static final String DEVELOPER_ID = "developer_id";
	// public static final String PUBLIC_KEY = "public_key";
	// public static final String SECURE_KEY = "secure_key";
	// public static final String SECURE_NET_ID = "secure_net_id";
	// public static final String APP_VERSION = "app_version";
	// public static final String HTTP_PROXY = "http_proxy";
	// public static final String MERCHANT_CLIENT_KEY = "merchant_client_id";
	// public static final String MERCHANT_SERVICE_KEY = "merchant_service_key";
	// public static final String API_ENDPOINT = "api_endpoint";
	public static HashMap<String, String> getPspConfig(String pspName, String apiEndpoint, String htePublicKey,
			String htePrivateKey, String merchantClientKey, String merchantServiceKey) {
		HashMap<String, String> pspConfig = new HashMap<>();
		pspConfig.put("psp_name", pspName);
		pspConfig.put("api_endpoint", apiEndpoint);
		pspConfig.put("hte_public_key", htePublicKey);
		pspConfig.put("hte_private_key", htePrivateKey);
		pspConfig.put("merchant_client_id", merchantClientKey);
		pspConfig.put("merchant_service_key", merchantServiceKey);
		return pspConfig;
	}

}