import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client implements Runnable {

    private int gate;
    private String mensaje;

    public Client(int puerto, String mensaje) {
        this.gate = puerto;
        this.mensaje = mensaje;
    }

    @Override
    public void run() {
        final String host = "127.0.0.1";
        DataOutputStream out;
        DataInputStream in;

        try {
            Socket sck = new Socket(host, gate);
            out = new DataOutputStream(sck.getOutputStream());
            out.writeUTF(mensaje);
            sck.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

