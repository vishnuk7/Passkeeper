package database;

import com.mongodb.MongoClientURI;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.*;
import io.github.cdimascio.dotenv.Dotenv;

public class Db {

    public static void connection() {  
      
        Dotenv dotenv = Dotenv.load();
        String DB_USER = dotenv.get("DB_USER");
        String DB_PASSWORD = dotenv.get("DB_PASSWORD");
  
        // Creating a Mongo client 
        MongoClientURI uri = new MongoClientURI(
           "mongodb+srv://"+DB_USER+":"+DB_PASSWORD+"@cluster0.xsgtf.mongodb.net/testDatabase?retryWrites=true&w=majority");
  
        MongoClient mongoClient = new MongoClient(uri); 
        
        // Accessing the database     
        MongoDatabase database = mongoClient.getDatabase("testDatabase");
  
        MongoCollection<Document> collection = database.getCollection("testCollection");
  
        Document myDoc = collection.find().first();
        System.out.println(myDoc.toJson());
  
        // close connnection
        mongoClient.close();    
    }
}