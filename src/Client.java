import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client implements Runnable{
    private int gate;
    private String message;

    public Client(int gate,String message){
        this.gate = gate;
        this.message = message;
    }

    public String calculate (String text) {
        String[] partes;
        String answer;
        double result;

        try {
            partes = text.split(",");
            int valor = Integer.parseInt(partes[0]);
            int peso = Integer.parseInt(partes[1]);
            int porcentaje = Integer.parseInt(partes[2]);
            result = valor * porcentaje / 100;
            result += (peso * 0.15);
            answer = String.valueOf(result);
        } catch (Exception e) {
            answer = "Error. Escriba numeros enteros divididos por comas";
        }
        return answer;
    }

    @Override
    public void run(){
        final String host = "127.0.0.1";
        DataOutputStream out;
        DataInputStream in;

        try{
            Socket sck = new Socket(host, gate);
            out = new DataOutputStream(sck.getOutputStream());
            out.writeUTF(message);
            sck.close();
        } catch(IOException ex){
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE,null,ex);
        }
    }
}
