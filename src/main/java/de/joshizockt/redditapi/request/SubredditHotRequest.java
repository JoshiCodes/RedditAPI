package de.joshizockt.redditapi.request;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import de.joshizockt.redditapi.RestAction;
import de.joshizockt.redditapi.objects.Author;
import de.joshizockt.redditapi.objects.PostInfo;
import de.joshizockt.redditapi.objects.RedditPost;
import de.joshizockt.redditapi.objects.SubReddit;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SubredditHotRequest extends Request<List<RedditPost>> {

    private String subreddit;
    private int amount;

    public SubredditHotRequest(SubReddit subReddit, int amount) {
        this(subReddit.getName(), amount);
    }

    public SubredditHotRequest(String subreddit, int amount) {
        this.subreddit = subreddit;
        this.amount = amount;
    }

    public String getPath() {
        return subreddit + "/hot/.json?count=" + amount;
    }

    @Override
    public RestAction<List<RedditPost>> execute() {
        JsonObject json = makeCall().complete(Throwable::printStackTrace);
        RestAction<List<RedditPost>> action = new RestAction<>();
        action.setContext(() -> {
            List<RedditPost> list = new ArrayList<>();
            for(JsonElement o : json.get("data").getAsJsonObject().getAsJsonArray("children")) {
                JsonObject e = o.getAsJsonObject().get("data").getAsJsonObject();
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
                RedditPost post = new RedditPost() {
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
                        return e.get("url").getAsString();
                    }

                    @Override
                    public JsonObject json() {
                        return e;
                    }

                };
                list.add(post);
            }
            return list;
        });
        return action;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public int getAmount() {
        return amount;
    }

}
