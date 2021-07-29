package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.Callable;

import static picocli.CommandLine.*;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0.0",
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable<String> {

    @Parameters(description = "path to first file", paramLabel = "filepath1")
    private Path filePath1;

    @Parameters(description = "path to second file", paramLabel = "filepath2")
    private Path filePath2;

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format;

    @Override
    public String call() throws Exception { // your business logic goes here...
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> json1 = mapper.readValue(filePath1.toFile(), new TypeReference<Map<String,Object>>(){});
        Map<String, Object> json2 = mapper.readValue(filePath2.toFile(), new TypeReference<Map<String,Object>>(){});
        return Differ.generate(json1, json2);
    }

    public static void main(String[] args) throws IOException {
        CommandLine cmd = new CommandLine(new App());
        cmd.execute(args);
        String result = cmd.getExecutionResult();
        System.out.println(result);
    }
}