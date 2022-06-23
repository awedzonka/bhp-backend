package com.awedzonka.bhpbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


    @GetMapping("/")
    public ResponseEntity<String> lp() {
        return new ResponseEntity<>("Body", HttpStatus.valueOf(200));
    }


    @GetMapping("/home")
    public ResponseEntity<String> home() {
        return new ResponseEntity<>("Body", HttpStatus.valueOf(200));
    }
}
