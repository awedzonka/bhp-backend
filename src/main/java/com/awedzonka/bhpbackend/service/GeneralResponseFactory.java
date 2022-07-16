package com.awedzonka.bhpbackend.service;

import com.awedzonka.bhpbackend.lib.LoggerService;
import com.awedzonka.bhpbackend.lib.ValidatorProvider;
import com.awedzonka.bhpbackend.model.User;
import com.awedzonka.bhpbackend.repository.UserRepository;
import com.awedzonka.bhpbackend.service.generalresponse.GeneralResponse;
import com.awedzonka.bhpbackend.service.generalresponse.fields.ContentPage;
import com.awedzonka.bhpbackend.session.CookieSession;
import com.awedzonka.bhpbackend.session.SessionService;
import com.awedzonka.bhpbackend.validator.RegistrationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GeneralResponseFactory {

    private final LoggerService loggerService;
    private final UserRepository userRepository;
    private final UserService userService;
    private final ValidatorProvider validatorProvider;
    private final SessionService sessionService;

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
        generalResponse.getCustomer().getRegistration().setStatusRegistration(400);

        Set<ConstraintViolation<User>> violations = validatorProvider
            .getValidator()
            .validate(user, RegistrationValidator.class);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<User> constraintViolation : violations) {
                generalResponse.getCustomer().getRegistration().addError(String.valueOf(constraintViolation.getPropertyPath()), constraintViolation.getMessage());
            }
            return generalResponse;
        }

        String message = userService.checkLoginAndPassword(user);
        if (!"registrationSuccess".equals(message)) {
            generalResponse.getCustomer().getRegistration().setGeneralErrorMessage(message);


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

    public GeneralResponse getRegistrationSuccessResponse() {
        return new GeneralResponse(
            new ContentPage("Dziękujemy za rejestrację konta", List.of(
                "Teraz możesz zalogować się na Swoje konto"))
        );
    }

    public GeneralResponse getLoginGetResponse() {
        return new GeneralResponse(
            new ContentPage("Logowanie", null));
    }

    public GeneralResponse getLoginPostResponse(User user) {
        GeneralResponse generalResponse = new GeneralResponse(
            new ContentPage("Logowanie", null));
        generalResponse.getCustomer().getLogging().setStatusLogging(400);

        if (user.getLogin() == null || user.getPassword() == null) {
            generalResponse.getCustomer().getLogging().setGeneralErrorMessage("Wpisz login i hasło");

            return generalResponse;
        }

        String check = userService.checkLogin(user, generalResponse);
        if (!"loginSuccess".equals(check)) {
            return generalResponse;
        }

        // utworzyć sesje , dodać info, że zalogowany
        User userByLogin = userRepository.findUserByLogin(user.getLogin());
        loggerService.info(userByLogin.toString());

        CookieSession sessionBrowser = sessionService.createSession(userByLogin);
        generalResponse.getCustomer().setCookieSession(sessionBrowser);


//        model.addAttribute("loggedUser", true);
//        userService.sessionStart(login);
//
//        if (userSession.getUserInSession().isSuperUser()) {
//            model.addAttribute("admin", true);
//
//        }
//
//        model.addAttribute("firstName", userSession.getUserInSession().getFirstName());
//        model.addAttribute("registration", true);
//        model.addAttribute("message", "Zalogowałeś się. Zapoznaj się z materiałami szkoleniowymi, wykonaj test.");
//

        // zapisac w redisie dane usera

        generalResponse.getCustomer().getLogging().setStatusLogging(200);
        return generalResponse;

    }

    public GeneralResponse logout() {
        // skasowac sesje
        return new GeneralResponse(
            new ContentPage("Wylogowałeś się", List.of(
                "Dziekujemy za wizytę")));
    }
}

