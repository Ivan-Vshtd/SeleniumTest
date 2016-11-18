package test.veshtardtests;

import org.apache.log4j.Logger;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import veshtard.Props;
import veshtard.task3.*;
import veshtard.webtestbase.WebDriverFactory;

public class Task3 {
    private static final Logger log = Logger.getLogger(Task3.class);
    private WebDriver driver;
    private Props prop;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        WebDriverFactory.startBrowser();
        prop = new Props();
        WebDriverFactory.getDriver().get(prop.getDeltaUrl());
    }

    @Test
    public void test() throws Exception {

        log.info("Start test,Task3");
        try {
            MainPage mainPage = new MainPage(WebDriverFactory.getDriver());
            TicketSelectionPage ticketSelectionPage = new TicketSelectionPage(WebDriverFactory.getDriver());
            PassangerInfoPage passangerInfoPage = new PassangerInfoPage(WebDriverFactory.getDriver());
            InfoPage infoPage = new InfoPage(WebDriverFactory.getDriver());
            TicketSubmitPage ticketSubmitPage = new TicketSubmitPage(WebDriverFactory.getDriver());

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
        WebDriverFactory.stopBrowser();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

}
