/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.cliente;

import com.ipn.mx.entidades.Alumno;
import com.ipn.mx.interfaces.AlumnoInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Invitado
 */


public class Cliente {

    static InputStreamReader isr = new InputStreamReader(System.in);
    static BufferedReader br = new BufferedReader(isr);
    static AlumnoInterface alumno;

    public static int controlador() throws IOException, SQLException {
        imprimeMenu();
        int opc = Integer.parseInt(br.readLine());

        switch (opc){
            case 1:
                registraNuevoAlumno();
                break;
            case 2:
                eliminarAlumno();
                break;
            case 3:
                actualizarAlumno();
                break;
            case 4:
                listarAlumnos();
                break;
            case 5:
                buscarAlumnoPorId();
                break;
            case 6:
                buscarAlumnoPorEmail();
                break;
            case 7:
                   return 0;


        }

        return 1;
    }

    public static void imprimeMenu() {
        System.out.println("Ingresa el numero de la operacion deseada");
        System.out.println("1.-Registrar nuevo alumno");
        System.out.println("2.-Eliminar alumno");
        System.out.println("3.-Actualizar alumno");
        System.out.println("4.-Listar alumnos");
        System.out.println("5.-Buscar alumno por id");
        System.out.println("6.-Buscar alumno por email");
        System.out.println("7.-Salir");
    }

    public static void registraNuevoAlumno() throws IOException {
        Alumno al = capturarInformacionAlumno();
        alumno.agregarAlumno(al);
    }

    public static void actualizarAlumno() throws IOException {
        Alumno al = capturarInformacionAlumno();
        al = capturarIdAlumno(al);
        alumno.actualizarAlumno(al);
    }

    public static void eliminarAlumno() throws IOException {
        Alumno al = new Alumno();
        al = capturarIdAlumno(al);
        alumno.eliminarAlumno(al);
    }

    public static  void listarAlumnos() throws RemoteException, SQLException {
        ArrayList<Alumno> alumnos = alumno.listarAlumnos();
        System.out.println("id" + "\t\t\t" + "nombre" + "\t\t\t\t" + "apellido paterno" + "\t\t\t\t" + "apellido materno" + "\t\t\t\t" + "email");

        for (Alumno al : alumnos) {
            System.out.println(String.valueOf(al.getIdAlumno()) + "\t\t\t" + al.getNombreAlumno() + "\t\t\t\t" + al.getPaternoAlumno() + "\t\t\t\t" + al.getMaternoAlumno() + "\t\t\t\t" + al.getEmail());

        }
    }

    public static void buscarAlumnoPorId() throws IOException {
        Alumno al = new Alumno();
        al = capturarIdAlumno(al);
        al =alumno.buscarAlumno(al);
        System.out.println("id" + "\t\t\t" + "nombre" + "\t\t\t\t" + "apellido paterno" + "\t\t\t\t" + "apellido materno" + "\t\t\t\t" + "email");
        System.out.println(String.valueOf(al.getIdAlumno()) + "\t\t\t" + al.getNombreAlumno() + "\t\t\t\t" + al.getPaternoAlumno() + "\t\t\t\t" + al.getMaternoAlumno() + "\t\t\t\t" + al.getEmail());
    }

    public static void buscarAlumnoPorEmail() throws IOException {
        Alumno al = new Alumno();
        al = capturarCorreoAlumno(al);
        al =alumno.buscarXEmail(al.getEmail());
        System.out.println("id" + "\t\t\t" + "nombre" + "\t\t\t\t" + "apellido paterno" + "\t\t\t\t" + "apellido materno" + "\t\t\t\t" + "email");
        System.out.println(String.valueOf(al.getIdAlumno()) + "\t\t\t" + al.getNombreAlumno() + "\t\t\t\t" + al.getPaternoAlumno() + "\t\t\t\t" + al.getMaternoAlumno() + "\t\t\t\t" + al.getEmail());
    }

    public static Alumno capturarIdAlumno(Alumno al) throws IOException {
        System.out.println("Ingrese el id del alumno");
        al.setIdAlumno(Integer.parseInt(br.readLine()));

        return al;
    }

    public static Alumno capturarCorreoAlumno(Alumno al) throws IOException {
        System.out.println("Ingrese el email del alumno");
        al.setEmail(br.readLine());

        return al;
    }

    public static Alumno capturarInformacionAlumno() throws IOException {
        Alumno al = new Alumno();
        System.out.println("Ingrese el nombre del alumno");
        al.setNombreAlumno(br.readLine());
        System.out.println("Ingrese el apellido paterno del alumno");
        al.setPaternoAlumno(br.readLine());
        System.out.println("Ingrese el apellido materno del alumno");
        al.setMaternoAlumno(br.readLine());
        System.out.println("Ingrese el email del alumno");
        al.setEmail(br.readLine());

        return al;

    }



    public static void main(String args[]) {
        try {
            alumno = (AlumnoInterface) Naming.lookup("losAlumnos");

            while (controlador() != 0){}
//
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
