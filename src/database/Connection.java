package database;

import com.mongodb.MongoClientURI;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Connection {

    protected MongoCollection<Document> collection;
    protected MongoClient mongoClient;

    Connection() {  
      
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
        mongoClient = new MongoClient(uri); 
        
        // Accessing the database     
        MongoDatabase database = mongoClient.getDatabase("passKeeper");
  
        // Accessing the collection
        collection = database.getCollection("userData");  
    }

    protected void closeConnection(){
        mongoClient.close();  
    }
    
}