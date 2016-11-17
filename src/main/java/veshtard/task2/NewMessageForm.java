package veshtard.task2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import veshtard.Methods;


public class NewMessageForm extends Methods{
    private WebDriver driver;
    @FindBy(xpath = "//textarea")
    private WebElement destination;
    @FindBy(xpath = "//div[3]/input")
    private WebElement subject;
    @FindBy(xpath = "//td[2]/div[2]/div")
    private WebElement msgBody;
    @FindBy(xpath = "//td/div/div/div[4]/table/tbody/tr/td/div/div[2]")
    private WebElement sendBtn;

    public void enterDestination(String destnString)
    {
        super.sendStringKey(destination,destnString);
    }

    public void enterSubject(String subjctString)
    {
        super.sendStringKey(subject,subjctString);
    }

    public void enterMsgBody(String msgString)
    {
        super.sendStringKey(msgBody,msgString);
    }

    public void sendMessage()
    {
        super.clicks(sendBtn);
    }

    public NewMessageForm(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
