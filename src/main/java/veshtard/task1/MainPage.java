package veshtard.task1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import veshtard.Methods;

public class MainPage extends Methods{
    private WebDriver drievr;
    @FindBy(id = "search_from_str")
    private WebElement search_str;
    @FindBy(name = "search")
    private WebElement search;

    public void checkSerchLine() {
        super.checkStringLine(search_str);
    }



    public void search(String stringKey) {
        super.sendStringKey(search_str,search,stringKey);
    }



    public MainPage(WebDriver drievr) {
        PageFactory.initElements(drievr, this);
        this.drievr = drievr;
    }
}
