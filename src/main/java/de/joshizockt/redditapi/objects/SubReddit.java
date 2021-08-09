package de.joshizockt.redditapi.objects;

public class SubReddit {

    private final String name;

    public SubReddit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getPrefixedName() {
        return "r/" + name;
    }

}
