package com.concordia.a2.exception;

import javax.xml.ws.WebFault;

@WebFault(name = "MissParameterException", targetNamespace = "http://localhost:8866/AlbumLog")
public class MissParameterException extends Exception{
    public MissParameterException(String msg){super(msg);}
}
