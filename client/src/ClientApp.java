import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientApp {

    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket clientSocket = null;
        OutputStreamWriter oWriter = null;
        BufferedWriter bWriter = null;
        Scanner scanner = new Scanner(System.in);

        clientSocket = new Socket("localhost", 9900);
        if (clientSocket.isConnected())
            System.out.println("Client is connected...");

        while (true) {
            oWriter = new OutputStreamWriter(clientSocket.getOutputStream());
            bWriter = new BufferedWriter(oWriter);

            String x = scanner.nextLine();
            bWriter.append(x);
            bWriter.newLine();
            bWriter.flush();
            if(x.equalsIgnoreCase("bye")) {
                clientSocket.close();
                scanner.close();
                break;
            }
        }

    }

}
