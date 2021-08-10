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
    String getUrl();
    default boolean isImage() {
        String str = getUrl();
        if (str == null) {
            return false;
        } else {
            return str.endsWith(".png") || str.endsWith(".jpg") || str.endsWith(".jpeg") || str.endsWith(".gif");
        }
    }

    JsonObject json();

}
