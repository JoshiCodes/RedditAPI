package de.joshizockt.redditapi.objects;

public interface PostInfo {

    Author getAuthor();
    String getTitle();
    int getUpvotes();
    int getDownvotes();
    int getComments();
    int getScore();
    boolean isNsfw();

}
