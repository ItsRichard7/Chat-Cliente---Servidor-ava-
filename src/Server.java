import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    public static void main (String[] args) {
        ServerSocket servidor = null;
        Socket sck = null;
        DataInputStream in;
        DataOutputStream out;
        final int gate = 6000;

        try {
            servidor = new ServerSocket(gate);
            System.out.println("Servidor Iniciado");

            while (true){

                sck = servidor.accept();

                System.out.println("Cliente Conectado");
                in = new DataInputStream(sck.getInputStream());
                out = new DataOutputStream(sck.getOutputStream());

                String mensaje = in.readUTF();
                System.out.println(mensaje);
                out.writeUTF("Server: Apágalo Otto, apágalo");
                sck.close();
                System.out.println("Cliente Desconectado");


            }

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
