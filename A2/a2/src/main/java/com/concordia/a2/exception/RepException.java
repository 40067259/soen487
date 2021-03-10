package com.concordia.a2.exception;

import com.concordia.a2.pojo.ServiceStatus;
import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM,
        customFaultCode = "{" + RepException.NAMESPACE_URI + "}custom_fault",
        faultStringOrReason = "@faultString")
public class RepException extends Exception{
    private ServiceStatus serviceStatus;

    public static final String NAMESPACE_URI = "http://www.soen487.com/exception";
    public RepException(String errorMsg){
        super(errorMsg);
    }
    public RepException(String errorMsg, ServiceStatus serviceStatus)
    {
        super(errorMsg);
        this.serviceStatus = serviceStatus;
    }
    public ServiceStatus getServiceStatus()
    {
            return serviceStatus;
    }

    public void setServiceStatus(ServiceStatus serviceStatus)
    {
        this.serviceStatus = serviceStatus;
    }

}

