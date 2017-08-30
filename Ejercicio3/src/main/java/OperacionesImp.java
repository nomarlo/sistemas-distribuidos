import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import static java.lang.Math.sqrt;

public class OperacionesImp extends UnicastRemoteObject implements Operaciones {

    public OperacionesImp() throws RemoteException{
        super();
    }

    public double suma(double primerSumando, double segundoSumando) {
        return primerSumando + segundoSumando;
    }

    public double resta(double minuendo, double sustraendo) {
        return minuendo - sustraendo;
    }

    public double dividir(double dividendo, double divisor) {
        return dividendo / divisor;
    }

    public double multiplicar(double primerFactor, double segundoFactor) {
        return primerFactor * segundoFactor;
    }

    public double suma(Double[] elementos) {
        double resultado = 0.0;

        for (double elemento : elementos) {
            resultado += elemento;
        }

        return resultado;
    }

    public double promedio(Double[] elementos) {
        return dividir(suma(elementos), elementos.length);
    }

    public double desviacionEstandar(Double[] elementos) {
        double promedio = promedio(elementos);

        for (int i = 0; i < elementos.length; i++) {
            elementos[i] = Math.pow(resta(elementos[i], promedio), 2);
        }

        double sumaRes = suma(elementos);

        return Math.sqrt(dividir(sumaRes, elementos.length));

    }
}
