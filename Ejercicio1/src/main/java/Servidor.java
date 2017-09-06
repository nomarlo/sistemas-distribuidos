import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.util.*;

public class Servidor {
    public static final int PUERTO = 5000;

    public static void main(String args[]) throws IOException {

        ServerSocket serverSocket = new ServerSocket(PUERTO);

        OperacionesImp operaciones = new OperacionesImp();

        double primerNumero, segundoNumero;

        List<Double> elementos;

        String elementosCadena;


        while (true) {

            Socket clientSocket = serverSocket.accept();

            BufferedReader bufferEntrada = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String comando = bufferEntrada.readLine();

            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);

            elementos = new ArrayList<Double>();

            if (comando.compareTo("suma") == 0) {
                primerNumero = Double.parseDouble(bufferEntrada.readLine());
                segundoNumero = Double.parseDouble(bufferEntrada.readLine());

                printWriter.println(operaciones.suma(primerNumero, segundoNumero));
            } else if (comando.compareTo("resta") == 0) {
                primerNumero = Double.parseDouble(bufferEntrada.readLine());
                segundoNumero = Double.parseDouble(bufferEntrada.readLine());

                printWriter.println(operaciones.resta(primerNumero, segundoNumero));
            } else if (comando.compareTo("dividir") == 0) {
                primerNumero = Double.parseDouble(bufferEntrada.readLine());
                segundoNumero = Double.parseDouble(bufferEntrada.readLine());

                printWriter.println(operaciones.dividir(primerNumero, segundoNumero));
            } else if (comando.compareTo("multiplicar") == 0) {
                primerNumero = Double.parseDouble(bufferEntrada.readLine());
                segundoNumero = Double.parseDouble(bufferEntrada.readLine());

                printWriter.println(operaciones.multiplicar(primerNumero, segundoNumero));
            } else if (comando.compareTo("sumaElementos") == 0) {
                elementosCadena = bufferEntrada.readLine();
                elementos = generarArrayList(elementosCadena);

                printWriter.println(operaciones.suma(elementos.toArray(new Double[elementos.size()])));

            } else if (comando.compareTo("promedio") == 0) {
                elementosCadena = bufferEntrada.readLine();
                elementos = generarArrayList(elementosCadena);

                printWriter.println(operaciones.promedio(elementos.toArray(new Double[elementos.size()])));
            } else if (comando.compareTo("desviacionEstandar") == 0) {
                elementosCadena = bufferEntrada.readLine();
                elementos = generarArrayList(elementosCadena);

                printWriter.println(operaciones.desviacionEstandar(elementos.toArray(new Double[elementos.size()])));
            }


            bufferEntrada.close();
            printWriter.close();
            clientSocket.close();
        }
    }

    private static List generarArrayList(String cadena) {
        List<Double> elementos = new ArrayList<Double>();

        StringTokenizer s = new StringTokenizer(cadena, ",");

        while (s.hasMoreTokens()) {
            elementos.add(Double.parseDouble(s.nextToken()));
        }

        return elementos;
    }

}