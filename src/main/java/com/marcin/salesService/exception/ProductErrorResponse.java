package com.marcin.salesService.exception;

import lombok.Data;

@Data
public class ProductErrorResponse {

    private int status;
    private String message;
    private long timeStamp;

}
