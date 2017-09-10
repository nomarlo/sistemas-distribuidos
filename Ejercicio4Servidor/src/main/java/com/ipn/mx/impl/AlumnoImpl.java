/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.impl;

import com.ipn.mx.entidades.Alumno;
import com.ipn.mx.interfaces.AlumnoInterface;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Invitado
 */
public class AlumnoImpl extends UnicastRemoteObject implements AlumnoInterface {
    
    private Connection conexion;
    
    private static final String SQL_INSERT = "insert into alumno(nombreAlumno, paternoAlumno, maternoAlumno, email) values(?, ?, ?, ?)";
    private static final String SQL_UPDATE = "update alumno SET nombreAlumno=?, paternoAlumno=?, maternoAlumno=?, email=? WHERE idAlumno=?";
    private static final String SQL_DELETE = "delete alumno WHERE idAlumno=?";

    private void obtenerConexion() throws ClassNotFoundException{
        String usuario="root";
        String clave="";
        String url="jdbc:mysql://localhost:3306/ejercicio4";
        String driverMySql="com.mysql.jdbc.Driver";
        
        try {
            Class.forName(driverMySql);
            conexion = DriverManager.getConnection(url, usuario, clave);
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    public AlumnoImpl() throws RemoteException{
        super();
        
    }

    @Override
    public void agregarAlumno(Alumno alumno) throws RemoteException {
        PreparedStatement ps = null;
        try {
            obtenerConexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlumnoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ps = conexion.prepareStatement(SQL_INSERT);
            ps.setString(1, alumno.getNombreAlumno());
            ps.setString(2, alumno.getPaternoAlumno());
            ps.setString(3, alumno.getMaternoAlumno());
            ps.setString(4, alumno.getEmail());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try{
                if(ps != null) ps.close();
                if(conexion != null) conexion.close();
                    
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }

    @Override
    public void eliminarAlumno(Alumno alumno) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizarAlumno(Alumno alumno) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void listarAlumnos() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void buscarAlumno(Alumno alumno) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void buscarXEmail(String email) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String[] args) {
        try{
            AlumnoInterface ai = new AlumnoImpl();
            LocateRegistry.createRegistry(1099);
            Naming.bind("losAlumnos", ai);
            System.out.println("OK");
        }catch(Exception e){
            
        }
    }
    
}
