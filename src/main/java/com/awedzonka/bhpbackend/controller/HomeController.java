package com.awedzonka.bhpbackend.controller;

import com.awedzonka.bhpbackend.lib.GsonProvider;
import com.awedzonka.bhpbackend.lib.LoggerService;
import com.awedzonka.bhpbackend.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final GsonProvider gsonProvider;
    private final LoggerService loggerService;
    private final HomeService homeService;

    @GetMapping("/homePage")
    public ResponseEntity<String> homePage() {
        loggerService.info("/homePage");
        String message = gsonProvider.get().toJson(homeService.homePage());
        loggerService.info(message);
        return new ResponseEntity<>(message, HttpStatus.valueOf(200));
    }


    @GetMapping("/aboutUs")
    public ResponseEntity<String> aboutUs() {
        loggerService.info("/aboutUs");
        String message = gsonProvider.get().toJson(homeService.aboutUs());
        loggerService.info(message);

        return new ResponseEntity<>(message, HttpStatus.valueOf(200));
    }

    @GetMapping("/registration")
    public ResponseEntity<String> registration() {
        loggerService.info("/registration");
        String message = gsonProvider.get().toJson(homeService.registration());
        loggerService.info(message);
        return new ResponseEntity<>(message, HttpStatus.valueOf(200));
    }
}
