package com.concordia.a2.exception;

import com.concordia.a2.pojo.ServiceStatus;

public class noLogException extends RuntimeException{

    private ServiceStatus serviceStatus;
    public noLogException(String msg){super(msg);}
    public noLogException(String errorMsg, ServiceStatus serviceStatus)
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
