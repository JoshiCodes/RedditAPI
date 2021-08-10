import de.joshizockt.redditapi.objects.RedditPost;
import de.joshizockt.redditapi.request.Statics;
import de.joshizockt.redditapi.request.SubredditHotRequest;

import java.util.List;

public class Reddit {

    public static void main(String[] args) throws Exception {
        for(RedditPost post : new SubredditHotRequest("memes", 10).execute().complete()) {
            System.out.println(post.getInfo().getTitle());
        }
    }

    public Reddit() {

    }

}
