package veshtard.task2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import veshtard.Methods;

public class PasswdPage extends Methods{
    private WebDriver driver;
    @FindBy(id = "Passwd")
    private WebElement passwords;
    @FindBy(id = "signIn")
    private WebElement signinBtns;

    public void enterPasswd(String pswd)
    {
        WebElement password = (new WebDriverWait(driver, 2))
                .until(ExpectedConditions.visibilityOf(passwords));
        super.checkStringLine(password);
        WebElement signinBtn = (new WebDriverWait(driver, 2))
                .until(ExpectedConditions.visibilityOf(signinBtns));
        super.sendStringKey(password,signinBtn,pswd);
    }

    public PasswdPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
