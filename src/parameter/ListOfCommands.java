package parameter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.Action;

public class ListOfCommands {
    public Map<String, Runnable> invokeOperation;
    String currentArg;
    List<Options> optList;
    List<String> argList;

    public ListOfCommands() {
        invokeOperation = new HashMap<String, Runnable>();
        invokeOperation.put("create", () -> createUser());
        invokeOperation.put("add", () -> addNewItem());
    }

    public void check(List<String> args, List<Options> optList) {
        this.optList = optList;
        for (String arg : args) {
            this.currentArg = arg;
            invokeOperation.get(this.currentArg).run();
        }
    }

    public void createUser() {
        Options[] tmpOptions = { null, null };
        for (Options op : this.optList) {
            if (op.flag.equals("-u") && op.value != null)
                tmpOptions[0] = op;

            if (op.flag.equals("-p") && op.value != null)
                tmpOptions[1]=  op;
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
            new Action().createUser(tmpOptions[0].value, tmpOptions[1].value);
        }
    }

    public void addNewItem() {
        Options[] tmpOptions = { null, null, null };
        for (Options op : this.optList) {
            if (op.flag.equals("-an") && op.value != null)
                tmpOptions[0] = op;

            if (op.flag.equals("-id") && op.value != null)
                tmpOptions[1]=  op;
            
            if (op.flag.equals("-p") && op.value != null)
                tmpOptions[2]=  op;
        }

        if (tmpOptions[0] == null && tmpOptions[1] == null && tmpOptions[2] == null) {
            System.out.println("Account name,id and password is missing");
            return;
        } else if (tmpOptions[0] == null) {
            System.out.println("Account name is missing");
            return;
        } else if (tmpOptions[1] == null) {
            System.out.println("Account id is missing");
            return;
        } else if (tmpOptions[2] == null) {
            System.out.println("Account password is missing");
            return;
        }
         else {
            new Action().addNewItem(tmpOptions[0].value, tmpOptions[1].value, tmpOptions[2].value);
        }
    }
}
