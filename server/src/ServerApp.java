import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {

    public static void main(String[] args) throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(9900)) {
            Socket clientSocket = null;
            InputStreamReader iReader = null;
            OutputStreamWriter oWriter = null;
            BufferedReader bReader = null;
            BufferedWriter bWriter = null;

            System.out.println("Server is running at port: " + serverSocket.getLocalPort());

            while (true) {
                clientSocket = serverSocket.accept();

                if (clientSocket.isConnected()) {
                    System.out.println("A client is connected...");
                    iReader = new InputStreamReader(clientSocket.getInputStream());
                    oWriter = new OutputStreamWriter(clientSocket.getOutputStream());
                    bReader = new BufferedReader(iReader);
                    bWriter = new BufferedWriter(oWriter);
                }

                while (clientSocket.isConnected()) {
                    String msg = bReader.readLine();
                    if (msg != null && !msg.isEmpty())
                        System.out.println(msg);
                    if (msg != null && msg.equalsIgnoreCase("bye")) {
                        System.out.println("Disconnecting client...");
                        clientSocket.close();
                        bWriter.close();
                        bReader.close();
                        break;
                    }
                }

            }
        }

    }

}
