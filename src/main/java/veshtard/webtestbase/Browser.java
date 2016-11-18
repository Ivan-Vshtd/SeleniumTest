package veshtard.webtestbase;


import veshtard.Props;

public enum  Browser {
    FIREFOX("firefox"),
    CHROME("chrome");

    private  String browserName;
    private static Props prop;

    private Browser(String browserName) {
        this.browserName = browserName;
    }

    public String getBrowserName() {
        return browserName;
    }

    public static Browser getByName(String name){
        for(Browser browser : values()) {
            if(browser.getBrowserName().equalsIgnoreCase(name)) {
                return browser;
            }
        }
        return null;
    }
    public static Browser getBrowser() {
        prop = new Props();
        Browser browserForTests = Browser.getByName(prop.getBrowser());
        if (browserForTests != null) {
            return browserForTests;
        } else {
            throw new IllegalStateException("Browser name '" + prop.getBrowser() + "' is not valid");
        }
    }
}
