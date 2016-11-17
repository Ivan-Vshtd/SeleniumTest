package veshtard.task3;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import veshtard.Methods;

public class PassangerInfoPage extends Methods{
    private WebDriver driver;
	@FindBy(css="span.ui-icon.ui-icon-triangle-1-s")
	private WebElement prefix;
	@FindBy(id="ui-id-3")
	private WebElement mr;
	@FindBy(id="firstName0")
	private WebElement firstName;
	@FindBy(id="middlename0")
	private WebElement middleName;
	@FindBy(id="lastName0")
	private WebElement lastName;
	@FindBy(css="#gender0-button > span.ui-icon.ui-icon-triangle-1-s")
	private WebElement gender;
	@FindBy(id="ui-id-61")
	private WebElement male;
	@FindBy(css="#month0-button > span.ui-icon.ui-icon-triangle-1-s")
	private WebElement month;
	@FindBy(id="ui-id-70")
	private WebElement july;
	@FindBy(css="#day0-button > span.ui-selectmenu-text")
	private WebElement day;
	@FindBy(id="ui-id-94")
	private WebElement d18;
	@FindBy(css="#year0-button > span.ui-selectmenu-text")
	private WebElement year;
	@FindBy(id="ui-id-138")
	private WebElement y1987;
	@FindBy(id="emgcFirstName_0")
	private WebElement emgcFirstName;
	@FindBy(id="emgcLastName_0")
	private WebElement emgcLastName;
	@FindBy(css="#emgcCountry_0-button > span.ui-selectmenu-text")
	private WebElement emgCountry;
	@FindBy(id="ui-id-242")
	private WebElement ec375;
	@FindBy(id="emgcPhoneNumber_0")
	private WebElement emgPhoneNumber;
	@FindBy(xpath="//div[4]/div[3]/div/div/span/span")
	private WebElement deviceType;
	@FindBy(xpath="//div[19]/ul/li")
	private WebElement cell;
	@FindBy(css="#countryCode0-button > span.ui-selectmenu-text")
	private WebElement countryCode;
	@FindBy(id="ui-id-465")
	private WebElement c375;
	@FindBy(id="telephoneNumber0")
	private WebElement telephoneNumber;
	@FindBy(id="email")
	private WebElement email;
	@FindBy(id="reEmail")
	private WebElement reEmail;
	@FindBy(id="paxReviewPurchaseBtn")
	private WebElement purchaseBtn;

    public void selectPrrefix()
    {
        super.clicks(prefix,mr);
    }

    public void enterFirstName(String fName)
    {
        super.sendStringKey(firstName,fName);
    }

    public void enterMiddleName(String mName)
    {
        super.sendStringKey(middleName,mName);
    }

    public void enterLastName(String lName)
    {
        super.sendStringKey(lastName,lName);
    }

    public void selectGender()
    {
        super.clicks(gender,male);
    }

    public void selectMonth()
    {
        super.clicks(month,july);
    }

    public void selectDay()
    {
       super.clicks(day,d18);
    }

    public void  selectYear()
    {
        super.clicks(year,y1987);
    }

    public void enterEmergencyFirstName(String eFName)
    {
        super.sendStringKey(emgcFirstName,eFName);
    }

    public void enterEmergencyLastName(String eLName)
    {
       super.sendStringKey(emgcLastName,eLName);
    }

    public void selectEmgCountry()
    {
        super.clicks(emgCountry,ec375);
    }

    public void enterEmgPhoneNumber(String emgNumber)
    {
        super.sendStringKey(emgPhoneNumber,emgNumber);
    }

    public void selectDevType()
    {
        super.clicks(deviceType,cell);
    }

    public void selectCountry()
    {
        super.clicks(countryCode,c375);
    }

    public void enterPhoneNumber(String phNumber)
    {
        super.sendStringKey(telephoneNumber,phNumber);
    }

    public void enterEmail(String emailAddr)
    {
        super.sendStringKey(email,emailAddr);
    }

    public void reEnterEmail(String reEmailAddr)
    {
        super.sendStringKey(reEmail,reEmailAddr);
    }

    public void submit()
    {
        super.clicks(purchaseBtn);
    }

    public PassangerInfoPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
