package de.joshizockt.redditapi.objects;

import com.google.gson.JsonObject;

import java.net.URL;
import java.util.List;

public interface RedditPost {

    SubReddit getSubreddit();
    PostInfo getInfo();
    boolean pinned();
    List<URL> getMediaUrls();
    String getPermaLink();
    boolean isVideo();

    JsonObject json();

}
