/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.interfaces;

import com.ipn.mx.entidades.Alumno;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Invitado
 */
public interface AlumnoInterface extends Remote {
   public void agregarAlumno(Alumno alumno) throws RemoteException;
   
   public void eliminarAlumno(Alumno alumno) throws RemoteException;
   
   public void actualizarAlumno(Alumno alumno) throws RemoteException;
   
   public ArrayList listarAlumnos() throws RemoteException, SQLException;
   
   public Alumno buscarAlumno(Alumno alumno) throws RemoteException;
   
   public Alumno buscarXEmail(String email) throws RemoteException;
   
   
    
}
