package com.awedzonka.bhpbackend.service.generalresponse.fields;

import com.awedzonka.bhpbackend.session.CookieSession;

public class Customer {
    private Registration registration;
    private Logging logging;

    private CookieSession cookieSession;

    public Customer() {
        this.registration = new Registration();
        this.logging = new Logging();
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public Logging getLogging() {
        return logging;
    }

    public void setLogging(Logging logging) {
        this.logging = logging;
    }

    public CookieSession getCookieSession() {
        return cookieSession;
    }

    public void setCookieSession(CookieSession cookieSession) {
        this.cookieSession = cookieSession;
    }
}
