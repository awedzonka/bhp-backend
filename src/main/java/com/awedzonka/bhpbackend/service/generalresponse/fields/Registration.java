package com.awedzonka.bhpbackend.service.generalresponse.fields;

import java.util.HashMap;
import java.util.Map;


public class Registration {
    private int statusRegistration;
    private String generalErrorMessage;

    private Map<String, String> mapErrors = new HashMap<>();

    public Map<String, String> getMapErrors() {
        return mapErrors;
    }

    public void setMapErrors(Map<String, String> mapErrors) {
        this.mapErrors = mapErrors;
    }

    public int getStatusRegistration() {
        return statusRegistration;
    }

    public void setStatusRegistration(int statusRegistration) {
        this.statusRegistration = statusRegistration;
    }

    public String getGeneralErrorMessage() {
        return generalErrorMessage;
    }

    public void setGeneralErrorMessage(String generalErrorMessage) {
        this.generalErrorMessage = generalErrorMessage;
    }

    public void addError(String attribute, String message) {
        mapErrors.put(attribute, message);
    }

}
