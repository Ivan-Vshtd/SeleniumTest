package veshtard.task2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import veshtard.Methods;

public class GmailPage extends Methods{
    private WebDriver driver;
    @FindBy(xpath = "//span/a")
    private WebElement incomingMsgBtn;
    @FindBy(xpath = "//div/a/span")
    private WebElement profile;
    @FindBy(xpath = "//div[3]/div[2]/a")
    private WebElement logoutBtn;

    public WebElement getIncomingMsgBtn() {
        return incomingMsgBtn;
    }

    public void goToIncmngMsg()
    {
        super.clicks(incomingMsgBtn);
    }
    public void profile()
    {
        super.clicks(profile);
    }
    public void logOut()
    {
        super.clicks(logoutBtn);
    }


    public GmailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
