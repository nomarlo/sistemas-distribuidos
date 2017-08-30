import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Servidor {
    public static void main(String args[]) throws RemoteException {
        try {
            Operaciones operaciones = new OperacionesImp();
            LocateRegistry.createRegistry(1099);
            Naming.bind("operaciones", operaciones);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
