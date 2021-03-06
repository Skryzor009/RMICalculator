/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RMICALCULATOR;

import RMICALCULATOR.FileExplorer;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author skryzor
 */
public final class CLIExecutor extends UnicastRemoteObject implements RMICommandExecutor {

    private static final Pattern REGEX = Pattern.compile("^(.+?)([ ](.*))?$");

    private final FileExplorer fileExplorer;

    public CLIExecutor(FileExplorer fileExplorer) throws RemoteException {
        this.fileExplorer = fileExplorer;
    }

    @Override
    public String execute(String command) throws RemoteException {

        Matcher matcher = REGEX.matcher(command);

        if (matcher.matches()) {

            String cmd = matcher.group(1);
            System.err.println(cmd);
            switch (cmd) {
                case "readf":
                    System.err.println("Selected cat");
                    return fileExplorer.readFileContent(matcher.group(3));
                case "showf":
                    System.err.println("Selected ls");
                    return fileExplorer.listAllFiles();
                case "delf":
                    System.err.println("Selected rm");
                    return fileExplorer.deleteFile(matcher.group(3));
                case "addf":
                    System.err.println("Selected touch");
                    return fileExplorer.createFile(matcher.group(3));
                case "writef":
                    System.err.println("Selected sh");
                    return processAppend(matcher.group(3));
                case "addwritef":
                    System.err.println("Selected toucha");
                    return processAppendAndC(matcher.group(3));
                default:
                    return "Command doesn't exist";
            }
        }

        return "Wrong Input";

    }

    private String processAppend(String appendParams) {
        String command;
        String text;
        Matcher matcher = REGEX.matcher(appendParams);

        if (matcher.matches()) {

            return fileExplorer.fileWrite(matcher.group(1), matcher.group(3));

        }

        return "Can't execute command";
    }

    private String processAppendAndC(String appendParams) {
        String command;
        String text;
        Matcher matcher = REGEX.matcher(appendParams);

        if (matcher.matches()) {

            return fileExplorer.fileCreateWrite(matcher.group(1), matcher.group(3));

        }

        return "Can't execute command";
    }

}
