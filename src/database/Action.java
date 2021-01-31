package database;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Action extends Connection {
  
   public Action(){
        super();
    }

    public void createUser(String username,String password){
        // query to be inserted
        Document query = new Document("_id", new ObjectId());
        query.append("user_name", username)
                .append("user_password", password)
                // .append("collection", java.util.Arrays.asList(
                //     new Document("name", "Google").append("account_id", "hehe@hhe.com").append("account_password", "gjkdfyhg"),
                //     new Document("name", "Yahooooo").append("account_id", "haha@hha.com").append("account_password", "dkfjhgfg")
                //     ))
                ;
        
        collection.insertOne(query);
        System.out.println("It's done😎");
        closeConnection();
    }
}
