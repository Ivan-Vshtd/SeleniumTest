package veshtard.task4a;

import org.apache.log4j.Logger;

import java.io.IOException;

public class MyFTPConnection {
    private static final String host = "ftp.mccme.ru";
    private static final String logIn = "anonymous";
    private static final String password = "anonymous";
    private static final String directory = "/testdir";
    private static final Logger log = Logger.getLogger(MyFTPConnection.class);

    public static void main(String[] args) throws IOException {
        MySimpleFTPClient simpleFTPClient = new MySimpleFTPClient();

        try {
            log.debug(String.format("Sign in with %s, %s",logIn,password));
            simpleFTPClient.connect(host);
            simpleFTPClient.login(logIn, password);
            serfDirectories(simpleFTPClient);
            log.debug("Making directory "+directory);
            if (simpleFTPClient.makeDirectory(directory)) {
                log.info("Successfully created directory: " + directory);
                log.debug("Removing directory "+directory);
                if (simpleFTPClient.removeDirectory(directory)) {
                    System.out.println("Directory successfully removed!");
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            try {
                simpleFTPClient.disconnect();
                log.info("Logout from FTP server...");
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }

    private static void serfDirectories(MySimpleFTPClient simpleFTP) throws IOException {
        String[] files = simpleFTP.listNames();
        printNames(files);

        System.out.println("================================");

        for (String file : files) {
            simpleFTP.changeWorkingDirectory(file);
            String[] filesStr = simpleFTP.listNames();
            printNames(filesStr);

            System.out.println("================================");
            simpleFTP.changeToParentDirectory();
        }
    }

    private static void printNames(String[] files) {
        if (files != null && files.length > 0) {
            for (String file : files) {
                System.out.println(file);
            }
        }
    }
}
