package com.powerreviews.demostoretest.confmodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Config {

    @Autowired
    SeleniumConfigModel seleniumConfigModel;

    @Autowired
    UrlConfigModel urlConfigModel;


    public SeleniumConfigModel getSeleniumConfigModel() {
        return seleniumConfigModel;
    }

    public UrlConfigModel getUrlConfigModel() {
        return urlConfigModel;
    }
}
