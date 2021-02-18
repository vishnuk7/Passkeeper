<<<<<<< HEAD
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
        invokeOperation.put("list", () -> list());
        invokeOperation.put("login", () -> login());
        invokeOperation.put("delete", () -> delete());
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
            new Action().createUser(tmpOptions[0].value, tmpOptions[1].value);
        }
    }

    public void addNewItem() {
        Options[] tmpOptions = { null, null, null };
        for (Options op : this.optList) {
            if (op.flag.equals("-an") && op.value != null)
                tmpOptions[0] = op;

            if (op.flag.equals("-id") && op.value != null)
                tmpOptions[1] = op;

            if (op.flag.equals("-p") && op.value != null)
                tmpOptions[2] = op;
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
        } else {
            new Action().addNewItem(tmpOptions[0].value, tmpOptions[1].value, tmpOptions[2].value);
        }
    }

    public void list() {
        Options[] tmpOptions = { null, null };
        boolean listAll = false;
        for (Options op : this.optList) {
            if (op.flag.equals("-an") && op.value != null)
                tmpOptions[0] = op;

            if (op.flag.equals("-l")) {
                tmpOptions[1] = op;
                listAll = true;
            }
        }
        if (tmpOptions[0] != null && tmpOptions[1] != null)
            new Action().listOutAccount(tmpOptions[0].value);
        else if (listAll)
            new Action().listAll();
        else if (tmpOptions[0] == null && tmpOptions[1] == null){
            System.out.println("invalid flag");
            return;
        } else
            new Action().list(tmpOptions[0].value);
    }

    public void login() {
        // passkeeper login -u <user_name> -p <password>
        Options[] tmpOptions = { null, null };
        for (Options op : this.optList) {
            if (op.flag.equals("-u") && op.value != null)
                tmpOptions[0] = op;
            if (op.flag.equals("-p") && op.value != null)
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
            new Action().login(tmpOptions[0].value, tmpOptions[1].value);
        }
    }

    public void delete(){
        Options[] tmpOptions = { null, null };
        boolean isList = false;
        for (Options op : this.optList) {
            if (op.flag.equals("-an") && op.value != null)
                tmpOptions[0] = op;
            if(op.flag.equals("-id") && op.value != null)
                tmpOptions[1] = op;
            if(op.flag.equals("-l"))
                isList=true;
        }

        if(tmpOptions[0] == null){
            System.out.println("Account name is missing!!");
            return;
        } else if(tmpOptions[0] != null && tmpOptions[1] != null){
            new Action().deleteOne(tmpOptions[0].value, tmpOptions[1].value);
        } else if(isList && tmpOptions[0] != null) {
            new Action().deleteSelection(tmpOptions[0].value);
        } else{
            new Action().deleteAll(tmpOptions[0].value);
        }
    }
}

=======
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
        invokeOperation.put("list", () -> list());
        invokeOperation.put("login", () -> login());
        invokeOperation.put("delete", () -> delete());
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
            new Action().createUser(tmpOptions[0].value, tmpOptions[1].value);
        }
    }

    public void addNewItem() {
        Options[] tmpOptions = { null, null, null };
        for (Options op : this.optList) {
            if (op.flag.equals("-an") && op.value != null)
                tmpOptions[0] = op;

            if (op.flag.equals("-id") && op.value != null)
                tmpOptions[1] = op;

            if (op.flag.equals("-p") && op.value != null)
                tmpOptions[2] = op;
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
        } else {
            new Action().addNewItem(tmpOptions[0].value, tmpOptions[1].value, tmpOptions[2].value);
        }
    }

    public void list() {
        Options[] tmpOptions = { null, null };
        boolean listAll = false;
        for (Options op : this.optList) {
            if (op.flag.equals("-an") && op.value != null)
                tmpOptions[0] = op;

            if (op.flag.equals("-l")) {
                tmpOptions[1] = op;
                listAll = true;
            }
        }
        if (tmpOptions[0] != null && tmpOptions[1] != null)
            new Action().listOutAccount(tmpOptions[0].value);
        else if (listAll)
            new Action().listAll();
        else if (tmpOptions[0] == null && tmpOptions[1] == null) {
            System.out.println("invalid flag");
            return;
        } else
            new Action().list(tmpOptions[0].value);
    }

    public void login() {
        // passkeeper login -u <user_name> -p <password>
        Options[] tmpOptions = { null, null };
        for (Options op : this.optList) {
            if (op.flag.equals("-u") && op.value != null)
                tmpOptions[0] = op;
            if (op.flag.equals("-p") && op.value != null)
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
            new Action().login(tmpOptions[0].value, tmpOptions[1].value);
        }
    }

    public void delete() {
        Options[] tmpOptions = { null, null };
        boolean isList = false;
        for (Options op : this.optList) {
            if (op.flag.equals("-an") && op.value != null)
                tmpOptions[0] = op;
            if (op.flag.equals("-id") && op.value != null)
                tmpOptions[1] = op;
            if (op.flag.equals("-l"))
                isList = true;
        }

        if (tmpOptions[0] == null) {
            System.out.println("Account name is missing!!");
            return;
        } else if (tmpOptions[0] != null && tmpOptions[1] != null) {
            new Action().deleteOne(tmpOptions[0].value, tmpOptions[1].value);
        } else if (isList && tmpOptions[0] != null) {
            new Action().deleteSelection(tmpOptions[0].value);
        } else {
            new Action().deleteAll(tmpOptions[0].value);
        }
    }

    // update details when account id is known
    // passkeeper update -an <app_name> -id <acoount_id> -cp <password> -np
    // <new_password>
    public void update() {
        Options[] tmpOptions = { null, null, null, null };
        for (Options op : this.optList) {
            if (op.flag.equals("-an") && op.value != null)
                tmpOptions[0] = op;
            if (op.flag.equals("-id") && op.value != null)
                tmpOptions[1] = op;
            if (op.flag.equals("-cp") && op.value != null)
                tmpOptions[2] = op;
            if (op.flag.equals("-np") && op.value != null)
                tmpOptions[3] = op;
        }

        if (tmpOptions[0] != null && tmpOptions[1] == null && tmpOptions[2] == null && tmpOptions[3] == null)
            new Action().interactiveUpdate(tmpOptions[0].value);
        if (tmpOptions[0] != null && tmpOptions[1] != null && tmpOptions[2] == null && tmpOptions[3] == null)
            new Action().updateOneId(tmpOptions[0].value, tmpOptions[1].value);
        if (tmpOptions[0] == null && tmpOptions[1] == null && tmpOptions[2] == null && tmpOptions[3] == null) {
            System.out.println("Please enter app name, account id, current password and new password");
            return;
        } else if (tmpOptions[1] == null && tmpOptions[2] == null && tmpOptions[3] == null) {
            System.out.println("Please enter account id, current password and new password");
            return;
        } else if (tmpOptions[2] == null && tmpOptions[3] == null) {
            System.out.println("Please enter account id, current password and new password");
            return;
        } else if (tmpOptions[3] == null) {
            System.out.println("Plase enter a new passsword");
            return;
        } else
            new Action().update(tmpOptions[0].value, tmpOptions[1].value, tmpOptions[2].value, tmpOptions[3].value);
    }
}
>>>>>>> upstream/main
