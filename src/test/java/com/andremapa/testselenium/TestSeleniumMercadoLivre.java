package com.andremapa.testselenium;

import com.andremapa.testselenium.utils.MercadoLivreSearch;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TestSeleniumMercadoLivre {

    private MercadoLivreSearch mercadoLivreSearch;

    @BeforeAll
    static void setupAll(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions()
                .addArguments("--log-level=3").addArguments("--silent")
                .addArguments("--ignore-certificate-errors").addArguments("--disable-popup-blocking")
                .addArguments("--incognito");
        ChromeDriver driver = new ChromeDriver(options);
        mercadoLivreSearch = new MercadoLivreSearch(driver);
    }

    @AfterEach
    void tearDown() {
        mercadoLivreSearch.closeDriver();
    }

    @Test
    void test_MercadoLivre() {
        mercadoLivreSearch.closeCookie();
        mercadoLivreSearch.search("notebook");
        mercadoLivreSearch.clickOnNewCategory();
        mercadoLivreSearch.clickOnHpCategory();
        mercadoLivreSearch.searchRamMemorie("6", "16");
        List<String> allProductTitle = mercadoLivreSearch.getAllProductTitle();
        mercadoLivreSearch.clickOnFirstProduct();
        List<String> productInfo = mercadoLivreSearch.getProductInfo();

        System.out.println("=================================");
        System.out.println("==========SELENIUM INFO==========");
        System.out.println("=================================");
        System.out.println("Product list:");
        allProductTitle.forEach(System.out::println);
        System.out.println("=================================");
        System.out.println("First Product Info:");
        productInfo.forEach(System.out::println);
        System.out.println("=================================");

        assertTrue(true);
    }
}
