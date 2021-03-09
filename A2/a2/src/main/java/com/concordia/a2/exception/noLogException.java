package com.concordia.a2.exception;

import javax.xml.ws.WebFault;

@WebFault(name = "noLogException", targetNamespace = "http://localhost:8866/AlbumLog")
public class noLogException extends Exception{
    public noLogException(String msg){super(msg);}
}
