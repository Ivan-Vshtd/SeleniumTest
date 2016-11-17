package test.veshtardtests;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import veshtard.Props;
import veshtard.task3.*;

public class Task3 {
    private static final Logger log = Logger.getLogger(Task3.class);
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
        log.info("Start testFF,Task3");
        doIt();
    }
    @Test
    public void testChrome() throws Exception {
        System.setProperty(prop.getPropDriver(), prop.getDriverPath());
        driver = new ChromeDriver();
        log.info("Start testChrome,Task3");
        doIt();
    }
    private void doIt() throws Exception
    {
        try {
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.get(prop.getDeltaUrl());
            MainPage mainPage = new MainPage(driver);
            TicketSelectionPage ticketSelectionPage = new TicketSelectionPage(driver);
            PassangerInfoPage passangerInfoPage = new PassangerInfoPage(driver);
            InfoPage infoPage = new InfoPage(driver);
            TicketSubmitPage ticketSubmitPage = new TicketSubmitPage(driver);

            log.info("Enter flight information");
            mainPage.checkBookATrip();
            mainPage.checkRoundTrip();
            mainPage.setOirginCity(prop.getOriginCity());
            mainPage.setDestinationCity(prop.getDestinationCity());
            mainPage.setDepartureDate(prop.getDeprtureDate());
            mainPage.setReturnDate(prop.getReturnDate());
            mainPage.checkExactDays();
            mainPage.checkMoney();
            mainPage.findFlights();
            log.info("Select tickets");
            ticketSelectionPage.selectOriginTicket();
            ticketSelectionPage.selectReturnTicket();
            ticketSubmitPage.submitSelection();
            log.info("Enter passangers info");
            passangerInfoPage.selectPrrefix();
            passangerInfoPage.enterFirstName(prop.getFirstName());
            passangerInfoPage.enterMiddleName(prop.getMiddleName());
            passangerInfoPage.enterLastName(prop.getLastName());
            passangerInfoPage.selectGender();
            passangerInfoPage.selectMonth();
            passangerInfoPage.selectDay();
            passangerInfoPage.selectYear();
            log.info("Enter emergency info");
            passangerInfoPage.enterEmergencyFirstName(prop.getEmergencyFirstName());
            passangerInfoPage.enterEmergencyLastName(prop.getEmergencyLastName());
            passangerInfoPage.selectEmgCountry();
            passangerInfoPage.enterEmgPhoneNumber(prop.getEmergencyPhoneNumber());
            log.info("Enter passengers communication methods");
            passangerInfoPage.selectDevType();
            passangerInfoPage.selectCountry();
            passangerInfoPage.enterPhoneNumber(prop.getPhoneNumber());
            passangerInfoPage.enterEmail(prop.getEmail());
            passangerInfoPage.reEnterEmail(prop.getEmail());
            passangerInfoPage.submit();
            log.info("Check availability confirmation button");
            infoPage.assertElem();
            log.info("End test,Task3");
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
