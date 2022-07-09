package com.awedzonka.bhpbackend.lib;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class HashCreator {

    public String createSha256(String textToHash) {
        return DigestUtils.sha256Hex(textToHash);
    }

    public String createSha384(String textToHash) {
        return DigestUtils.sha384Hex(textToHash);
    }
}
