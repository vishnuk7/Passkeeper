package parameter;

import java.util.ArrayList;
import java.util.List;

// for (int i = 0; i < args.length; i++) {
//     switch (args[i].charAt(0)) {
//     case '-':
//         if (args[i].length < 2)
//             throw new IllegalArgumentException("Not a valid argument: "+args[i]);
//         if (args[i].charAt(1) == '-') {
//             if (args[i].length < 3)
//                 throw new IllegalArgumentException("Not a valid argument: "+args[i]);
//             // --opt
//             doubleOptsList.add(args[i].substring(2, args[i].length));
//         } else {
//             if (args.length-1 == i)
//                 throw new IllegalArgumentException("Expected arg after: "+args[i]);
//             // -opt
//             optsList.add(new Option(args[i], args[i+1]));
//             i++;
//         }
//         break;
//     default:
//         // arg
//         argsList.add(args[i]);
//         break;
//     }
// }

public class ParserArgs {
    List<Options> optList = new ArrayList<Options>();
    List<String> argsList = new ArrayList<String>();

    public ParserArgs(String[] args){
        // int count = 0;
        for (int i = 0; i < args.length; i++){
            
            switch (args[i].charAt(0)) {
                case '-': 
                    if(args[i].length() > 2)
                        this.errorMessage(args[i]);
                    else {
                        this.optList.add(new Options(args[i], args[i+1]));
                    }
                    break;
                default: 
                    argsList.add(args[i]);
                    break;
            }
        }
        System.out.println();
    }

    private void errorMessage(String arg){
        throw new IllegalArgumentException("Expected arg after: "+arg);
    }
}
