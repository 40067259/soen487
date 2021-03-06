package com.concordia.a2.service;

import com.concordia.a2.exception.MissParameterException;
import com.concordia.a2.exception.RepException;
import com.concordia.a2.mapper.AlbumMapper;
import com.concordia.a2.pojo.LogEntry;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;
import java.util.List;

@WebService(name= "logService", endpointInterface = "com.concordia.a2.service.logService")
public class logServiceImp implements logService {

    @Autowired
    AlbumMapper mapper;

    public List<LogEntry> getChangeLogs(String from, String to, String type) throws MissParameterException {
        if (from.isEmpty()&&to.isEmpty()&&type.isEmpty())
            return mapper.getAllEntry();
        if (from.isEmpty()||to.isEmpty()||type.isEmpty())
            throw new MissParameterException("missing parameter error.");

        return mapper.getLogEntry(from,to,type);
    }

    public String clearLogs() throws RepException
    {
        throw new RepException("the method is not yet supported");
    }

}