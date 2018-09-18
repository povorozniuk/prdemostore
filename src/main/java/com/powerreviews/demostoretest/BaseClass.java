package com.powerreviews.demostoretest;

import com.powerreviews.demostoretest.util.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseClass {
    @Autowired
    UI ui;

    public BaseClass() {
    }

    public UI ui() {
        return ui;
    }
}
