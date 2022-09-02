package com.andremapa.testselenium.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class MercadoLivreSearch extends PageSearch{

    private static final String URL = "https://www.mercadolivre.com.br/";
    private static final String XPATH_COOKIE = "//button[@class=\"cookie-consent-banner-opt-out__action cookie-consent" +
            "-banner-opt-out__action--primary cookie-consent-banner-opt-out__action--key-accept\"]";
    private static final String XPATH_SEARCHBAR = "//input[@class=\"nav-search-input\"]";
    private static final String XPATH_SEARCHBUT = "//button[@class=\"nav-search-btn\"]";
    private static final String XPATH_NEWCATEGORY = "//a[@class=\"ui-search-link\"]/span[text()=\"Novo\"]/..";
    private static final String XPATH_HPCATEGORY = "//a[@class=\"ui-search-link\"]/span[text()=\"HP\"]/..";
    private static final String XPATH_RAMINBOX = "//input[@data-testid=\"Minimum-RAM_MEMORY\"]";
    private static final String XPATH_RAMMAXBOX = "//input[@data-testid=\"Maximum-RAM_MEMORY\"]";
    private static final String XPATH_RAMBOXBUT= "//button[@data-testid=\"submit-RAM_MEMORY\"]";
    private static final String XPATH_PRODUCT_LIST = "//li[@class=\"ui-search-layout__item\"]//a[@class=\"ui-search-link\"]";
    private static final String XPATH_PRODUCT_TITLE = "//h1[@class=\"ui-pdp-title\"]";
    private static final String XPATH_PRODUCT_PRICE = "//span[@class=\"andes-money-amount__fraction\"]";
    private static final String XPATH_PRODUCT_QUANTITY = "//span[@class=\"ui-pdp-buybox__quantity__selected\"]";
    private WebElement searchBar;

    public MercadoLivreSearch(ChromeDriver chromeDriver) {
        super(chromeDriver);
        chromeDriver.get(URL);
    }

    private void driverWait(){
        webDriver.manage().timeouts().implicitlyWait(Duration.ofMillis(1000));
    }

    public void closeCookie(){
        webDriver.findElement(By.xpath(XPATH_COOKIE)).click();
    }

    private WebElement findSearchBar(){
        if(searchBar == null){
             searchBar = webDriver.findElement(By.xpath(XPATH_SEARCHBAR));
        }
        return searchBar;
    }

    private WebElement findSearchBut(){
        return webDriver.findElement(By.xpath(XPATH_SEARCHBUT));
    }

    private WebElement findMinRamBox(){
        return webDriver.findElement(By.xpath(XPATH_RAMINBOX));
    }

    private WebElement findMaxRamBox(){
        return webDriver.findElement(By.xpath(XPATH_RAMMAXBOX));
    }

    private WebElement findRamBoxBut(){
        return webDriver.findElement(By.xpath(XPATH_RAMBOXBUT));
    }

    private List<WebElement> findProductList(){
        return webDriver.findElements(By.xpath(XPATH_PRODUCT_LIST));
    }

    public List<String> getAllProductTitle(){
        return findProductList().stream().map(x-> x.getAttribute("title")).toList();
    }

    public List<String> getProductInfo(){
        return List.of(
          webDriver.findElement(By.xpath(XPATH_PRODUCT_TITLE)).getText(),
          webDriver.findElement(By.xpath(XPATH_PRODUCT_PRICE)).getText(),
          webDriver.findElement(By.xpath(XPATH_PRODUCT_QUANTITY)).getText()
        );
    }

    public void clickOnNewCategory(){
         webDriver.findElement(By.xpath(XPATH_NEWCATEGORY)).click();
         driverWait();
    }

    public void clickOnHpCategory(){
        webDriver.findElement(By.xpath(XPATH_HPCATEGORY)).click();
        driverWait();
    }

    public void clickOnFirstProduct(){
        findProductList().get(0).click();
        driverWait();
    }

    public void search(String text){
        findSearchBar().sendKeys(text);
        findSearchBut().click();
        driverWait();
    }

    public void searchRamMemorie(String minRam, String maxRam){
        findMinRamBox().sendKeys(minRam);
        findMaxRamBox().sendKeys(maxRam);
        findRamBoxBut().click();
        driverWait();
    }
}
