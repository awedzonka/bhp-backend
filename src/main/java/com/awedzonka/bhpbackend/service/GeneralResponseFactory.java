package com.awedzonka.bhpbackend.service;

import com.awedzonka.bhpbackend.service.generalresponse.GeneralResponse;
import com.awedzonka.bhpbackend.service.generalresponse.fields.ContentPage;
import com.awedzonka.bhpbackend.service.generalresponse.fields.RegistrationFormFields;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneralResponseFactory {

    public GeneralResponse getHomePageResponse() {
        return new GeneralResponse(
            new ContentPage("Witamy na stronie szkoleniowej", List.of("Zapraszamy do rejestracji konta."))
        );
    }

    public GeneralResponse getAboutUsResponse() {
        return new GeneralResponse(
            new ContentPage("Informacje o nas:", List.of(
                "Jan Kowalski",
                "ul. Dobra 38",
                "04-081 Warszawa",
                "telefon: 123456789",
                "e-mail: mail@mail.pl"))
        );
    }

    public GeneralResponse getRegistrationResponse() {
        return new GeneralResponse(
            new ContentPage("Rejestracja konta", null),
            new RegistrationFormFields());
    }
}
