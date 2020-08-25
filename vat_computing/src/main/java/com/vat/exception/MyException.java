package com.vat.exception;

public class MyException extends RuntimeException{

    private static final long serialVersionUID = 8092421651079757280L;

    public MyException(String message) {
	super(message);
    }
    
}
