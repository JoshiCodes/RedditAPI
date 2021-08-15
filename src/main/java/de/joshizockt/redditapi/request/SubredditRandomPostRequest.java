package de.joshizockt.redditapi.request;

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
                JsonObject e = json.getAsJsonArray().get(0).getAsJsonObject().get("data").getAsJsonObject().get("children").getAsJsonArray().get(0).getAsJsonObject().get("data").getAsJsonObject();
                Author author = new Author() {
                    @Override
                    public String getName() {
                        return e.get("author_fullname").getAsString();
                    }
                };
                PostInfo postInfo = new PostInfo() {
                    @Override
                    public Author getAuthor() {
                        return author;
                    }

                    @Override
                    public String getTitle() {
                        return e.get("title").getAsString();
                    }

                    @Override
                    public int getUpvotes() {
                        return e.get("ups").getAsInt();
                    }

                    @Override
                    public int getDownvotes() {
                        return e.get("downs").getAsInt();
                    }

                    @Override
                    public int getComments() {
                        return e.get("num_comments").getAsInt();
                    }

                    @Override
                    public int getScore() {
                        return e.get("score").getAsInt();
                    }

                    @Override
                    public boolean isNsfw() {
                        return e.get("over_18").getAsBoolean();
                    }

                };
            return new RedditPost() {
                @Override
                public SubReddit getSubreddit() {
                    return new SubReddit(e.get("subreddit").getAsString(), e.get("subreddit_id").getAsString(), e.get("subreddit_type").getAsString().equals("public"));
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
                    return e.get("permalink").getAsString();
                }

                @Override
                public boolean isVideo() {
                    return false;
                }

                @Override
                public String getUrl() {
                    return e.get("url_overridden_by_dest").getAsString();
                }

                @Override
                public JsonObject json() {
                    return e;
                }

            };
        });
        return action;
    }

}
