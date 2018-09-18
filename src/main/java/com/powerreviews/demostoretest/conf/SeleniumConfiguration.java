package com.powerreviews.demostoretest.conf;

import com.powerreviews.demostoretest.confmodel.SeleniumConfigModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeleniumConfiguration {

    @Value("${selenium.browser}")
    private String browser;
    @Value("${selenium.driverName}")
    private String driverName;

    @Bean
    SeleniumConfigModel seleniumConfigModel(){
        return new SeleniumConfigModel(browser, driverName);
    }

}
