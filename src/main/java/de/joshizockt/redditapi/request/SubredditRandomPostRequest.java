package de.joshizockt.redditapi.request;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import de.joshizockt.redditapi.RestAction;
import de.joshizockt.redditapi.objects.Author;
import de.joshizockt.redditapi.objects.PostInfo;
import de.joshizockt.redditapi.objects.RedditPost;
import de.joshizockt.redditapi.objects.SubReddit;

import java.net.URL;
import java.util.List;

public class SubredditRandomPostRequest extends Request<RedditPost> {

    private final String SUBREDDIT;

    public SubredditRandomPostRequest(String subreddit) {
        this.SUBREDDIT = subreddit;
    }

    @Override
    public String getPath() {
        return SUBREDDIT + "/random/.json";
    }

    @Override
    public RestAction<RedditPost> execute() {
        JsonElement json = makeCallAsElement().complete(Throwable::printStackTrace);
        RestAction<RedditPost> action = new RestAction<>();
        action.setContext(() -> {
                JsonObject e;

                if(json instanceof JsonArray) {
                    e = json.getAsJsonArray().get(0).getAsJsonObject();
                } else {
                    e = json.getAsJsonObject();
                }

                e = e.get("data").getAsJsonObject().get("children").getAsJsonArray().get(0).getAsJsonObject().get("data").getAsJsonObject();

                final JsonObject object = e;

                Author author = new Author() {
                    @Override
                    public String getName() {
                        return object.get("author_fullname").getAsString();
                    }
                };
                PostInfo postInfo = new PostInfo() {
                    @Override
                    public Author getAuthor() {
                        return author;
                    }

                    @Override
                    public String getTitle() {
                        return object.get("title").getAsString();
                    }

                    @Override
                    public int getUpvotes() {
                        return object.get("ups").getAsInt();
                    }

                    @Override
                    public int getDownvotes() {
                        return object.get("downs").getAsInt();
                    }

                    @Override
                    public int getComments() {
                        return object.get("num_comments").getAsInt();
                    }

                    @Override
                    public int getScore() {
                        return object.get("score").getAsInt();
                    }

                    @Override
                    public boolean isNsfw() {
                        return object.get("over_18").getAsBoolean();
                    }

                };
            return new RedditPost() {
                @Override
                public SubReddit getSubreddit() {
                    return new SubReddit(object.get("subreddit").getAsString(), object.get("subreddit_id").getAsString(), object.get("subreddit_type").getAsString().equals("public"));
                }

                @Override
                public PostInfo getInfo() {
                    return postInfo;
                }

                @Override
                public boolean pinned() {
                    return false;
                }

                @Override
                public List<URL> getMediaUrls() {
                    return null;
                }

                @Override
                public String getPermaLink() {
                    return object.get("permalink").getAsString();
                }

                @Override
                public boolean isVideo() {
                    return false;
                }

                @Override
                public String getUrl() {
                    return object.get("url_overridden_by_dest").getAsString();
                }

                @Override
                public JsonObject json() {
                    return object;
                }

            };
        });
        return action;
    }

}
