package hexlet.code;

import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {

    private static String getExpected(String pathFile) throws FileNotFoundException {
        Path path = Paths.get(pathFile).toAbsolutePath().normalize();
        return new BufferedReader(new FileReader(path.toFile()))
                .lines()
                .collect(Collectors.joining("\n"));
    }

    @Test
    void testGenerateJsonDiff() throws Exception {
        String pathFile1 = "src/test/resources/fixtures/file11.json";
        String pathFile2 = "src/test/resources/fixtures/file12.json";
        String pathExpectedFile = "src/test/resources/expected/stylish_file_11_12.txt";

        String expected = getExpected(pathExpectedFile);
        String actual = Differ.generate(pathFile1, pathFile2,"stylish");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testGenerateYmlDiff() throws Exception {
        String pathFile1 = "src/test/resources/fixtures/file11.yml";
        String pathFile2 = "src/test/resources/fixtures/file12.yml";
        String pathExpectedFile = "src/test/resources/expected/stylish_file_11_12.txt";

        String expected = getExpected(pathExpectedFile);
        String actual = Differ.generate(pathFile1, pathFile2,"stylish");
        assertThat(actual).isEqualTo(expected);
    }
}
