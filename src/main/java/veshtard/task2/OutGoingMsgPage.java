package veshtard.task2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import veshtard.Methods;


public class OutGoingMsgPage extends Methods {
    private WebDriver driver;
    @FindBy(xpath = "//span[2]/div")
    private  WebElement moreBtn;
    @FindBy(xpath = "//a[contains(text(),'Спам')]")
    private WebElement spamMsgBtn;

    public WebElement getSpamMsgBtn() {
        return spamMsgBtn;
    }

    public void goToSpamMsg() throws InterruptedException
    {
        super.clicks(moreBtn,spamMsgBtn);
        Thread.sleep(3000);

    }

    public OutGoingMsgPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
