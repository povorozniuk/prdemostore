package com.powerreviews.demostoretest.confmodel;

import org.springframework.stereotype.Component;

@Component
public class UrlConfigModel {

    private String demo_store_base_url;
    private String products;
    private String womens_fleet_jacket;

    public UrlConfigModel(String demo_store_base_url, String products, String womens_fleet_jacket) {
        this.demo_store_base_url = demo_store_base_url;
        this.products = products;
        this.womens_fleet_jacket = womens_fleet_jacket;
    }

    public String getDemo_store_base_url() {
        return demo_store_base_url;
    }

    public String getProducts() {
        return products;
    }

    public String getWomens_fleet_jacket() {
        return womens_fleet_jacket;
    }
}
