package com.awedzonka.bhpbackend.controller;

import com.awedzonka.bhpbackend.lib.GsonProvider;
import com.awedzonka.bhpbackend.lib.LoggerService;
import com.awedzonka.bhpbackend.model.User;
import com.awedzonka.bhpbackend.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class HomeController {

    private final GsonProvider gsonProvider;
    private final LoggerService loggerService;
    private final HomeService homeService;

    @GetMapping("/homePage")
    public ResponseEntity<String> homePage() {
        loggerService.info("/homePage");
        String message = gsonProvider.get().toJson(homeService.homePage());
        loggerService.info(message);
//         new Sleep().run(1500);
        return new ResponseEntity<>(message, buildHeaders(), HttpStatus.valueOf(200));
    }


    @GetMapping("/aboutUs")
    public ResponseEntity<String> aboutUs() {
        loggerService.info("/aboutUs");
        String message = gsonProvider.get().toJson(homeService.aboutUs());
        loggerService.info(message);

        return new ResponseEntity<>(message, buildHeaders(), HttpStatus.valueOf(200));
    }

    @GetMapping("/registration")
    public ResponseEntity<String> registrationGet() {
        loggerService.info("/registration");
        String message = gsonProvider.get().toJson(homeService.registrationGet());
        loggerService.info(message);
        return new ResponseEntity<>(message, buildHeaders(), HttpStatus.valueOf(200));
    }

//    @RequestMapping(path = "/registration", method = RequestMethod.OPTIONS)
//    public ResponseEntity<String> registrationOptions(@RequestBody String user) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Access-Control-Allow-Origin", "*");
//        httpHeaders.add("Access-Control-Request-Method", "POST");
//        httpHeaders.add("Access-Control-Allow-Methods", "POST");
//        httpHeaders.add("Access-Control-Allow-Headers", "X-CUSTOM");
//        httpHeaders.add("Access-Control-Request-Headers", "X-CUSTOM");
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//
//
//        return new ResponseEntity<>("", httpHeaders, HttpStatus.valueOf(200));
//    }


    @PostMapping(value = "/registration")
    public ResponseEntity<String> registrationPost(@RequestBody String user) {
        User userObject = gsonProvider.get().fromJson(user, User.class);
        loggerService.info("/registrationPost");
        String message = gsonProvider.get().toJson(homeService.registrationPost(userObject));
        loggerService.info(message);
        return new ResponseEntity<>(message, buildHeaders(), HttpStatus.valueOf(200));
    }

    @GetMapping(value = "/registration-success")
    public ResponseEntity<String> registrationSuccess() {
        loggerService.info("/registrationSuccess");
        String message = gsonProvider.get().toJson(homeService.registrationSuccess());
        loggerService.info(message);
        return new ResponseEntity<>(message, buildHeaders(), HttpStatus.valueOf(200));
    }

    @GetMapping("/login")
    public ResponseEntity<String> loginGet() {
        loggerService.info("/loginGet");
        String message = gsonProvider.get().toJson(homeService.loginGet());
        loggerService.info(message);
        return new ResponseEntity<>(message, buildHeaders(), HttpStatus.valueOf(200));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginPost(@RequestBody String user) {
        User userObject = gsonProvider.get().fromJson(user, User.class);
        loggerService.info("/loginPost");
        String message = gsonProvider.get().toJson(homeService.loginPost(userObject));
        loggerService.info(message);
        return new ResponseEntity<>(message, buildHeaders(), HttpStatus.valueOf(200));
    }

    @RequestMapping("/logout")
    public ResponseEntity<String> logout(Model model) {
        loggerService.info("/registrationSuccess");
        String message = gsonProvider.get().toJson(homeService.logout());
        loggerService.info(message);
        return new ResponseEntity<>(message, buildHeaders(), HttpStatus.valueOf(200));
    }

    @GetMapping("getSessionData")
    public ResponseEntity<String> getSessionData(@RequestHeader(value = "BHP_SID", defaultValue = "0") String BHP_SID) {
        loggerService.info("/getSessionData");
        String message = homeService.getSessionData(BHP_SID);
        if (null == message){
            message = "{}";
        }
        loggerService.info(message);
        return new ResponseEntity<>(message, buildHeaders(), HttpStatus.valueOf(200));
    }

    private HttpHeaders buildHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
//         httpHeaders.add("Access-Control-Allow-Origin", "*");
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
