package com.marcin.salesService.exception;

public class NotEnoughtProductInWarehouseException extends RuntimeException {

    public NotEnoughtProductInWarehouseException(String message){
        super(message);
    }

    public NotEnoughtProductInWarehouseException(String message, Throwable cause){
        super(message, cause);
    }

    public NotEnoughtProductInWarehouseException(Throwable cause){
        super(cause);
    }
}
