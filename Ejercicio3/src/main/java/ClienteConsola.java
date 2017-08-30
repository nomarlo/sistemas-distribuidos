import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClienteConsola {

    public static void main(String args[]) throws RemoteException {
        double x=5.5, y=9.7;
        Operaciones operaciones = null;

        try {
            operaciones = (Operaciones) Naming.lookup("operaciones");
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        System.out.println("La suma es: " + operaciones.suma(x,y));

    }
}
