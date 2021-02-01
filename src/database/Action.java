package database;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import token.Token;

import static com.mongodb.client.model.Updates.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

// user defined data structure
class userData {
    public String account_id;
    public String account_password;
    public String app_name;

    userData(String account_id, String account_password) {
        this.account_id = account_id;
        this.account_password = account_password;
    }

    userData(String account_id, String account_password, String app_name) {
        this.account_id = account_id;
        this.account_password = account_password;
        this.app_name = app_name;
    }
}

class userLogin {
    public String id;
    public String user_name;
    public String user_password;

    userLogin(String user_name, String user_password) {
        this.user_name = user_name;
        this.user_password = user_password;
    }
}

public class Action extends Connection {

    public Action() {
        super();
    }

    public void createUser(String username, String password) {
        // query to be inserted
        Document query = new Document("_id", new ObjectId());
        query.append("user_name", username).append("user_password", password);

        collection.insertOne(query);
        System.out.println("Done...welcome!!😎");
        closeConnection();
    }

    // append a new entry to the collections array in document
    public void addNewItem(String accountNmme, String id, String password) {
        Document filter = null;
        try {
            filter = new Document("_id", new ObjectId(new Token().getId()));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        collection.updateOne(filter, push("collections",
                new Document("name", accountNmme).append("account_id", id).append("account_password", password)));
        System.out.println("👍👍");
        closeConnection();
    }

    // retrieve data
    public void view(String accountName) {
        DB db = mongoClient.getDB("passKeeper");
        DBCollection coll = db.getCollection("userData");

        BasicDBObject viewData = new BasicDBObject();
        BasicDBObject field = new BasicDBObject();

        // condition
        try {
            viewData.put("_id", new ObjectId(new Token().getId()));
        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        // fields to retrieve
        field.put("_id", 0);
        field.put("collections", 1);

        List<userData> uData = new ArrayList<userData>();

        DBCursor cursor = coll.find(viewData, field);

        // loop through retrieved data
        while (cursor.hasNext()) {
            BasicDBObject obj = (BasicDBObject) cursor.next();
            JSONParser parser = new JSONParser();
            JSONArray json;

            // parse retrieved data to JSON array
            try {
                json = (JSONArray) parser.parse(obj.getString("collections"));
                for (int i = 0; i < json.size(); i++) {
                    JSONObject jsonObj = (JSONObject) json.get(i);

                    // create an array of objects containing user data
                    if (jsonObj.get("name").equals(accountName)) {
                        uData.add(new userData(jsonObj.get("account_id").toString(),
                                jsonObj.get("account_password").toString()));
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        // user selection
        System.out.println("Make a selection...");
        int count = 1;
        for (userData ud : uData) {
            System.out.println(count + ". " + ud.account_id);
            count++;
        }

        Scanner scanner = new Scanner(System.in);
        int ch = scanner.nextInt();
        scanner.close();

        // copy selected entry to clicpboard
        if (ch > 0 && ch <= uData.size()) {
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Clipboard clipboard = toolkit.getSystemClipboard();
            StringSelection strSel = new StringSelection(uData.get(ch - 1).account_password);
            clipboard.setContents(strSel, null);
            System.out.println("Copied to clipboard!!✅");
        } else {
            System.out.println("Incorrect selection!!🤐");
        }
    }

    public void listAll() {
        DB db = mongoClient.getDB("passKeeper");
        DBCollection coll = db.getCollection("userData");

        BasicDBObject viewData = new BasicDBObject();
        BasicDBObject field = new BasicDBObject();

        // condition
        try {
            viewData.put("_id", new ObjectId(new Token().getId()));
        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        // fields to retrieve
        field.put("_id", 0);
        field.put("collections", 1);

        List<userData> uData = new ArrayList<userData>();

        DBCursor cursor = coll.find(viewData, field);

        // loop through retrieved data
        while (cursor.hasNext()) {
            BasicDBObject obj = (BasicDBObject) cursor.next();
            JSONParser parser = new JSONParser();
            JSONArray json;

            // parse retrieved data to JSON array
            try {
                json = (JSONArray) parser.parse(obj.getString("collections"));
                for (int i = 0; i < json.size(); i++) {
                    JSONObject jsonObj = (JSONObject) json.get(i);
                    uData.add(new userData(jsonObj.get("account_id").toString(),
                            jsonObj.get("account_password").toString(), jsonObj.get("name").toString()));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            int count = 1;
            System.out.println("Here's list 👇");
            for (userData ud : uData) {
                System.out.println(count + ". " + ud.app_name + " - " + ud.account_id);
                count++;
            }

        }
    }

    // login function
    public void login(String username, String password) {
        DB db = mongoClient.getDB("passKeeper");
        DBCollection coll = db.getCollection("userData");

        BasicDBObject viewData = new BasicDBObject();
        BasicDBObject field = new BasicDBObject();

        // condition
        field.put("user_name", 1);
        field.put("_id", 1);
        field.put("user_password", 1);

        DBCursor cursor = coll.find(viewData, field);
        while (cursor.hasNext()) {
            BasicDBObject obj = (BasicDBObject) cursor.next();

            if (username.equals(obj.getString("user_name")) && password.equals(obj.getString("user_password"))) {
                try {
                    new Token().generate(obj.getString("_id"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
