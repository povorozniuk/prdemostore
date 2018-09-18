package com.powerreviews.demostoretest.util;

import com.powerreviews.demostoretest.confmodel.Config;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

@Component
public class UI {

    @Autowired
    Config config;

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    public UI() {
    }

    public void startInstance(){
        if (config.getSeleniumConfigModel().getBrowser().equalsIgnoreCase("chrome")){
            try {
                String driverPath = ResourceUtils.getFile("classpath:drivers/" + config.getSeleniumConfigModel().getDriverName()).getPath();
                System.out.println(driverPath);
                System.setProperty("webdriver.chrome.driver", driverPath);
                driver = new ChromeDriver();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            wait = new WebDriverWait(driver, 12000);
            js = (JavascriptExecutor) driver;
        }
    }

    public void killInstance(){
        if (!driver.equals(null)){
            driver.quit();
            driver = null;

        }
    }

    public WebDriver driver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }
}
