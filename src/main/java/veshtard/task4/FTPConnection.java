package veshtard.task4;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

public class FTPConnection {
    private static final String host = "ftp.mccme.ru";
    private static final String logIn = "anonymous";
    private static final String password = "anonymous";
    private static final String directory = "/testdir";
    private static final Logger log = Logger.getLogger(FTPConnection.class);


    public static void main(String[] args) {
        FTPClient client = new FTPClient();

        try {
            client.connect(host);

            log.debug(String.format("Sign in with %s, %s",logIn,password));
            boolean login = client.login(logIn, password);
            if (login) {
                log.info("Login success...");

                serfDirectories(client);

                log.debug("Making directory "+directory);
                boolean isMade = client.makeDirectory(directory);
                showServerReply(client);
                if (isMade) {
                    log.info("Successfully created directory: " + directory);
                    log.debug("Removing directory "+directory);
                    boolean deleted = client.removeDirectory(directory);
                    if (deleted) {
                        log.info("The directory was removed successfully.");
                    } else {
                        log.info("Could not delete the directory, it may not be empty.");
                    }

                } else {
                    log.error("Failed to create directory. See server's reply.");
                }

                boolean logout = client.logout();
                if (logout) {
                    log.info("Logout from FTP server...");
                }
            } else {
                log.error("Login fail...");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static void showServerReply(FTPClient ftpClient) {
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String reply : replies) {
                log.info("SERVER: " + reply);
            }
        }
    }

    private static void printNames(String files[]) {
        if (files != null && files.length > 0) {
            for (String file: files) {
                System.out.println(file);
            }
        }
    }
    private static void serfDirectories(FTPClient client) throws IOException
    {
        String[] files = client.listNames();
        printNames(files);

        System.out.println("================================");

        for(String file : files) {
            client.changeWorkingDirectory(file);
            String[] filesStr = client.listNames();
            printNames(filesStr);

            System.out.println("================================");
            client.changeToParentDirectory();
        }
    }
}