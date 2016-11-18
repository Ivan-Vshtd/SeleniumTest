package veshtard;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Props {
    private static final String PATH_TO_PROPERTIES = "src/main/resources/variables.properties";

    private String loadProperty(String nameOfProp) {

        Properties prop = new Properties();

        try {
            FileInputStream fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            prop.load(fileInputStream);

        } catch (IOException e) {
            System.out.println("Ошибка в программе: файл " + PATH_TO_PROPERTIES + " не обнаружен");
            e.printStackTrace();
        }
        return prop.getProperty(nameOfProp);
    }

    public String getBrowser() {
        return loadProperty("browser");
    }

    public String getTutUrl() {
        return loadProperty("tutUrl");
    }

    public String getPropDriver() {
        return loadProperty("propDriver");
    }

    public String getDriverPath() {
        return loadProperty("driverPath");
    }

    public String getLinkString() {
        return loadProperty("linkString");
    }


    public String getGoogleUrl() {
        return loadProperty("googleUrl");
    }

    public String getStringKey() {
        return loadProperty("stringKey");
    }

    public String getLogin() {
        return loadProperty("login");
    }

    public String getPassword() {
        return loadProperty("password");
    }

    public String getSearchString() {
        return loadProperty("searchString");
    }

    public String getMail_destination() {
        return loadProperty("mail_destination");
    }

    public String getMail_subject() {
        return loadProperty("mail_subject");
    }

    public String getMail_textbody() {
        return loadProperty("mail_textbody");
    }

    public String getMsg_assert() {
        return loadProperty("msg_assert");
    }

    public String getAttribute() {
        return loadProperty("attribute");
    }

    public int getExpcount_assert() {
        return Integer.parseInt(loadProperty("expcount_assert"));
    }

    public String getDeltaUrl() {
        return loadProperty("deltaUrl");
    }

    public String getOriginCity() {
        return loadProperty("originCity");
    }

    public String getDestinationCity() {
        return loadProperty("destinationCity");
    }

    public String getDeprtureDate() {
        return loadProperty("deprtureDate");
    }

    public String getReturnDate() {
        return loadProperty("returnDate");
    }

    public String getFirstName() {
        return loadProperty("firstName");
    }

    public String getMiddleName() {
        return loadProperty("middleName");
    }

    public String getLastName() {
        return loadProperty("lastName");
    }

    public String getEmergencyFirstName() {
        return loadProperty("emergencyFirstName");
    }

    public String getEmergencyLastName() {
        return loadProperty("emergencyLastName");
    }

    public String getEmergencyPhoneNumber() {
        return loadProperty("emergencyPhoneNumber");
    }

    public String getPhoneNumber() {
        return loadProperty("phoneNumber");
    }

    public String getEmail() {
        return loadProperty("email");
    }
}