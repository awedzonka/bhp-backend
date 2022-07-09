package com.awedzonka.bhpbackend.service.generalresponse.fields;

import java.util.HashMap;
import java.util.Map;

public class Logging {

    private int statusLogging;
    private String generalErrorMessage;
    private Map<String, String> mapErrors = new HashMap<>();

    public int getStatusLogging() {
        return statusLogging;
    }

    public void setStatusLogging(int statusLogging) {
        this.statusLogging = statusLogging;
    }

    public String getGeneralErrorMessage() {
        return generalErrorMessage;
    }

    public void setGeneralErrorMessage(String generalErrorMessage) {
        this.generalErrorMessage = generalErrorMessage;
    }

    public Map<String, String> getMapErrors() {
        return mapErrors;
    }

    public void setMapErrors(Map<String, String> mapErrors) {
        this.mapErrors = mapErrors;
    }

    public void addError(String attribute, String message) {
        mapErrors.put(attribute, message);
    }
}
