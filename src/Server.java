import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server extends Observable implements Runnable {

    private int gate;

    public Server(int puerto) {
        this.gate = puerto;
    }

    @Override
    public void run() {
        ServerSocket servidor = null;
        Socket sck = null;
        DataInputStream in;

        try {
            servidor = new ServerSocket(gate);
            System.out.println("Servidor Iniciado");

            while (true) {

                sck = servidor.accept();

                in = new DataInputStream(sck.getInputStream());
                String mensaje = in.readUTF();
                System.out.println(mensaje);

                this.setChanged();
                this.notifyObservers(mensaje);
                this.clearChanged();

                sck.close();
                System.out.println("Cliente Desconectado");

            }

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

