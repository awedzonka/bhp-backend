package com.awedzonka.bhpbackend.controller;

import com.awedzonka.bhpbackend.lib.GsonProvider;
import com.awedzonka.bhpbackend.lib.LoggerService;
import com.awedzonka.bhpbackend.service.GeneralResponse;
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

    @GetMapping("/homePage")
    public ResponseEntity<String> lp() {
        loggerService.info("homePage");
        String message = gsonProvider.get().toJson(new GeneralResponse("Wiadomosc testowa"));
        return new ResponseEntity<>(message, HttpStatus.valueOf(200));
    }


    @GetMapping("/home")
    public ResponseEntity<String> home() {
        return new ResponseEntity<>("Body", HttpStatus.valueOf(200));
    }
}
