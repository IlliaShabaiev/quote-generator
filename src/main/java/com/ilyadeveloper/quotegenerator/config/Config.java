package com.ilyadeveloper.quotegenerator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class Config {

    @Value("${ws1.baseurl}")
    private String baseurl;

    @Value("${ws1.inspiration}")
    private String inspiration;

    @Value("${ws1.slash}")
    private String slash;

    public String getBaseurl() {
        return baseurl;
    }

    public String getInspiration() {
        return inspiration;
    }

    public String getSlash() {
        return slash;
    }
}
