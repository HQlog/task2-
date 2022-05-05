import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindTest {

    private void equalsFiles(String inputName, String expectedOutputInFile) throws IOException {

        StringBuilder result1 = new StringBuilder();
        try (BufferedReader read = new BufferedReader(new FileReader(inputName))) {
            String str = read.readLine();
            while (str != null) {
                result1.append(str).append("\n");
                str = read.readLine();
            }
        }
        StringBuilder result2 = new StringBuilder();
        try (BufferedReader read = new BufferedReader(new FileReader(expectedOutputInFile))) {
            String str1 = read.readLine();
            while (str1 != null) {
                result2.append(str1).append("\n");
                str1 = read.readLine();
            }
        }

        assertEquals(result2.toString().trim(), result1.toString().trim());
    }

    @Test
    void test() throws IOException {


      new Find().workWithArguments("-d /Users/Xiaomi/task2!/input/sdf a.txt".trim().split(" "));

        equalsFiles("output/newTestFind.txt", "output/forTest1.txt");
        new File("output/newTestFind.txt").delete();

        new Find().workWithArguments("-r -d /Users/Xiaomi/task2!/input q.txt".trim().split(" "));

        equalsFiles("output/newTestFind.txt", "output/forTest2.txt");
        new File("output/newTestFind.txt").delete();

    }
}
