package com.awedzonka.bhpbackend.service;

import com.awedzonka.bhpbackend.service.generalresponse.GeneralResponse;
import com.awedzonka.bhpbackend.service.generalresponse.fields.ContentPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeneralResponseFactory {

    public GeneralResponse getHomePageResponse() {
        return new GeneralResponse(
            new ContentPage("Witamy na stronie szkoleniowej", "Zapraszamy do rejestracji konta.")
        );
    }
}
