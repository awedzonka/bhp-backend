package com.awedzonka.bhpbackend.service;

import com.awedzonka.bhpbackend.model.User;
import com.awedzonka.bhpbackend.service.generalresponse.GeneralResponse;
import com.awedzonka.bhpbackend.service.generalresponse.fields.ContentPage;
import com.awedzonka.bhpbackend.validator.RegistrationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GeneralResponseFactory {

    private final UserService userService;
    private final Validator validator;

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

    public GeneralResponse getRegistrationGetResponse() {
        return new GeneralResponse(
            new ContentPage("Rejestracja konta", null));
    }

    public GeneralResponse getRegistrationPostResponse(User user) {
        GeneralResponse generalResponse = new GeneralResponse(
            new ContentPage("Rejestracja konta", null));

        String message = userService.checkLoginAndPassword(user);
        if (!"registrationSuccess".equals(message)) {
            generalResponse.getCustomer().getRegistration().setGeneralErrorMessage(message);
            generalResponse.getCustomer().getRegistration().setStatusRegistration(400);


            return generalResponse;
        }

        Set<ConstraintViolation<User>> violations = validator.validate(user, RegistrationValidator.class);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<User> constraintViolation : violations) {
                generalResponse.getCustomer().getRegistration().addError(String.valueOf(constraintViolation.getPropertyPath()), constraintViolation.getMessage());
            }
            return generalResponse;
        }


        String passToHash = user.getPassword(); // hash password
        user.setPasswordHash(passToHash);
        user.setLastSlide(1);

        userService.save(user);
        generalResponse.getCustomer().getRegistration().setGeneralErrorMessage("Dziękujemy za rejestrację. Teraz możesz się zalogować.");
        generalResponse.getCustomer().getRegistration().setStatusRegistration(200);

        return generalResponse;

    }
}

