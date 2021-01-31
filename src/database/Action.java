package database;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;

public class Action extends Connection {
  
   public Action(){
        super();
    }

    public void createUser(String username,String password){
        // query to be inserted
        Document query = new Document("_id", new ObjectId());
        query.append("user_name", username)
                .append("user_password", password);
        
        collection.insertOne(query);
        System.out.println("It's done😎");
        closeConnection();
    }

    public void  addNewItem(String accountNmme, String id, String password){
        Document filter = new Document("_id", new ObjectId("6016a063454a445efb25b2a3"));

        collection.updateOne(filter, push("collections",
            new Document("name", accountNmme)
            .append("account_id", id)
            .append("account_password", password)));

        System.out.println("Update done😎");
        closeConnection();          
            
    }
}
