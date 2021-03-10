package com.concordia.a2.service;

import com.concordia.a2.exception.MissParameterException;
import com.concordia.a2.exception.RepException;
import com.concordia.a2.exception.noLogException;
import com.concordia.a2.mapper.AlbumMapper;
import com.concordia.a2.pojo.ServiceStatus;
import com.soen487.log_ws.LogEntry;
import com.soen487.log_ws.LogList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class logService {
    @Autowired
    AlbumMapper mapper;

    private LogList l = new LogList();

    public LogList getChangeLogs(String from, String to, String type) {

        l.getList().clear();
        for(LogEntry entry: mapper.getLogEntry(from,to,type))
        {
            l.getList().add(entry);
        }
        return l;
        /*try
        {
            if (from.isEmpty()&&to.isEmpty()&&type.isEmpty())
                return mapper.getAllEntry();
            if (from.isEmpty()||to.isEmpty()||type.isEmpty())
                throw new MissParameterException("missing parameter error.");
            else
                return mapper.getLogEntry(from,to, type);
        }catch (NullPointerException e)
        {
            throw new noLogException("there is no log record yet.");
        }*/

    }

    public LogList getAllChangeLogs(){
        l.getList().clear();
        for(LogEntry entry: mapper.getAllEntry())
        {
            l.getList().add(entry);
        }
        return l;

    }

    public ServiceStatus clearLogs()
    {
        ServiceStatus status = new ServiceStatus();
        status.setCode("ERROR");
        status.setDescription("it is not supported yet.");
        return status;
    }

}
