package red.felnull.ikisugibot.command;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import red.felnull.ikisugibot.util.DiscordUtil;

import java.io.IOException;

public class CoronaCommand extends Command {
    @Override
    public void start(long chanelID, String[] attackd) throws IOException {

        String contry = "japan";

        if (attackd[0] != null) {
            contry = attackd[0];
        }
        OkHttpClient client = new OkHttpClient();
        Gson gson = new Gson();
        Request carequest = new Request.Builder().url("https://covid-193.p.rapidapi.com/statistics?country=" + contry).get().addHeader("x-rapidapi-host", "covid-193.p.rapidapi.com").addHeader("x-rapidapi-key", "ba8971e351msh4b81c761ffd88c5p18ebb3jsn43949b084fab").build();

        try {
            Response caresponse = client.newCall(carequest).execute();
            String st = caresponse.body().string();
            JsonObject jsonobject = new Gson().fromJson(st, JsonObject.class);
            String results = jsonobject.get("results").getAsString();
            if (results.equals("0")) {
                DiscordUtil.sendMessage(chanelID, contry + "は存在しないかデーターがありません");
                return;
            }

        } catch (Exception ex) {
            String st = "エラーが発生しました:";
            st += ex.getLocalizedMessage();
            DiscordUtil.sendMessage(chanelID, st);
            return;
        }

        Request request = new Request.Builder().url("https://covid-193.p.rapidapi.com/statistics?country=" + contry).get().addHeader("x-rapidapi-host", "covid-193.p.rapidapi.com").addHeader("x-rapidapi-key", "ba8971e351msh4b81c761ffd88c5p18ebb3jsn43949b084fab").build();

        Response response = client.newCall(request).execute();
        String st = response.body().string();

        JsonObject jsonobject = new Gson().fromJson(st, JsonObject.class);
        JsonArray responsed = jsonobject.get("response").getAsJsonArray();
        JsonObject responsedd = responsed.get(0).getAsJsonObject();
        String day = getNonNullString(responsedd, "day");
        String country = getNonNullString(responsedd, "country");
        JsonObject cases = responsedd.get("cases").getAsJsonObject();
        String newd = getNonNullString(cases, "new");
        String active = getNonNullString(cases, "active");
        String recovered = getNonNullString(cases, "recovered");
        String onem_pop = getNonNullString(cases, "1M_pop");
        String total = getNonNullString(cases, "total");
        JsonObject deaths = responsedd.get("deaths").getAsJsonObject();
        String dnew = getNonNullString(deaths, "new");
        String donem_pop = getNonNullString(deaths, "1M_pop");
        String dtotal = getNonNullString(deaths, "total");
        JsonObject tests = responsedd.get("tests").getAsJsonObject();
        String tonem_pop = getNonNullString(tests, "1M_pop");
        String ttotal = getNonNullString(tests, "total");

        String text = day + "での" + country + "のコロナウイルス統計情報は以下の通りです";
        text += "\n感染者数: " + newd;
        text += "\n現在患者数: " + active;
        text += "\n累積退院者: " + recovered;
        text += "\n百万人あたりの感染者数: " + onem_pop;
        text += "\n合計感染者数: " + total;
        text += "\n死亡者数: " + dnew;
        text += "\n百万人あたりの死亡者数: " + donem_pop;
        text += "\n合計死亡者数: " + dtotal;
        text += "\n百万人あたりの検査済み人数: " + tonem_pop;
        text += "\n合計検査済み人数: " + ttotal;

        DiscordUtil.sendMessage(chanelID, text);


    }

    public String getNonNullString(JsonObject obj, String st) {

        if (obj.get(st).isJsonNull()) {
            return "データーが存在しません";
        }

        if (st.equals("country") || st.equals("day") || st.equals("day")) {
            return obj.get(st).getAsString();
        }

        return obj.get(st).getAsString() + "人";
    }

    @Override
    public String getExplanatory() {
        return "最新のコロナウイルス統計情報を表示します";
    }
}
