package test.veshtardtests;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import veshtard.Props;
import veshtard.task2.*;

public class Task2 {
    private static final Logger log = Logger.getLogger(Task2.class);
    private WebDriver driver;
    private Props prop;

    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {

        prop = new Props();
    }

    @Test
    public void testFF() throws Exception {
        driver = new FirefoxDriver();
        log.info("Start testFF,Task2");
        doIt();
    }
    @Test
    public void testChrome() throws Exception {
        System.setProperty(prop.getPropDriver(), prop.getDriverPath());
        driver = new ChromeDriver();
        log.info("Start testChrome,Task2");
        doIt();
    }

    private void doIt() throws Exception
    {
        try {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get(prop.getGoogleUrl());

            LoginPage loginPage = new LoginPage(driver);
            PasswdPage passwdPage = new PasswdPage(driver);
            GmailPage gmailPage = new GmailPage(driver);
            IncomingMsgPage incomingMsgPage = new IncomingMsgPage(driver);
            OutGoingMsgPage outGoingMsgPage = new OutGoingMsgPage(driver);
            SpamMsgPage spamMsgPage = new SpamMsgPage(driver);
            NewMessageForm newMessageForm = new NewMessageForm(driver);

            log.info("Login");
            loginPage.logIn(prop.getLogin());
            log.info("Enter password");
            passwdPage.enterPasswd(prop.getPassword());
            gmailPage.goToIncmngMsg();
            log.info("page check, incoming");
            incomingMsgPage.assertPageTitle(driver,String.format(prop.getMsg_assert(),gmailPage.getIncomingMsgBtn().getAttribute(prop.getAttribute()),prop.getLogin()));
            incomingMsgPage.goToOutMsg();
            log.info("page check, outgoing");
            outGoingMsgPage.assertPageTitle(driver,String.format(prop.getMsg_assert(),incomingMsgPage.getOutgoingMsgBtn().getAttribute(prop.getAttribute()),prop.getLogin()));
            outGoingMsgPage.goToSpamMsg();
            log.info("page check, spam");
            spamMsgPage.assertPageTitle(driver,String.format(prop.getMsg_assert(),outGoingMsgPage.getSpamMsgBtn().getAttribute(prop.getAttribute()),prop.getLogin()));
            driver.navigate().back();
            driver.navigate().back();
            log.info("Search");
            incomingMsgPage.checksearchLine();
            incomingMsgPage.search(prop.getSearchString());
            log.info("Comparing the number of messages");
            incomingMsgPage.assertExpectedCount(prop.getExpcount_assert(),incomingMsgPage.countMessages());
            log.info("Getting the message list");
            incomingMsgPage.getListOfMessages();
            log.info("Send a mail");
            incomingMsgPage.createNewMsg();
            newMessageForm.enterDestination(prop.getMail_destination());
            newMessageForm.enterSubject(prop.getMail_subject());
            newMessageForm.enterMsgBody(prop.getMail_textbody());
            newMessageForm.sendMessage();
            gmailPage.profile();
            log.info("Logout");
            gmailPage.logOut();
            log.info("End test,Task2");
        } catch (InterruptedException e) {
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
