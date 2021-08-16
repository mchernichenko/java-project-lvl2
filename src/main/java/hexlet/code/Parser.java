package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final ObjectMapper YAML_MAPPER = new ObjectMapper(new YAMLFactory());

    public static Map<String, Object> parser(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (filePath.endsWith("json")) {
            return MAPPER.readValue(path.toFile(), new TypeReference<>() {
            });
        } else {
            return YAML_MAPPER.readValue(path.toFile(), new TypeReference<>() {
            });
        }
    }
}
