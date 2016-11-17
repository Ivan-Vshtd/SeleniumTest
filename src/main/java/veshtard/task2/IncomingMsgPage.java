package veshtard.task2;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import veshtard.Methods;

import java.util.ArrayList;
import java.util.List;

public class IncomingMsgPage extends Methods{
    private static final Logger log = Logger.getLogger(IncomingMsgPage.class);
    private WebDriver driver;
    @FindBy(linkText = "Отправленные")
    private WebElement outgoingMsgBtn;
    @FindBy(xpath = "//td/div/input")
    private WebElement search;
    @FindBy(xpath = "//button")
    private WebElement searchBtn;
    @FindBy(xpath = "//div[2]/div/div/div/div[2]/div/div/div/div/div")
    private WebElement newMsgBtn;
	@FindBy(xpath="//div[5]/div/div/table/tbody/tr")
	private List<WebElement> messages;

    public WebElement getOutgoingMsgBtn() {
        return outgoingMsgBtn;
    }

    public void checksearchLine()
    {
        super.checkStringLine(search);

    }

    public void search(String searchString)
    {
        super.sendStringKey(search,searchBtn,searchString);
    }
    public void createNewMsg()
    {
        super.clicks(newMsgBtn);
    }

    public int countMessages() {
        List<WebElement> msgelements = messages;
        int count = 0;
        for (WebElement element : msgelements) {
            count++;
        }
        System.out.println("Total messages: " + count);
        return count;
    }

    public void getListOfMessages() {
        List<List<String>> listlistmsg = new ArrayList<List<String>>();
        List<String> list = new ArrayList<String>();

        List<WebElement> webelements = messages;
        for (WebElement element : webelements) {
            list.add(element.getText());
        }
        listlistmsg.add(list);
        System.out.println(listlistmsg);
    }
    public void goToOutMsg() throws InterruptedException
    {
        super.clicks(outgoingMsgBtn);
        Thread.sleep(3000);
    }

    public void assertExpectedCount(int expCount,int actCount)
    {
        log.debug("Enter expected number of messages "+expCount);
        try {
            Assert.assertEquals(actCount,expCount);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public IncomingMsgPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
}
