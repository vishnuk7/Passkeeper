import java.util.List;

import parameter.Options;
import parameter.ParserArgs;

public class App {
    private static void parameter(Options op) {
        switch (op.flag) {
            case "-h":
                System.out.println("call the help method");
                break;
        }

    }

    public static void main(String[] args) {
        ParserArgs parserArgs = new ParserArgs(args);
        List<Options> optList = parserArgs.getOptList();

        for (Options op : optList) {
            parameter(op);
        }
    }
}
