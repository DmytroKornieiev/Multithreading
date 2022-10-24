package com.epam.task.cucumber.hooks;

import com.epam.task.cucumber.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.net.MalformedURLException;

public class DriverHooks {
    
    @Before
    public void setUpDriver() throws MalformedURLException {
        DriverManager.setUpDriver();
    }

    @After
    public void quitDriver(){
        DriverManager.quitDriver();
    }
}
