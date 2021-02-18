import java.util.List;
import parameter.ListOfCommands;
import parameter.Options;
import parameter.ParserArgs;

public class App {
    public static void main(String[] args) throws Exception {
        ListOfCommands listOfCommands = new ListOfCommands();
        ParserArgs parserArgs = new ParserArgs(args);
        List<Options> optList = parserArgs.getOptList();
        List<String> argsList = parserArgs.getArgsList();

        listOfCommands.check(argsList, optList);
    }
}
