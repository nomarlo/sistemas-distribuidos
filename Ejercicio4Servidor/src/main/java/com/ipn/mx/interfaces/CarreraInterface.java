/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.interfaces;

import com.ipn.mx.entidades.Carrera;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Invitado
 */
public interface CarreraInterface extends Remote {
    public void agregarCarrera(Carrera carrera) throws RemoteException;

    public void eliminarCarrera(Carrera carrera) throws RemoteException;

    public void actualizarCarrera(Carrera carrera) throws RemoteException;

    public void listarCarreras() throws RemoteException;

    public void buscarCarrera(Carrera carrera) throws RemoteException;


}
