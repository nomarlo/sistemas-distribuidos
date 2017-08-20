import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Servidor {
    public static final int PUERTO = 5000;

    private static Date getFecha(){
        GregorianCalendar calendarioGregoriano = new GregorianCalendar();
        return calendarioGregoriano.getTime();
    }


    public static String metodoUno(){
        Date fecha = getFecha();

        DateFormat formatoFecha = DateFormat.getDateInstance();

        return formatoFecha.format(fecha);
    }

    public static String metodoDos(){
        Date fecha = getFecha();

        DateFormat formatoHora = DateFormat.getTimeInstance();

        return formatoHora.format(fecha);
    }

    public static void main (String args []) throws IOException{

        ServerSocket serverSocket = new ServerSocket(PUERTO);


        while(true){

            Socket clientSocket = serverSocket.accept();

            BufferedReader bufferEntrada = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String comando = bufferEntrada.readLine();

            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);

            if(comando.compareTo("getFecha") == 0){
                printWriter.println(metodoUno());
            }
            else{
                printWriter.println(metodoDos());
            }

            bufferEntrada.close();
            printWriter.close();
            clientSocket.close();
        }
    }

}
