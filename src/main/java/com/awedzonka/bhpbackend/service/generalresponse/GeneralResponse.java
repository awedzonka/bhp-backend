package com.awedzonka.bhpbackend.service.generalresponse;


import com.awedzonka.bhpbackend.service.generalresponse.fields.ContentPage;
import com.awedzonka.bhpbackend.service.generalresponse.fields.Customer;
import com.awedzonka.bhpbackend.service.generalresponse.fields.LoginFormFields;
import com.awedzonka.bhpbackend.service.generalresponse.fields.RegistrationFormFields;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralResponse {

    private final Customer customer;
    private final ContentPage contentPage;
    private final RegistrationFormFields registrationFormFields;
    private final LoginFormFields loginFormFields;

    public GeneralResponse(ContentPage contentPage) {
        this.contentPage = contentPage;
        this.registrationFormFields = new RegistrationFormFields();
        this.loginFormFields = new LoginFormFields();
        this.customer = new Customer();
    }


}
