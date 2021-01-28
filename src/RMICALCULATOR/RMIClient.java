package RMICALCULATOR;

import RMICALCULATOR.RMIClientNodeConnector;
import RMICALCULATOR.RMICommandExecutor;
import RMICALCULATOR.FileExplorer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RMIClient {

    public static void main(String[] args) throws IOException, NotBoundException {

        String rmiName = "rmi://localhost:8999/master";

        System.out.println("Connecting: " + rmiName);
        RMIClientNodeConnector c = (RMIClientNodeConnector) Naming.lookup(rmiName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {

            System.out.print("Input command:");
            String name = reader.readLine();
            System.out.println(name);
            System.out.println(c.forwardCommand(name));
        }
    }
}
