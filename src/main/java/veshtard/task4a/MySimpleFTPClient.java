package veshtard.task4a;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MySimpleFTPClient {

    private Socket socket = null;
    private Socket dataSocket = null;
    private BufferedReader reader = null;
    private BufferedWriter writer = null;
    private String response = null;

    public MySimpleFTPClient() {

    }

    public void connect(String host, int port) throws IOException {
        if (socket != null) {
            throw new IOException("SimpleFTPclient is already connected. Disconnect first.");
        }
        socket = new Socket(host, port);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream()));

        response = readLine();
        if (!response.startsWith("220")) {
            throw new IOException("Server: "+ response);
        } else {
            System.out.println("Connection success...");
        }

    }

    public void connect(String host) throws IOException {
        this.connect(host, 21);

    }

    public void login(String user, String pass) throws IOException {

        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream()));

        sendLine("USER", user);

        response = readLine();
        if (!response.startsWith("331")) {
            throw new IOException("Server: "+ response);
        }

        sendLine("PASS", pass);

        response = readLine();
        if (!response.startsWith("230")) {
            throw new IOException("Server: "+ response);
        } else {
            System.out.println("Login success...");
        }

    }

    public void disconnect() throws IOException {
        try {
            sendLine("QUIT");
        } finally {
            socket = null;
            response = readLine();
            if (!response.startsWith("221")) {
                throw new IOException("Server: "+ response);
            } else {
                System.out.println("Logout success...");
            }
        }
    }

    public String pwd() throws IOException {
        sendLine("PWD");
        String dir = null;
        response = readLine();
        if (response.startsWith("257")) {
            int firstQuote = response.indexOf('\"');
            int secondQuote = response.indexOf('\"', firstQuote + 1);
            if (secondQuote > 0) {
                dir = response.substring(firstQuote + 1, secondQuote);
            }
        }
        return dir;
    }

    public void changeWorkingDirectory(String dir) throws IOException {
        sendLine("CWD", dir);
        response = readLine();
        if (!response.startsWith("550")) {
            if (!response.startsWith("250")) {
                throw new IOException("Server: "+ response);
            }
        }
    }

    public void changeToParentDirectory() throws IOException {
        sendLine("CDUP");
        response = readLine();
        if (!response.startsWith("250")) {
            throw new IOException("Server: "+ response);
        }
    }

    public boolean makeDirectory(String dir) throws IOException {
        sendLine("MKD", dir);
        response = readLine();
        if (response.startsWith("550")) {
            System.out.println("Creation of the directory is denied");
        } else if (!response.startsWith("250")) {
            throw new IOException("Server: "+ response);
        }
        return response.startsWith("250");
    }

    public boolean removeDirectory(String dir) throws IOException {

            sendLine("RMD", dir);
            response = readLine();
            if (response.startsWith("550")) {
                System.out.println("Removing of the directory is denied, may such a directory does not exist!");
            } else if (!response.startsWith("250")) {
                throw new IOException("Server: "+ response);
            }
        return response.startsWith("250");
    }

    public String[] listNames()
            throws IOException {
        sendLine("PASV");
        response = readLine();
        if (!response.startsWith("227")) {
            throw new IOException("Server: "+ response);
        }

        String ip = null;
        int port = -1;
        int opening = response.indexOf('(');
        int closing = response.indexOf(')', opening + 1);
        if (closing > 0) {
            String dataLink = response.substring(opening + 1, closing);
            StringTokenizer tokenizer = new StringTokenizer(dataLink, ",");
            try {
                ip = tokenizer.nextToken() + "." + tokenizer.nextToken() + "."
                        + tokenizer.nextToken() + "." + tokenizer.nextToken();
                port = Integer.parseInt(tokenizer.nextToken()) * 256
                        + Integer.parseInt(tokenizer.nextToken());
            } catch (Exception e) {
                throw new IOException("Server: "+ response);
            }
        }
        dataSocket = new Socket(ip, port);


        sendLine("NLST");
        response = readLine();
        if (!response.startsWith("150")) {
            throw new IOException("Server: "+ response);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(dataSocket.getInputStream(), "ISO-8859-1"));

        ArrayList<String> results = new ArrayList<String>();

        String line;
        while ((line = reader.readLine()) != null) {
            results.add(line);
        }

        reader.close();
        dataSocket.close();
        response = readLine();
        if (!response.startsWith("226")) {
            throw new IOException("Server: "+ response);
        }

        String[] names = new String[results.size()];
        return (String[]) results.toArray(names);
    }

    private void sendLine(String line, String args) throws IOException {
        if (socket == null) {
            throw new IOException("SimpleFTP is not connected.");
        }
        try {
            writer.write(appendLine(line, args));
            writer.flush();
        } catch (IOException e) {
            socket = null;
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendLine(String line) throws IOException {
        this.sendLine(line, (String) null);
    }

    private String appendLine(String line, String args) throws Exception {
        if (socket == null) {
            throw new IOException("SimpleFTPclient is not connected.");
        }
        try {
            StringBuilder builder = new StringBuilder();
            builder.setLength(0);
            builder.append(line);
            if (args != null) {
                builder.append(' ');
                builder.append(args);
            }

            builder.append("\r\n");

            return builder.toString();
        } catch (Exception e) {
            socket = null;
            throw e;
        }
    }

    private String appendLine(String line) throws Exception {
        return this.appendLine(line, (String) null);
    }

    private String readLine() throws IOException {
        return reader.readLine();
    }
}
