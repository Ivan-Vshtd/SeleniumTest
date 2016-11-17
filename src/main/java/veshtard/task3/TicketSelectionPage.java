package veshtard.task3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import veshtard.Methods;

public class TicketSelectionPage extends Methods{
    private WebDriver driver;
	private String xpath="/html/body/div[5]/div[2]/div/main/div[2]/div[3]/div[1]/div/div/div[7]/div[1]/div/table/tbody/tr[2]/td[2]/div[3]/button";
	@FindBy(xpath="/html/body/div[5]/div[2]/div/main/div[2]/div[3]/div[1]/div/div/div[7]/div[1]/div/table/tbody/tr[2]/td[2]/div[3]/button")
	private WebElement returntickets;

    public void selectOriginTicket()
    {
        WebElement originticket = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        super.clicks(originticket);
    }
    public void selectReturnTicket()
    {
        WebElement returnticket = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.visibilityOf(returntickets));
        super.clicks(returnticket);
    }

    public TicketSelectionPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
