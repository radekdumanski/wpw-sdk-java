package com.worldpay.innovation.wpwithin.thriftadapter;

import com.worldpay.innovation.wpwithin.WPWithinWrapperImpl;
import com.worldpay.innovation.wpwithin.rpc.types.ServiceDetails;
import com.worldpay.innovation.wpwithin.types.WWServiceDetails;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by conor on 13/08/2016.
 */
public class ServiceDetailsAdapter {

    public static Set<WWServiceDetails> convertServiceDetails(Set<ServiceDetails> svcDetails) {

        Set<WWServiceDetails> wwSvcDetails = new HashSet<WWServiceDetails>();

        if (svcDetails != null && svcDetails.size() > 0) {

            for (ServiceDetails svcDetail : svcDetails) {

                WWServiceDetails wwSvcDetail = new WWServiceDetails();
                wwSvcDetail.setServiceId(svcDetail.getServiceId());
                wwSvcDetail.setServiceName(svcDetail.getServiceName());
                wwSvcDetail.setServiceDescription(svcDetail.getServiceDescription());
                wwSvcDetail.setServiceType(svcDetail.getServiceType());
                wwSvcDetails.add(wwSvcDetail);
            }
        } else {
            Logger.getLogger(WPWithinWrapperImpl.class.getName()).log(Level.INFO, "ConvertServiceDetails: serviceDetail is null or empty");
        }
        return wwSvcDetails;

    }
}
