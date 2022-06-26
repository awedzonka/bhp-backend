package com.awedzonka.bhpbackend.service.generalresponse.fields;

public class Customer {
    private Registration registration;

    public Customer() {
        this.registration = new Registration();
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }
}
