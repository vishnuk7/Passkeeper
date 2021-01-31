package parameter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.Action;

public class ListOfCommands {
    public Map<String, Runnable> invokeOperation;
    String currentArg;
    List<Options> optList;

    public ListOfCommands() {
        invokeOperation = new HashMap<String, Runnable>();
        invokeOperation.put("create", () -> create());
    }

    public void check(String arg, List<Options> optList) {
        this.currentArg = arg;
        this.optList = optList;
        invokeOperation.get(this.currentArg).run();
    }

    public void create() {
        Options[] tmpOptions = { null, null };
        for (Options op : this.optList) {
            if (op.flag.equals("-u")) 
                tmpOptions[0] = op;
                
            if (op.flag.equals("-p")) 
                tmpOptions[1] = op;
        }

        if (tmpOptions[0] == null && tmpOptions[1] == null) {
            System.out.println("Please enter username and password!!");
            return;
        } else if (tmpOptions[0] == null) {
            System.out.println("Please enter username!!");
            return;
        } else if (tmpOptions[1] == null) {
            System.out.println("Please enter password!!");
            return;
        } else {
            new Action().insertData(tmpOptions[0].value, tmpOptions[1].value);
        }
    }
}
