package RMICALCULATOR;


import RMICALCULATOR.CLIExecutor;
import RMICALCULATOR.RMICommandExecutor;
import RMICALCULATOR.FileExplorerImpl;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class NodeServer {

    public static void main(String args[]) throws RemoteException, MalformedURLException{
            LocateRegistry.createRegistry(9000);
            RMICommandExecutor commandExecutor = new CLIExecutor(new FileExplorerImpl());
            Naming.rebind("rmi://localhost:9000/fileExplorer", commandExecutor);
    }
}
