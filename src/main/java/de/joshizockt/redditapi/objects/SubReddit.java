package de.joshizockt.redditapi.objects;

public class SubReddit {

    private final String name;
    private final String id;
    private final boolean isPublic;

    public SubReddit(String name, String id) { this(name, id, true); }
    public SubReddit(String name, String id, boolean isPublic) {
        this.name = name;
        this.id = id;
        this.isPublic = isPublic;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPrefixedName() {
        return "r/" + name;
    }

    public boolean isPublic() {
        return isPublic;
    }

}
