package com.davi.restaurant_burguer.exceptions;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String message;
    private int status;
    private Date timeStamp;

    public ExceptionResponse(String message, int status, Date timeStamp) {
        this.message = message;
        this.status = status;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }
    public int getStatus() {
        return status;
    }
    public Date getTimeStamp() {
        return timeStamp;
    }
}
