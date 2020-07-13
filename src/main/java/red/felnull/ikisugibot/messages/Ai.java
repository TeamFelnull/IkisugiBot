package red.felnull.ikisugibot.messages;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Ai {
    public static void init() {

    }

    public static String getResbone(String message) {
        String st = null;
        String data = "apikey=DZZz4KCWUPsoi8tgmG4RKL9K4ghAP2Br&query=" + message;
        HttpURLConnection conn = null;
        try {
            URL url = new URL("https://api.a3rt.recruit-tech.co.jp/talk/v1/smalltalk");

            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setUseCaches(false);

            try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
                dos.writeBytes(data);
            }
            int rescode = conn.getResponseCode();
            if (rescode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                    StringBuilder buf = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        buf.append(line);
                    }
                    st = convertToOiginal(buf.toString());
                }
            }

        } catch (Exception e) {
            st += ":" + e.getLocalizedMessage();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        return st;
    }

    private static String convertToOiginal(String unicode) {
        JsonObject jsonobject = new Gson().fromJson(unicode, JsonObject.class);
        JsonElement results = jsonobject.get("results").getAsJsonArray().get(0);
        JsonObject sa = new Gson().fromJson(results, JsonObject.class);

        return sa.get("reply").getAsString();
    }

}
