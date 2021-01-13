import java.util.List;

import parameter.Options;
import parameter.ParserArgs;

public class App {

    public static void main(String[] args) {
        ParserArgs parserArgs = new ParserArgs(args);
        List<Options> optList = parserArgs.getOptList();

        // for (Options op : optList) {
        //     parameter(op);
        // }
    }
}
