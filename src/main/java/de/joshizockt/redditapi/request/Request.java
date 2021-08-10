package de.joshizockt.redditapi.request;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.joshizockt.redditapi.RestAction;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public abstract class Request<T> {

    public abstract String getPath();
    public abstract RestAction<T> execute();

    public RestAction<JsonObject> makeCall() {
        return new RestAction<JsonObject>().setContext(() -> makeCall(Statics.REDDIT_BASE_URL));
    }

    public JsonObject makeCall(String host) throws IOException {
        URL url = new URL(host + getPath());
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("User-Agent", Statics.USER_AGENT);
        return JsonParser.parseReader(new InputStreamReader((InputStream) con.getContent())).getAsJsonObject();
    }

}
