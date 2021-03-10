package com.concordia.a2.exception;

import com.concordia.a2.pojo.ServiceStatus;

public class MissParameterException extends RuntimeException{

    private ServiceStatus serviceStatus;

    public MissParameterException(String errorMsg){
        super(errorMsg);
    }
    public MissParameterException(String errorMsg, ServiceStatus serviceStatus)
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
