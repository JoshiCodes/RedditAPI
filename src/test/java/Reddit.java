import de.joshizockt.redditapi.objects.RedditPost;
import de.joshizockt.redditapi.request.Statics;
import de.joshizockt.redditapi.request.SubredditHotRequest;

import java.util.List;

public class Reddit {

    public static void main(String[] args) throws Exception {
        /*new RequestMaker(Statics.REDDIT_BASE_URL).doRequest(new SubredditHotRequest("memes", 100), response -> {
            System.out.println(response.jsonString());
        }, error -> {
            System.out.println("Error");
        });*/
        for(RedditPost post : new SubredditHotRequest("memes", 10).complete().complete()) {
            System.out.println(post.getInfo().getTitle());
        }
    }

    public Reddit() {

    }

}
