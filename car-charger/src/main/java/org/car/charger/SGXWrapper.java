package org.car.charger;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;

public interface SGXWrapper extends Library {
	public static final SGXWrapper INSTANCE = (SGXWrapper) Native.loadLibrary("ProducerEnclaveBridge",
			SGXWrapper.class);

	long en_create_enclave(LongByReference[] token, LongByReference eid, IntByReference updated);

	long en_destroy_enclave(long eid);

	long en_get_hte_public_key(long eid, byte[] p, int len);

	long en_get_hte_private_key(long eid, byte[] b, int len);

	long en_get_merchant_client_key(long eid, byte[] b, int len);

	long en_get_merchant_service_key(long eid, byte[] b, int len);

}
