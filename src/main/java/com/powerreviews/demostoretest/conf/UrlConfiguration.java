package com.powerreviews.demostoretest.conf;

import com.powerreviews.demostoretest.confmodel.UrlConfigModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UrlConfiguration {

    @Value("${url.demo-store-base-url}")
    private String demo_store_base_url;
    @Value("${url.products}")
    private String products;
    @Value("${url.womens-fleet-jacket}")
    private String womens_fleet_jacket;

    @Bean
    UrlConfigModel urlConfigModel(){
        return new UrlConfigModel(demo_store_base_url, products, womens_fleet_jacket);
    }

}
