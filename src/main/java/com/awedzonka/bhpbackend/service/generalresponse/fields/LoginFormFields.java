package com.awedzonka.bhpbackend.service.generalresponse.fields;

import java.util.ArrayList;
import java.util.List;

public class LoginFormFields {

    private final List<InputText> inputTextList;

    public LoginFormFields() {
        ArrayList<InputText> inputTextList = new ArrayList<>();
        inputTextList.add(new InputText("Podaj login", "login", "login", "text", ""));
        inputTextList.add(new InputText("Podaj hasło", "password", "password", "password", ""));
        this.inputTextList = inputTextList;
    }
}
