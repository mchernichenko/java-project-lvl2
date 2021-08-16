package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import java.util.concurrent.Callable;
import static picocli.CommandLine.Option;
import static picocli.CommandLine.Parameters;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0.0",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<String> {

    @Parameters(description = "path to first file", paramLabel = "filepath1")
    private String filePath1;

    @Parameters(description = "path to second file", paramLabel = "filepath2")
    private String filePath2;

    @Option(names = {"-f", "--format"},
            defaultValue = "STYLISH",
            description = "output format. Valid values: [${COMPLETION-CANDIDATES}] [default: ${DEFAULT-VALUE}]")
    private Formatter.Format format;

/*    @Option(names = {"-f", "--format"},
            defaultValue = "stylish",
            description = "output format. [default: ${DEFAULT-VALUE}]")
    private String format;*/

    /**
     * Сравнение плоских json.
     * @return Результат сравнения плоских json
     * @throws Exception
     */
    @Override
    public String call() throws Exception { // your business logic goes here...
        return Differ.generate(filePath1, filePath2, format.toString());
    }

    public static void main(String[] args) {
        CommandLine cmd = new CommandLine(new App());
        cmd.execute(args);  // здесь вызов метода call на объекте App
        String result = cmd.getExecutionResult();
        System.out.println(result);
    }
}
