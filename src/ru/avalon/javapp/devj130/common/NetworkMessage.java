package ru.avalon.javapp.devj130.common;

import java.io.Serializable;
import java.util.Date;

public class NetworkMessage implements Serializable {
    private String message;
    private Date processingTime;

    public NetworkMessage(String message) {
        if (message == null)
            throw new IllegalArgumentException("'message' can't be null");
        this.message = message;
    }

    public void markProcessingTime() {
        if (processingTime != null)
            throw new IllegalArgumentException("'processingTime' has already been set");
        processingTime = new Date();
    }

    public String getMessage() {
        return message;
    }

    public Date getProcessingTime() {
        return processingTime;
    }
}
