package RMICALCULATOR;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileExplorerImpl implements FileExplorer {

    @Override
    public String readFileContent(String name) {
        String text = "";

        try {
            System.err.println(name);
            File myObj = new File(name);
            try (Scanner myReader = new Scanner(myObj)) {
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();

                    text = text + data + "\n";
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist");
            return "File doesn't exist";
        }

        return text;
    }

    @Override
    public String listAllFiles() {
        String text = "";

        try (Stream<Path> paths = Files.list(Paths.get(""))) {

            StringBuilder sb = new StringBuilder();

            paths.filter(Files::isRegularFile)
                    .map(x -> x.toString())
                    .forEach(s -> sb.append(s).append('\n'));

            text = sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }

    @Override
    public String deleteFile(String name) {
        String text = "";

        File file = new File(name);

        if (file.delete()) {
            text = "File deleted";
            System.out.println("File deleted");
        } else {
            text = "File doesn't exist";
            System.out.println("File doesn't exist");
        }

        return text;
    }

    @Override
    public String createFile(String name) {
        String text = "";

        try {
            File myObj = new File(name);
            if (myObj.createNewFile()) {
                System.out.println("Created: " + myObj.getName());
                text = "Created: " + myObj.getName();
            } else {
                System.out.println("File exists.");
                text = "File exists.";
            }
        } catch (IOException e) {
            System.out.println("Error");
            text = "Error";
            e.printStackTrace();
        }

        return text;
    }

    @Override
    public String fileWrite(String name, String message) {
        String text = "";

        try {
            Files.write(Paths.get(name), message.getBytes(), StandardOpenOption.APPEND);
            text = "Text added";
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
            text = "File doesn't exist";
        }
        return text;
    }

    @Override
    public String fileCreateWrite(String name, String message) {
        String text = "";

        String context = null;
        context = message;
        File file = new File(name);
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write(context);
            text = "Text added";
        } catch (IOException e) {
            text = "File doesn't exist";
            e.printStackTrace();

        } finally {
            //close resources
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return text;
        }

    }

}
