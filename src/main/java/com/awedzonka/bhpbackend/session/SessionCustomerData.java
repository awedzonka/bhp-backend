package com.awedzonka.bhpbackend.session;

import com.awedzonka.bhpbackend.model.User;
import lombok.Data;


public class SessionCustomerData {
    private Integer questionNumber;
    private Integer numberOfQuestions;
    private Integer points;
    private Integer goodAnswers;
    private String firstName;
    private Boolean admin;

    public SessionCustomerData(User user) {
        this.firstName = user.getFirstName();
    }

    public SessionCustomerData() {
    }
}
