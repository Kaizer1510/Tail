import org.kohsuke.args4j.*;
import java.io.*;
import java.util.*;

public class TailLauncher {

    @Argument(usage = "input files", metaVar = "List<String>")
    private List<String> inputList =  new ArrayList<String>();

    @Option(name = "-o", metaVar = "String", usage = "output file")
    private String out = null;

    @Option(name = "-c", usage = "number of characters", forbids = "-n")
    private int charNum = 0;

    @Option(name = "-n", usage = "number of lines", forbids = "-c")
    private int lineNum = 10;

    public static void main(String[] args) {
        new TailLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar TailJava.jar [-c num|-n num] [-o ofile] file0 file1 file2 …");
            parser.printUsage(System.err);
            return;
        }

        try {
            StringBuilder res = new StringBuilder();
            Tail tail = new Tail(lineNum, charNum);
            switch (inputList.size()) {
                case 0:
                    res.append(tail.cut(null));
                    break;
                case 1:
                    res.append(tail.cut(inputList.get(0)));
                    break;
                default:
                    for (String inputName : inputList) {
                        File f = new File(inputName);
                        res.append(f.getName() + "\n" + tail.cut(inputName));
                    }
                    break;
            }

            if (out != null) {
                try {
                    FileWriter writer = new FileWriter(new File(out));
                    writer.write(res.toString());
                    writer.close();
                } catch (Exception e) {
                    System.out.print(e.toString());
                }
            }
            else System.out.println(res.toString());
        } catch (IOException e) {
            System.err.println(e.getMessage());        }
    }
}