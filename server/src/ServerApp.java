import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {

    public static void main(String[] args) throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(9900)) {

            int clientCount = 0;
            System.out.println("Server is running at port: " + serverSocket.getLocalPort());

            while (true) {
                Socket clientSocket = serverSocket.accept();
                clientCount++;
                Thread socketThread = new Thread(new SocketThread(clientSocket, "Thread " + clientCount));
                socketThread.start();

            }
        }

    }

}
