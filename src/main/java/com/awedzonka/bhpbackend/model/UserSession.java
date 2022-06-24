package com.awedzonka.bhpbackend.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSession {

    private User userInSession;

    private boolean loggedUser;

    public User getUserInSession() {
        return userInSession;
    }

    public void setUserInSession(User userInSession) {
        this.userInSession = userInSession;
    }

    public boolean isLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(boolean loggedUser) {
        this.loggedUser = loggedUser;
    }
}
