package com.concordia.a2.exception;

public class DuplicatedIdException extends Exception{
      public DuplicatedIdException(){
          super("Your isrc has been existed,please try another one");
      }
}
