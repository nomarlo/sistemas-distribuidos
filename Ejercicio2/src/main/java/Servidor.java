import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends Thread {

    public static final int PUERTO = 6666;
    ServerSocket socketServidor;

    public Servidor(){
        try {
            socketServidor = new ServerSocket(PUERTO);
            System.out.println("Ok");
        }
        catch (IOException e){
            e.printStackTrace();
        }
        start();
    }

    public void run(){
        try {
            while (true) {
                Socket socketCliente = socketServidor.accept();

                SQLConexion miConexion = new SQLConexion(socketCliente);

            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new Servidor();
    }

}
