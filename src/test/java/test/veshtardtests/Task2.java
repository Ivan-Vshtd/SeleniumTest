package test.veshtardtests;

import org.apache.log4j.Logger;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import veshtard.Props;
import veshtard.task2.*;
import veshtard.webtestbase.WebDriverFactory;

public class Task2 {
    private static final Logger log = Logger.getLogger(Task2.class);
    private Props prop;

    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        WebDriverFactory.startBrowser();
        prop = new Props();
        WebDriverFactory.getDriver().get(prop.getGoogleUrl());
    }

    @Test
    public void test2() throws Exception {
        log.info("Start test,Task2");
        try {
            LoginPage loginPage = new LoginPage(WebDriverFactory.getDriver());
            PasswdPage passwdPage = new PasswdPage(WebDriverFactory.getDriver());
            GmailPage gmailPage = new GmailPage(WebDriverFactory.getDriver());
            IncomingMsgPage incomingMsgPage = new IncomingMsgPage(WebDriverFactory.getDriver());
            OutGoingMsgPage outGoingMsgPage = new OutGoingMsgPage(WebDriverFactory.getDriver());
            SpamMsgPage spamMsgPage = new SpamMsgPage(WebDriverFactory.getDriver());
            NewMessageForm newMessageForm = new NewMessageForm(WebDriverFactory.getDriver());

            log.info("Login");
            loginPage.logIn(prop.getLogin());
            log.info("Enter password");
            passwdPage.enterPasswd(prop.getPassword());
            gmailPage.goToIncmngMsg();
            log.info("page check, incoming");
            incomingMsgPage.assertPageTitle(WebDriverFactory.getDriver(),String.format(prop.getMsg_assert(),gmailPage.getIncomingMsgBtn().getAttribute(prop.getAttribute()),prop.getLogin()));
            incomingMsgPage.goToOutMsg();
            log.info("page check, outgoing");
            outGoingMsgPage.assertPageTitle(WebDriverFactory.getDriver(),String.format(prop.getMsg_assert(),incomingMsgPage.getOutgoingMsgBtn().getAttribute(prop.getAttribute()),prop.getLogin()));
            outGoingMsgPage.goToSpamMsg();
            log.info("page check, spam");
            spamMsgPage.assertPageTitle(WebDriverFactory.getDriver(),String.format(prop.getMsg_assert(),outGoingMsgPage.getSpamMsgBtn().getAttribute(prop.getAttribute()),prop.getLogin()));
            WebDriverFactory.getDriver().navigate().back();
            WebDriverFactory.getDriver().navigate().back();
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
        WebDriverFactory.stopBrowser();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}
