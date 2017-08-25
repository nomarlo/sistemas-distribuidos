import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.*;

public class SQLConexion extends Thread {
    protected Socket socketCliente;
    protected BufferedReader entrada;
    protected PrintStream salida;
    protected String consulta;

    public SQLConexion(Socket socketCliente) {
        this.socketCliente = socketCliente;

        try {
            entrada = new BufferedReader(new InputStreamReader(this.socketCliente.getInputStream()));

        } catch (IOException e) {
            System.err.println(e);
            try {
                this.socketCliente.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return;
        }

        start();
    }

    public void run(){
        try{
            consulta= entrada.readLine();
            System.out.println("Consulta a ejecutar: "+ consulta+ ";");
            ejecutarSQL();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ejecutarSQL(){
        Connection con;
        Statement st;
        ResultSet rs;
        ResultSetMetaData resultSetMetaData;
        boolean existenmasFilas;
        String driver ="com.mysql.jdbc.Driver";
        String usuario = "root", clave ="clave", registro;
        int numeroColumnas, i;

        try{
            Class.forName(driver);
            con = DriverManager.getConnection("jdbc:mysql=//localhost:3306/test/", usuario, clave);
            st = con.createStatement();
            rs = st.executeQuery(consulta);
            existenmasFilas = rs.next();

            if(!existenmasFilas){
                salida.println("No hay filas");
            }
            //return;

            resultSetMetaData = rs.getMetaData();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
