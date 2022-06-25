package com.awedzonka.bhpbackend.service;

import com.awedzonka.bhpbackend.service.generalresponse.GeneralResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final GeneralResponseFactory generalResponseFactory;

    public GeneralResponse homePage() {
        return generalResponseFactory.getHomePageResponse();
    }

    public GeneralResponse aboutUs() {
        return generalResponseFactory.getAboutUsResponse();
    }

    public GeneralResponse registration() {
        return generalResponseFactory.getRegistrationResponse();
    }
}
