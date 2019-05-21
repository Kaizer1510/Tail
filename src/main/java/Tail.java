import java.io.*;
import java.util.*;

public class Tail {

    private final int lineNum;
    private final int charNum;

    public Tail (int lineNum, int charNum) {
        this.lineNum = lineNum;
        this.charNum = charNum;
    }

    private String extractInput(String inputName) throws IOException {
        InputStream i = System.in;
        if (!inputName.equals("")) i = new FileInputStream(new File(inputName));
        Scanner scanner = new Scanner(i);
        StringBuilder in = new StringBuilder();
        while(scanner.hasNextLine()){
            in.append(scanner.nextLine() + "\n");
        }
        scanner.close();
        return in.toString();
    }

    public String cut(String inputName) throws IOException {
        String text = extractInput(inputName);
        String tail = "";
        if (charNum == 0) {
            List<String> lineList = Arrays.asList(text.split("\n"));
            int size = lineList.size();
            if (size > lineNum) lineList = lineList.subList(size - lineNum, size);
            for (String line: lineList ) {
                tail = tail + line + "\n";
            }
        } else {
            int length = text.length();
            if (length > charNum) tail = text.substring(length - charNum - 1);
        }
        return tail;
    }
}
