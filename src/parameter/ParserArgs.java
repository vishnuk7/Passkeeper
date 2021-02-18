package parameter;

import java.util.ArrayList;
import java.util.List;

public class ParserArgs {
    List<Options> optList = new ArrayList<Options>();
    List<String> argsList = new ArrayList<String>();

    public ParserArgs(String[] args){
        List<Integer> indexTracker = new ArrayList<Integer>();
        for (int i = 0; i < args.length; i++){
            
            switch (args[i].charAt(0)) {
                case '-': 
                    if(args[i].length() == 1 || args[i].length() > 3)
                        this.errorMessage(args[i]);
                    else {
                        if(args.length-1 != i) {
                            if(args[i+1].charAt(0) != '-'){
                            this.optList.add(new Options(args[i], args[i+1]));
                            indexTracker.add(i+1);
                            }
                            else{
                                System.out.println("Value cannot be "+ args[i+1]);
                                System.exit(0);
                            }
                        }
                        else
                            this.optList.add(new Options(args[i]));
                    }
                    break;
                default: 
                        if(!indexTracker.contains(i)){
                            argsList.add(args[i]);
                        }
                    break;
            }
        }
        
    }

    public List<String> getArgsList(){
        return this.argsList;
    }
    
    public List<Options> getOptList() {
        return this.optList;
    }

    private void errorMessage(String arg){
        throw new IllegalArgumentException("Expected arg after length: "+arg);
    }
}
