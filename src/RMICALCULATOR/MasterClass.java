/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMICALCULATOR;

import RMICALCULATOR.CLIExecutor;
import RMICALCULATOR.RMICommandExecutor;
import RMICALCULATOR.FileExplorerImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author skryzor
 */
public class MasterClass {

    public static void main(String[] args) throws NotBoundException, IOException {

        try {

            Registry registry = LocateRegistry.createRegistry(8999);

            RMIClientNodeConnector commandExecutor = new ClientNodeConnector();
            Naming.rebind("rmi://localhost:8999/master", commandExecutor);
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
            e.printStackTrace();
        }
    }
}
