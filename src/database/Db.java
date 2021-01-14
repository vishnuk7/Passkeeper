package database;

import com.mongodb.MongoClientURI;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Db {

    public static void connection() {  
      
        // set log priority from INFO to SEVERE
        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.SEVERE);

        // fetching data from dotenv
        Dotenv dotenv = Dotenv.load();
        String DB_USER = dotenv.get("DB_USER");
        String DB_PASSWORD = dotenv.get("DB_PASSWORD");
  
        // Creating a Mongo client and connection string
        MongoClientURI uri = new MongoClientURI(
           "mongodb+srv://"+DB_USER+":"+DB_PASSWORD+"@cluster0.xsgtf.mongodb.net/testDatabase?retryWrites=true&w=majority");
        MongoClient mongoClient = new MongoClient(uri); 
        
        // Accessing the database     
        MongoDatabase database = mongoClient.getDatabase("testDatabase");
  
        // Accessing the collection
        MongoCollection<Document> collection = database.getCollection("testCollection");
        
        // call the insert function
        insertData(collection);
  
        // close connnection
        mongoClient.close();    
    }

    private static void insertData(MongoCollection<Document> collection){
        // query to be inserted
        Document query = new Document("_id", new ObjectId());
        query.append("user_name", "vishnu")
                .append("user_password", "hehe")
                .append("collection", java.util.Arrays.asList(
                    new Document("name", "Google").append("account_id", "hehe@hhe.com").append("account_password", "gjkdfyhg"),
                    new Document("name", "Yahooooo").append("account_id", "haha@hha.com").append("account_password", "dkfjhgfg")
                    ));
        
        collection.insertOne(query);
        System.out.println("Insert successful");
    }
}