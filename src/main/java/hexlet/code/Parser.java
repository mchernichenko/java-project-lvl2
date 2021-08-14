package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parser(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (filePath.endsWith("json")) {
            return new ObjectMapper().readValue(path.toFile(), new TypeReference<>() {
            });
        } else {
            return new ObjectMapper(new YAMLFactory()).readValue(path.toFile(), new TypeReference<>() {
            });
        }
    }
}
