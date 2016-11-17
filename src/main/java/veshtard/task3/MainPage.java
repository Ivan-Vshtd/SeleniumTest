package veshtard.task3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import veshtard.Methods;

public class MainPage extends Methods{
    private WebDriver driver;
    @FindBy(id = "book-air-content-trigger")
    private WebElement flight;
	@FindBy(xpath="/html/body/main/div[1]/div[1]/div/nav/ul/li[2]/div/div/div/div[1]/div[1]/div[1]/div/div/form/div[2]/div[1]/div[4]/div/fieldset/label[1]/span")
	private WebElement roundTrip;
    @FindBy(id = "originCity")
    private WebElement originCity;
    @FindBy(id = "destinationCity")
    private WebElement destinationCity;
	@FindBy(id="depDateCalIcon")
	private WebElement depDateCallIcon;
	@FindBy(id="departureDate")
	private WebElement departureDate;
	@FindBy(id="returnDate")
	private WebElement returnDate;
	@FindBy(xpath="/html/body/main/div[1]/div[1]/div/nav/ul/li[2]/div/div/div/div[1]/div[1]/div[1]/div/div/form/div[2]/div[1]/div[6]/fieldset[2]/div[1]/div[1]/div[1]/div/fieldset/label[1]/span")
	private WebElement exactDays;
	@FindBy(id="cashRadioBtn")
	private WebElement money;
	@FindBy(id="findFlightsSubmit")
	private WebElement findFlights;

    public void checkBookATrip()
    {
        super.clicks(flight);
    }
    public void checkRoundTrip()
    {
        super.clicks(roundTrip);
    }
    public void setOirginCity(String city)
    {
        super.sendStringKey(originCity,city);
    }

    public void setDestinationCity(String city)
    {
        super.sendStringKey(destinationCity,city);
    }

    public void setDepartureDate(String depdate)
    {
        super.sendStringKey(departureDate,depdate);
    }

    public void setReturnDate(String retdate)
    {
        super.sendStringKey(returnDate,retdate);
    }

    public void checkExactDays()
    {
        super.clicks(exactDays);
    }

    public void checkMoney()
    {
       super.clicks(money);
    }

    public void findFlights()
    {
       super.clicks(findFlights);
    }


    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
