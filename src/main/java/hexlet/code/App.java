package hexlet.code;

import hexlet.code.exceptions.WrongFileFormatException;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Runnable {

    @Parameters(index = "0", description = "path to first file")
    private String filepath1;

    @Parameters(index = "1", description = "path to second file")
    private String filepath2;

    @Option(names = { "-f", "--format"}, paramLabel = "format", defaultValue = "stylish",
            description = "output format [default: stylish]")
    private String format = "stylish";

    @Override
    public final void run() {
        try {
            System.out.println(Differ.generate(filepath1, filepath2, format));
        } catch (WrongFileFormatException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Incorrect path to the file");
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
