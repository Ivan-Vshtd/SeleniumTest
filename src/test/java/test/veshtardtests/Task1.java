package test.veshtardtests;

import org.apache.log4j.Logger;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import veshtard.Props;
import veshtard.task1.*;
import veshtard.webtestbase.WebDriverFactory;

public class Task1 {
    private static final Logger log = Logger.getLogger(Task1.class);
    private Props prop;
    private StringBuffer verificationErrors = new StringBuffer();

    public String getLinkString() {
        return prop.getLinkString();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        WebDriverFactory.startBrowser();
        prop = new Props();
        WebDriverFactory.getDriver().get(prop.getTutUrl());
    }

    @Test
    public void test1() throws Exception {

        log.info("Start test,Task1");
        try {

            MainPage mainPage = new MainPage(WebDriverFactory.getDriver());
            SearchPage searchPage = new SearchPage(WebDriverFactory.getDriver());

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
        WebDriverFactory.stopBrowser();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}