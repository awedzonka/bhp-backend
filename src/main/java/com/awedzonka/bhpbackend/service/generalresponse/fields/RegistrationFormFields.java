package com.awedzonka.bhpbackend.service.generalresponse.fields;

import java.util.ArrayList;
import java.util.List;

public class RegistrationFormFields {

    private final List<InputText> inputTextList;

    public RegistrationFormFields() {
        ArrayList<InputText> inputTextList = new ArrayList<>();
        inputTextList.add(new InputText("Podaj login", "login", "login", "text", ""));
        inputTextList.add(new InputText("Podaj hasło", "password", "password", "password", ""));
        inputTextList.add(new InputText("Powtórz hasło", "password2", "password2", "password", ""));
        inputTextList.add(new InputText("Imię", "firstName", "firstName", "text", ""));
        inputTextList.add(new InputText("Nazwisko", "lastName", "lastName", "text", ""));
        inputTextList.add(new InputText("Miasto", "city", "city", "text", ""));
        inputTextList.add(new InputText("Ulica", "street", "street", "text", ""));
        inputTextList.add(new InputText("Numer domu", "homeNumber", "homeNumber", "text", ""));
        inputTextList.add(new InputText("Email:", "email", "email", "text", ""));
        this.inputTextList = inputTextList;
    }
}
