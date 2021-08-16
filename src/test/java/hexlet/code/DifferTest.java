package hexlet.code;

import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static hexlet.code.Formatter.Format.STYLISH;
import static hexlet.code.Formatter.Format.PLAIN;
import static hexlet.code.Formatter.Format.JSON;
import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {

    private static String getExpected(String pathFile) throws FileNotFoundException {
        Path path = Paths.get(pathFile).toAbsolutePath().normalize();
        return new BufferedReader(new FileReader(path.toFile()))
                .lines()
                .collect(Collectors.joining("\n"));
    }

    @Test
    void testGenerateStylishDiffFromSimpleJson() throws Exception {
        String pathFile1 = "src/test/resources/fixtures/file1_simple.json";
        String pathFile2 = "src/test/resources/fixtures/file2_simple.json";
        String pathExpectedFile = "src/test/resources/expected/diff_stylish_simple.txt";

        String expected = getExpected(pathExpectedFile);
        String actual = Differ.generate(pathFile1, pathFile2, STYLISH.toString());
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testGenerateStylishDiffFromJson() throws Exception {
        String pathFile1 = "src/test/resources/fixtures/file1_complex.json";
        String pathFile2 = "src/test/resources/fixtures/file2_complex.json";
        String pathExpectedFile = "src/test/resources/expected/diff_stylish_complex.txt";

        String expected = getExpected(pathExpectedFile);
        String actual = Differ.generate(pathFile1, pathFile2, STYLISH.toString());
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testGenerateStylishDiffFromYml() throws Exception {
        String pathFile1 = "src/test/resources/fixtures/file1_complex.yml";
        String pathFile2 = "src/test/resources/fixtures/file2_complex.yml";
        String pathExpectedFile = "src/test/resources/expected/diff_stylish_complex.txt";

        String expected = getExpected(pathExpectedFile);
        String actual = Differ.generate(pathFile1, pathFile2, STYLISH.toString());
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testGeneratePlainDiffFromJson() throws Exception {
        String pathFile1 = "src/test/resources/fixtures/file1_complex.json";
        String pathFile2 = "src/test/resources/fixtures/file2_complex.json";
        String pathExpectedFile = "src/test/resources/expected/diff_plain_complex.txt";

        String expected = getExpected(pathExpectedFile);
        String actual = Differ.generate(pathFile1, pathFile2, PLAIN.toString());
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testGenerateJsonDiffFromJson() throws Exception {
        String pathFile1 = "src/test/resources/fixtures/file1_complex.json";
        String pathFile2 = "src/test/resources/fixtures/file2_complex.json";
        String pathExpectedFile = "src/test/resources/expected/diff_json_complex.txt";

        String expected = getExpected(pathExpectedFile);
        String actual = Differ.generate(pathFile1, pathFile2, JSON.toString());
        assertThat(actual).isEqualTo(expected);
    }
}
