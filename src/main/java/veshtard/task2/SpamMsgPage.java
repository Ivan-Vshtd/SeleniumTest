package veshtard.task2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import veshtard.Methods;


public class SpamMsgPage extends Methods{
    private WebDriver driver;
    @FindBy(xpath = "//div[3]/div/div/div/div[4]/div/div/div")
    private WebElement spamMsgBtn;

    public SpamMsgPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
