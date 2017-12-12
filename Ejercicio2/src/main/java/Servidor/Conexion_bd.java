package Servidor;

import java.sql.Connection;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;


public class Conexion_bd {

    public String esquema;
    public int numeroAtributos;
    Connection con = null;
    public String estado;

    public Conexion_bd() {
        estado = iniciarConexion();

    }

    private String iniciarConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ejercicio4", "root", "");
        } catch (SQLException e) {
            System.err.println("Error SQL al intentar conectar con la base de datos");
            e.printStackTrace();
            return "error sql";
        } catch (ClassNotFoundException ee) {

            System.err.println("No se pudo cargar la clase " + ee);
            return ("fallo la clase");
        }
        return "si se pudo";
    }

    public void cerrarConexion() {
        try {
            con.close();
        } catch (SQLException e) {
            System.err.println("No se pudo cerrar la conexión con la base de datos");
            e.printStackTrace();
        }
    }


    public ArrayList obtListaPalabras(String consulta) {
        Statement s;
        ResultSet rs;
        ArrayList palabras = new ArrayList();
        try {
            s = con.createStatement();
            rs = s.executeQuery(consulta);
            ResultSetMetaData tabla = rs.getMetaData();
            int numAtributos = tabla.getColumnCount();
            numeroAtributos = numAtributos;
            esquema = tabla.getTableName(numAtributos);

            for (int i = 1; i <= numAtributos; i++) {
                palabras.add(tabla.getColumnName(i));
            }
        } catch (SQLException e) {
            System.out.println("Problemas SQL en obtListaPalabras de servicioAutocompletado");
            palabras.add("Problemas SQL en obtListaPalabras de servicioAutocompletado");
            e.printStackTrace();
        } catch (Exception ee) {
            System.out.println("Problemas en obtListaPalabras de servicioAutocompletado");
            palabras.add("Problemas en obtListaPalabras de servicioAutocompletado");
            ee.printStackTrace();
        }
        return palabras;
    }


    public ArrayList obtListaPalabras(String esquema, String atributo) {
        Statement s;
        ResultSet rs;
        ArrayList palabras = new ArrayList();
        try {
            s = con.createStatement();
            rs = s.executeQuery(" SELECT " + atributo + " FROM " + esquema);
            while (rs.next()) {
                palabras.add(rs.getString(atributo));
            }
        } catch (SQLException e) {
            System.out.println("Problemas SQL en obtListaPalabras de servicioAutocompletado");
            palabras.add("Problemas SQL en obtListaPalabras de servicioAutocompletado");
            e.printStackTrace();
        } catch (Exception ee) {
            System.out.println("Problemas en obtListaPalabras de servicioAutocompletado");
            palabras.add("Problemas en obtListaPalabras de servicioAutocompletado");
            ee.printStackTrace();
        }
        return palabras;
    }

}

