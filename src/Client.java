import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    public static void main (String[] args) {
        final String host = "127.0.0.1";
        final int gate = 6000;
        DataInputStream in;
        DataOutputStream out;

        try {
            Socket sck = new Socket(host, gate);

            in = new DataInputStream(sck.getInputStream());
            out = new DataOutputStream(sck.getOutputStream());

            out.writeUTF("Cliente: Hola Peter!");

            String mensaje = in.readUTF();
            System.out.println(mensaje);

            sck.close();

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
}
