package com.concordia.a2.exception;

import javax.xml.ws.WebFault;

@WebFault(name = "RepException", targetNamespace = "http://localhost:8866/AlbumLog")
public class RepException extends Exception{
    public RepException(String errorMsg){
        super(errorMsg);
    }
}
