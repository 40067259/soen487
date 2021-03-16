package com.concordia.a2.controller;


import com.concordia.a2.exception.MissParameterException;
import com.concordia.a2.exception.RepException;
import com.concordia.a2.exception.noLogException;
import com.concordia.a2.pojo.ServiceStatus;
import com.concordia.a2.service.logService;
import com.soen487.log_ws.ClearLogRequest;
import com.soen487.log_ws.GetChangeLogsRequest;
import com.soen487.log_ws.GetChangeLogsResponse;
import com.soen487.log_ws.LogList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Endpoint
public class logEndPoint {

    private static final String NAMESPACE_URI = "http://www.soen487.com/log-ws";

    @Autowired
    private logService service;

    @Autowired
    public logEndPoint(logService service) {
        this.service = service;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getChangeLogsRequest")
    @ResponsePayload
    public GetChangeLogsResponse getChangeLogs(@RequestPayload GetChangeLogsRequest request) throws MissParameterException, noLogException {

        GetChangeLogsResponse response = new GetChangeLogsResponse();
        if(request.getFrom().equals("null")&&request.getTo().equals("null")&&request.getType().equals("null"))
        {
            response.setChangedLogs(service.getAllChangeLogs());
            return response;
        }

        if(request.getFrom().equals("null")||request.getTo().equals("null")||request.getType().equals("null"))
            throw new MissParameterException("missing parameter error.");

        response.setChangedLogs(service.getChangeLogs(request.getFrom(),request.getTo(),request.getType()));


        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "clearLogRequest")
    @ResponsePayload
    public ClearLogRequest ClearLogRequest() throws RepException {
        ServiceStatus serviceStatus = service.clearLogs();

        if (serviceStatus.getCode().equalsIgnoreCase("Error"))
         throw new RepException(serviceStatus.getDescription());
        ClearLogRequest response = new ClearLogRequest();
        response.setResult(serviceStatus.getDescription());
        return response;

    }

}
