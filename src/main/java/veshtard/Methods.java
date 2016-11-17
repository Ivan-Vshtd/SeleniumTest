package veshtard;


import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Methods {
    private static final Logger log = Logger.getLogger(Methods.class);
    public void checkStringLine(WebElement element) {
        element.clear();
    }
    public void sendStringKey(WebElement str, WebElement btn,String stringKey) {
        log.debug("Enter string "+ stringKey);
        str.sendKeys(stringKey);
        btn.click();
    }
    public void sendStringKey(WebElement str,String stringKey) {
        log.debug("Enter string "+ stringKey);
        str.sendKeys(stringKey);
    }

    public void clicks(WebElement link)
    {
        link.click();
    }
    public void clicks(WebElement link,WebElement nLink)
    {
        link.click();
        nLink.click();
    }
    public void assertPageTitle(WebDriver driver, String expectedtitle)
    {
        log.debug("Enter expected string for this page: "+expectedtitle);
        try {
            Assert.assertEquals(expectedtitle,driver.getTitle());
        } catch(Exception e) {
            log.error(e.getMessage());
        }
    }
}
