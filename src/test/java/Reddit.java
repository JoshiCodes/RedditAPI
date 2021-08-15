import de.joshizockt.redditapi.objects.RedditPost;
import de.joshizockt.redditapi.request.Statics;
import de.joshizockt.redditapi.request.SubredditHotRequest;
import de.joshizockt.redditapi.request.SubredditRandomPostRequest;

import java.util.List;

public class Reddit {

    public static void main(String[] args) throws Exception {
        RedditPost post = new SubredditRandomPostRequest("memes").execute().complete();
        System.out.println(post.getUrl());
        System.out.println(post.getSubreddit().isPublic() + " / " + post.getSubreddit().getId());
    }

    public Reddit() {

    }

}
