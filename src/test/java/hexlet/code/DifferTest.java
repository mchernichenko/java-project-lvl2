package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {

    private static Map<String, Object> data1;
    private static Map<String, Object> data2;
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    private static Map<String, Object> readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return MAPPER.readValue(filePath.toFile(), new TypeReference<>() {
        });
    }

    @BeforeAll
    public static void beforeAll() throws Exception {
        data1 = readFixture("file1.json");
        data2 = readFixture("file2.json");
    }

    @Test
    void testGenerate() {
        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";

        String actual = Differ.generate(data1, data2);
        assertThat(actual).isEqualTo(expected);
    }
}
