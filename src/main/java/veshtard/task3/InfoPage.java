package veshtard.task3;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class InfoPage {
    private static final Logger log = Logger.getLogger(InfoPage.class);
    private WebDriver driver;

    public void assertElem()
   {
       try {
           Assert.assertTrue(isElementPresent(By.id("continue_button")));
       } catch (Exception e) {
           log.error(e.getMessage());
       }
   }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }



    public InfoPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
