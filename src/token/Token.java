package token;

import java.io.FileReader;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Token {
    public JSONObject jwtPayload = new JSONObject();
    private String id;

    public Token() throws JSONException {
    }

    public void generate(String id) {
        this.id = id;
        final int EXPIRY_DAYS = 2;

        try {
            this.jwtPayload.put("status", 0);
        } catch (JSONException e1) {

            e1.printStackTrace();
        }

        try {
            this.jwtPayload.put("id", this.id);
        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        LocalDateTime ldt = LocalDateTime.now().plusDays(EXPIRY_DAYS);
        try {
            this.jwtPayload.put("exp", ldt.toEpochSecond(ZoneOffset.UTC));
        } catch (JSONException e1) {
            e1.printStackTrace();
        } // this needs to be

        String token = "";
        try {
            token = new JWebToken(this.jwtPayload).toString();
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        JSONObject jo = new JSONObject();
        try {
            jo.put("token", token);
        } catch (JSONException e1) {
            e1.printStackTrace();
        }

        try {
            PrintWriter pw = new PrintWriter("isLogin.json");
            pw.write(jo.toString());
            pw.flush();
            pw.close();
        } catch (Exception e) {// Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }

    }

    public String getId() {
        String token = "";
        try {
            JSONTokener obj = new JSONTokener(new FileReader("isLogin.json"));
            token = new JSONObject(obj).getString("token");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }


        JWebToken incomingToken;
        try {
            incomingToken = new JWebToken(token);
            if (incomingToken.isValid()) {
                return incomingToken.getId();
            }
        } catch (NoSuchAlgorithmException | JSONException e) {
            e.printStackTrace();
        }

        return token;
    }

}
