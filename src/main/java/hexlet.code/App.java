package hexlet.code;

import picocli.CommandLine;

import static picocli.CommandLine.Command;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0.0",
        description = "Compares two configuration files and shows a difference.")

public class App {

    public static void main(String[] args) {
        if (args.length > 0) {
            new CommandLine(new App()).execute(args);
        }
        System.out.println("Hello World!");
    }
}