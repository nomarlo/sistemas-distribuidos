package Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

public class SQLConexion extends Thread {
    
    protected Socket socketCliente;
    protected BufferedReader entrada;
    protected PrintStream salida;
    protected String consulta;
    
    @Override
    public void run()
    {
        try
        {
            consulta= entrada.readLine();
            System.out.println("Consulta a ejecutar: "+consulta+";");
            Conexion_bd sa= new Conexion_bd();
            ArrayList palabras= new ArrayList();
            palabras=sa.obtListaPalabras(consulta);
            System.out.println("Esquema: "+sa.esquema);
            
            ArrayList atributos = new ArrayList();
            for(int j=0;j<palabras.size();j++)
            {
                atributos=sa.obtListaPalabras(sa.esquema, (String) palabras.get(j));
                for(int i=0;i<atributos.size();i++)
                    System.out.println((String)atributos.get(i));
            }
            sa.cerrarConexion();
        }
        catch(IOException e)
        {
            System.out.println("esta vacio");
        }
    }
    
    public SQLConexion(Socket socketCliente)
    {
        this.socketCliente=socketCliente;
        try
        {
            entrada = new BufferedReader(new InputStreamReader(this.socketCliente.getInputStream()));
        }
        catch(IOException e)
        {
            System.err.println(e);
            try
            {
                this.socketCliente.close();
            }
            catch(IOException ez)
            {
                    
            }
            return;
        }
        start();
    }
}
