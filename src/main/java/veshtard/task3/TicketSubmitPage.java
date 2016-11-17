package veshtard.task3;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import veshtard.Methods;

public class TicketSubmitPage extends Methods{
    private WebDriver driver;

    private String id="tripSummarySubmitBtn";



    public void submitSelection()
    {
        WebElement submitbtn = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.presenceOfElementLocated(By.id(id)));
        super.clicks(submitbtn);
    }

    public TicketSubmitPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
