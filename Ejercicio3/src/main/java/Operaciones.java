import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Operaciones extends Remote {
    double suma(double primerSumando, double segundoSumando) throws RemoteException;

    double resta(double minuendo, double sustraendo) throws RemoteException;

    double dividir(double dividendo, double divisor) throws RemoteException;

    double multiplicar(double primerFactor, double segundoFactor) throws RemoteException;

    double suma(Double[] elementos) throws RemoteException;

    double promedio(Double[] elementos) throws RemoteException;

    double desviacionEstandar(Double[] elementos) throws RemoteException;




}
