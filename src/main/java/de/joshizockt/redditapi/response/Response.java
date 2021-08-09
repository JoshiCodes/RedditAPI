package de.joshizockt.redditapi.response;

import com.google.gson.JsonObject;

public interface Response {

    String jsonString();
    JsonObject json();

}
