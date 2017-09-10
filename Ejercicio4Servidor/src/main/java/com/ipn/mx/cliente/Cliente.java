/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.cliente;

import com.ipn.mx.entidades.Alumno;
import com.ipn.mx.interfaces.AlumnoInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 *
 * @author Invitado
 */
public class Cliente {
    
    public static void main(String args[]){
        try {
            AlumnoInterface alumno = (AlumnoInterface) Naming.lookup("losAlumnos");
            
            Alumno al = new Alumno();
            
            al.setNombreAlumno("Pedro");
            al.setPaternoAlumno("Picapiedra");
            al.setMaternoAlumno("Pill");
            al.setEmail("pedro@mail.com");

            alumno.agregarAlumno(al);
                       
            
        }catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        
    }
    
}
