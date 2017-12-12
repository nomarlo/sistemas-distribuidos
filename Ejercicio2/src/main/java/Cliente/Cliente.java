package Cliente;

import Servidor.Servidor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Cliente {

    public void conecta(String peticion) throws IOException, ClassNotFoundException {
        Socket client = new Socket("localhost", Servidor.PUERTO);
        System.out.println("Puerto conexion: " + client.getPort());
        DataOutputStream consulta = new DataOutputStream(client.getOutputStream());
        consulta.writeBytes(peticion);
        client.close();

    }

}
