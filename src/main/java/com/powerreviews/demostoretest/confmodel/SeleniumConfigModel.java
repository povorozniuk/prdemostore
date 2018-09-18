package com.powerreviews.demostoretest.confmodel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SeleniumConfigModel {

    private String browser;
    private String driverName;

    public SeleniumConfigModel(String browser, String driverName) {
        this.browser = browser;
        this.driverName = driverName;
    }

    public String getBrowser() {
        return browser;
    }

    public String getDriverName() {
        return driverName;
    }
}
