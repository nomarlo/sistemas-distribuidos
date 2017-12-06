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
import java.sql.*;
import java.util.ArrayList;
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
    private static final String SQL_DELETE = "delete FROM alumno WHERE idAlumno=?";
    private static final String SQL_SELECT_ALL = "select * from alumno";
    private static final String SQL_SELECT_BY_ID = "select * from alumno WHERE idAlumno=?";
    private static final String SQL_SELECT_BY_EMAIL = "select * from alumno WHERE email=?";

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

    public void eliminarAlumno(Alumno alumno) throws RemoteException {
        PreparedStatement ps = null;
        try {
            obtenerConexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlumnoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ps = conexion.prepareStatement(SQL_DELETE);
            ps.setInt(1,alumno.getIdAlumno());
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


    public void actualizarAlumno(Alumno alumno) throws RemoteException {
        PreparedStatement ps = null;
        try {
            obtenerConexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlumnoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ps = conexion.prepareStatement(SQL_UPDATE);
            ps.setString(1, alumno.getNombreAlumno());
            ps.setString(2, alumno.getPaternoAlumno());
            ps.setString(3, alumno.getMaternoAlumno());
            ps.setString(4, alumno.getEmail());
            ps.setInt(5,alumno.getIdAlumno());
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


    public ArrayList listarAlumnos() throws RemoteException, SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
        try {
            obtenerConexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlumnoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ps = conexion.prepareStatement(SQL_SELECT_ALL);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    alumnos.add(mappearAlumno(rs));
                }
            }

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

        return alumnos;
    }


    public Alumno buscarAlumno(Alumno alumno) throws RemoteException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Alumno al = new Alumno();
        try {
            obtenerConexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlumnoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ps = conexion.prepareStatement(SQL_SELECT_BY_ID);
            ps.setInt(1,alumno.getIdAlumno());
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    al = mappearAlumno(rs);
                }
            }

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

        return al;

    }


    public Alumno buscarXEmail(String email) throws RemoteException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Alumno al = new Alumno();
        try {
            obtenerConexion();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlumnoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ps = conexion.prepareStatement(SQL_SELECT_BY_EMAIL);
            ps.setString(1,email);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    al = mappearAlumno(rs);
                }
            }

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

        return al;
    }

    private Alumno mappearAlumno(ResultSet rs){
        Alumno al = new Alumno();
        try {
            al.setIdAlumno(rs.getInt(1));
            al.setNombreAlumno(rs.getString(2));
            al.setPaternoAlumno(rs.getString(3));
            al.setMaternoAlumno(rs.getString(4));
            al.setEmail(rs.getString(5));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return al;
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
