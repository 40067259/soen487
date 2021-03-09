package com.concordia.a2.service;

import com.concordia.a2.exception.MissParameterException;
import com.concordia.a2.exception.RepException;
import com.concordia.a2.exception.noLogException;
import com.concordia.a2.pojo.LogEntry;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://localhost:8866/AlbumLog")
public interface logService {

    @WebMethod(operationName = "getAllLogEntry")
    List<LogEntry> getChangeLogs(@WebParam(name = "from") String from, @WebParam(name = "to") String to, @WebParam(name = "type") String type) throws MissParameterException, noLogException;
    @WebMethod(operationName = "clearLogs")
    String clearLogs() throws RepException;
}
