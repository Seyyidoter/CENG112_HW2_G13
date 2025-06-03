package util;

import command.Command;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// read CSV file
public class FileIO {

    // return list of commands
    public static List<Command> readCommandsFromCSV(String filePath) {
        List<Command> commandList = new ArrayList<>();

        // Not: used try-with-resources.automatically close.
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            // each line
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1); // split by comma
                String type = parts[0].toLowerCase(); // lowercase (new, resolve, etc.)

                // Note: switch-case control
                switch (type) {
                    case "new":
                        // new command
                        if (parts.length == 4) {
                            String name = parts[1];
                            String issue = parts[2];
                            String priority = parts[3];
                            commandList.add(new Command("new", name, issue, priority));
                        }
                        break;

                    case "resolve":
                        // resolve command
                        commandList.add(new Command("resolve"));
                        break;

                    case "display":
                    case "history":
                        // with or without mode
                        if (parts.length == 2) {
                            commandList.add(new Command(type, parts[1])); // with mode
                        } else {
                            commandList.add(new Command(type)); // no mode
                        }
                        break;

                    default:
                        // unknown line
                        System.out.println("Unknown command in CSV: " + line);
                }
            }

        } catch (IOException e) {
            // error while reading
            // Not: IOException = file open/read error.
            System.out.println("Error reading CSV file: " + e.getMessage());
        }

        return commandList;
    }
}
