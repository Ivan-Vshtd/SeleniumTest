package veshtard.webtestbase;

import org.openqa.selenium.remote.DesiredCapabilities;
import veshtard.Props;


public class CapabilitiesGenerator {
    private static Props prop;
    public static DesiredCapabilities getDefaultCapabilities(Browser browser) {
        prop = new Props();
        switch (browser) {
            case FIREFOX:
                return DesiredCapabilities.firefox();
            case CHROME:
                System.setProperty(prop.getPropDriver(), prop.getDriverPath());
                if (System.getProperty("webdriver.chrome.driver") == null) {
                    throw new IllegalStateException("System variable 'webdriver.chrome.driver' should be set to path for executable driver");
                }
                return DesiredCapabilities.chrome();
            default:
                throw new IllegalStateException("Browser is not supported");
        }
    }


}
