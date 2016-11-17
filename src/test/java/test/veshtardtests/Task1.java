package test.veshtardtests;


import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import veshtard.Props;
import veshtard.task1.*;

public class Task1 {
    private static final Logger log = Logger.getLogger(Task1.class);
    private WebDriver driver;
    private Props prop;
    private StringBuffer verificationErrors = new StringBuffer();

    public String getLinkString() {
        return prop.getLinkString();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        prop = new Props();
    }

    @Test
    public void testFF() throws Exception {
        driver = new FirefoxDriver();

        log.info("Start testFF,Task1");
        doIt();
    }

    @Test
    public void testChrome() throws Exception {
        System.setProperty(prop.getPropDriver(), prop.getDriverPath());
        driver = new ChromeDriver();
        log.info("Start testChrome,Task1");
        doIt();
    }

    private void doIt() throws Exception {
        try {
            driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
            driver.get(prop.getTutUrl());

            MainPage mainPage = new MainPage(driver);
            SearchPage searchPage = new SearchPage(driver);

            log.info("Search field definition");
            mainPage.checkSerchLine();
            mainPage.search(prop.getStringKey());
            log.info("search for matching strings");
            searchPage.clicks(searchPage.searchLink(prop.getLinkString()));
            log.info("End test,Task1");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}