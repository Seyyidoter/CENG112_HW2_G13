package util;

import command.Command;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * FileIO - Reads commands from a CSV file and returns them as a list.
 * Each "new" command is assigned an increasing arrivalTime based on order.
 */
public class FileIO {

    public static List<Command> readCommandsFromCSV(String filePath) {
        List<Command> commandList = new ArrayList<>();
        int arrivalCounter = 1; // Custom arrivalTime based on line order

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", -1);
                String type = parts[0].toLowerCase();

                switch (type) {
                    case "new":
                        if (parts.length == 4) {
                            String name = parts[1];
                            String issue = parts[2];
                            String priority = parts[3];
                            Command cmd = new Command("new", name, issue, priority);
                            cmd.setArrivalTime(arrivalCounter++); // Assign manual order
                            commandList.add(cmd);
                        }
                        break;

                    case "resolve":
                        commandList.add(new Command("resolve"));
                        break;

                    case "display":
                    case "history":
                        if (parts.length == 2) {
                            commandList.add(new Command(type, parts[1]));
                        } else {
                            commandList.add(new Command(type));
                        }
                        break;

                    default:
                        System.out.println("Unknown command in CSV: " + line);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }

        return commandList;
    }
}

