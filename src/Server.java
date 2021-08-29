import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends Observable implements Runnable {
    private int gate;
    public Server(int gate) {
        this.gate = gate;
    }

    @Override
    public void run() {
        ServerSocket servidor = null;
        Socket sck = null;
        DataInputStream in;

        try {
            servidor = new ServerSocket(gate);
            while (true) {
                sck = servidor.accept();
                in = new DataInputStream(sck.getInputStream());
                String mensaje = in.readUTF();
                this.setChanged();
                this.notifyObservers();
                this.clearChanged();
                sck.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
}
