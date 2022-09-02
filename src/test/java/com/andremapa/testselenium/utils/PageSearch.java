package com.andremapa.testselenium.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageSearch {

    protected WebDriver webDriver;

    public PageSearch(ChromeDriver chromeDriver) {
        this.webDriver = chromeDriver;
    }

    public void closeDriver(){
        webDriver.quit();
    }
}
