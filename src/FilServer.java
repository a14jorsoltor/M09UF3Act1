import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class FilServer extends Thread implements Runnable  {

    public FilServer(Socket socket) {
        this.socket = socket;
    }
    private Socket socket;
    private int nContMissatges=0;

    @Override
    public void run() {
        try (
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out.println("Missatge " + ++nContMissatges +" Hola soc el pong reponent al ping: " + inputLine);
            }
        } catch (
                IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + socket.getLocalPort() + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}
