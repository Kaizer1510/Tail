import java.io.*;
import java.util.*;

public class Tail {

    private final int lineNum;
    private final int charNum;

    public Tail (int lineNum, int charNum) {
        this.lineNum = lineNum;
        this.charNum = charNum;
    }

    public String cut(String inputName) throws IOException {
        InputStream i = System.in;
        if (inputName != null) i = new FileInputStream(new File(inputName));
        else System.out.println("Введите текст (ввод оканчивается коммандой \"конец текста\"):");
        Scanner scanner = new Scanner(i);
        Deque<String> lineList = new ArrayDeque<String>();
        if (charNum == 0) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (inputName == null && line.equals("конец текста")) break;
                lineList.add(line + "\n");
                if (lineList.size() > lineNum) lineList.removeFirst();

            }
        } else {
            int weight = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (inputName == null && line.equals("конец текста")) break;
                weight += line.length();
                lineList.add(line + "\n");
                if ( weight - lineList.getFirst().length() > charNum) lineList.removeFirst();

            }
        }
        StringBuilder resBuilder = new StringBuilder();
        for (String line: lineList) {
            resBuilder.append(line);
        }
        String res = resBuilder.toString();
        if (charNum > 0) res = res.substring(res.length() - charNum - 1);
        scanner.close();
        return res;
    }
}
