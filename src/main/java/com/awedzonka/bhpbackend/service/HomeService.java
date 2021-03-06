package com.awedzonka.bhpbackend.service;

import com.awedzonka.bhpbackend.model.User;
import com.awedzonka.bhpbackend.redis.RedisBhpClient;
import com.awedzonka.bhpbackend.service.generalresponse.GeneralResponse;
import com.awedzonka.bhpbackend.session.SessionCustomerData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final GeneralResponseFactory generalResponseFactory;
    private final RedisBhpClient redisBhpClient;

    public GeneralResponse homePage() {
        return generalResponseFactory.getHomePageResponse();
    }

    public GeneralResponse aboutUs() {
        return generalResponseFactory.getAboutUsResponse();
    }

    public GeneralResponse registrationGet() {
        return generalResponseFactory.getRegistrationGetResponse();
    }

    public GeneralResponse registrationPost(User user) {
        return generalResponseFactory.getRegistrationPostResponse(user);
    }

    public GeneralResponse registrationSuccess() {
        return generalResponseFactory.getRegistrationSuccessResponse();
    }

    public GeneralResponse loginGet() {
        return generalResponseFactory.getLoginGetResponse();
    }

    public GeneralResponse loginPost(User user) {
        return generalResponseFactory.getLoginPostResponse(user);
    }

    public GeneralResponse logout() {
        return generalResponseFactory.logout();
    }

    public String getSessionData(String BHP_SID) {
        return redisBhpClient.getValue(BHP_SID);
    }
}
