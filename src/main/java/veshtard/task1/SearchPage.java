package veshtard.task1;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import veshtard.Methods;

import java.util.List;


public class SearchPage extends Methods{
    private static final Logger log = Logger.getLogger(SearchPage.class);
    private WebDriver driver;
    @FindBy(xpath = "//h3")
    private List<WebElement> header3;

    public WebElement searchLink(String link) {
        log.debug("search compliance "+link);
        List<WebElement> discount = header3;
        int count = 0;
        WebElement temp = null;
        for (WebElement element : discount) {
            count++;

            if (link.equals(element.getText()))
                temp = element;
        }
        System.out.println("Total links: " + count);
        return temp;
    }

    @Override
    public void clicks(WebElement link) {
        try {
            super.clicks(link);
        } catch (Exception e) {
            log.error("No matches found! "+new org.openqa.selenium.WebDriverException());
        }
    }

    public SearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
