package com.awedzonka.bhpbackend.service.generalresponse;


import com.awedzonka.bhpbackend.service.generalresponse.fields.ContentPage;
import com.awedzonka.bhpbackend.service.generalresponse.fields.RegistrationFormFields;

public class GeneralResponse {

    private final ContentPage contentPage;
    private final RegistrationFormFields registrationFormFields;

    public GeneralResponse(ContentPage contentPage) {
        this.contentPage = contentPage;
        this.registrationFormFields = null;
    }

    public GeneralResponse(ContentPage contentPage, RegistrationFormFields registrationFormFields) {
        this.contentPage = contentPage;
        this.registrationFormFields = registrationFormFields;
    }
}
