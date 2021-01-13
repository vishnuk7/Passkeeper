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
        List<String> argsList = parserArgs.getArgsList();

        System.out.println("hello");

        for (Options op : optList) {
            parameter(op);
        }

        for(String arg: argsList){
            System.out.println("hee"+ arg);
        }
    }
}
