import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SocketThread implements Runnable {

    private String threadName;
    private Socket socket;
    private InputStreamReader iReader;
    private OutputStreamWriter oWriter;
    private BufferedReader bReader;
    private BufferedWriter bWriter;

    public SocketThread(Socket socket) {
        assert (socket != null);
        this.socket = socket;
    }

    public SocketThread(Socket socket, String threadName) {
        this(socket);
        this.threadName = threadName;
    }

    @Override
    public void run() {

        try {
            if (socket.isConnected()) {
                System.out.println("A client is connected...");
                this.iReader = new InputStreamReader(this.socket.getInputStream());
                this.oWriter = new OutputStreamWriter(this.socket.getOutputStream());
                this.bReader = new BufferedReader(iReader);
                this.bWriter = new BufferedWriter(oWriter);
            }

            while (socket.isConnected()) {
                String msg = bReader.readLine();
                if(msg != null && !msg.isEmpty())
                    System.out.println(threadName + ": " + msg);
                if(msg == null || msg.equalsIgnoreCase("bye")) {
                    System.out.println("Disconnecting client and deallocating thread " + threadName + "...");
                    socket.close();
                    bReader.close();
                    bWriter.close();
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
