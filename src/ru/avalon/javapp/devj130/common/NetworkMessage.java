package ru.avalon.javapp.devj130.common;

import java.io.Serializable;
import java.util.Date;

public class NetworkMessage implements Serializable {
    private String message;
    private Date processingTime;
    private Date processingEndTime;
    private Date processingStartTime;

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

    public void startProcessing() {
        if (processingStartTime != null)
            throw new IllegalArgumentException("'processingStartTime' has already been set");
        processingStartTime = new Date();
    }

    public void endProcessing() {
        if (processingEndTime != null)
            throw new IllegalArgumentException("'processingEndTime' has already been set");
        if (processingStartTime == null)
            throw new IllegalArgumentException("'processingStartTime' has already been set yet");
        processingEndTime = new Date();
    }

    public String getMessage() {
        return message;
    }

    public Date getProcessingTime() {
        return processingTime;
    }

    public Date getProcessingEndTime() {
        return processingEndTime;
    }

    public Date getProcessingStartTime() {
        return processingStartTime;
    }

    public String getProcessingPeriod() {
        return "Processing since " + processingStartTime + " till " + processingEndTime + ".";
    }
}
