import org.junit.Test;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TailTest {

    private void assertFileContent(String name, String expectedContent) {
        try {
            Scanner o = new Scanner(new FileInputStream(new File(name)));
            Scanner e = new Scanner(new FileInputStream(new File(expectedContent)));
            while (o.hasNextLine() || e.hasNextLine()) {
                assertEquals(o.nextLine(), e.nextLine());
            }
            o.close(); e.close();
        } catch (Exception e) {
            System.out.print(e.toString());
            fail();
        }
    }

    @Test
    public void TestF1() {
        String[] args = {"-o", "Files/Output/OFile1.txt", "Files/Input/IFile1.txt"};
        TailLauncher.main(args);
        assertFileContent("Files/Output/OFile1.txt", "Files/Expect/EFile1.txt");
    }

    @Test
    public void TestF2() {
        String[] args = {"-c", "58", "-o", "Files/Output/OFile2.txt", "Files/Input/IFile2.txt"};
        TailLauncher.main(args);
        assertFileContent("Files/Output/OFile2.txt", "Files/Expect/EFile2.txt");
    }

    @Test
    public void TestF3() {
        String[] args = {"-n", "1", "-o", "Files/Output/OFile3.txt", "Files/Input/IFile3.txt"};
        TailLauncher.main(args);
        assertFileContent("Files/Output/OFile3.txt", "Files/Expect/EFile3.txt");
    }

    @Test
    public void TestF4() {
        String[] args = {"-n", "15", "-o", "Files/Output/OFile4.txt"
                , "Files/Input/IFile4.txt", "Files/Input/IFile3.txt", "Files/Input/IFile2.txt"};
        TailLauncher.main(args);
        assertFileContent("Files/Output/OFile4.txt", "Files/Expect/EFile4.txt");
    }
}