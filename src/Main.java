//import necessary files
import command.Command;
import command.CommandProcessor;
import util.FileIO;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // file path
        String filePath = "src/example_commands.csv";

        // read commands
        List<Command> commands = FileIO.readCommandsFromCSV(filePath);

        // run them
        CommandProcessor processor = new CommandProcessor();
        processor.process(commands);
    }
}
