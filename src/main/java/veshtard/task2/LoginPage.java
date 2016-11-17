package veshtard.task2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import veshtard.Methods;

public class LoginPage extends Methods{
    private WebDriver driver;
    @FindBy(id = "Email")
    private WebElement email;
    @FindBy(id = "next")
    private WebElement nextBtn;

    public void logIn(String login)
    {
        super.checkStringLine(email);
        super.sendStringKey(email,nextBtn,login);
    }

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
