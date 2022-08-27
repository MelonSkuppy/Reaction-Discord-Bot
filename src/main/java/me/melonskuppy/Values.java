package me.melonskuppy;

public enum Values {

    // DEFAULT
    GUILD("Guild", "999262183097630830"),
    MESSAGE_CHANNEL("Message Channel", "1013031023887515668"),

    // Colours
    RED("Red", "999637871545823323"),
    PURPLE("Purple", "999637872002998302"),
    GREEN("Green", "999637872460189776"),
    PINK("Pink", "999637872908976178"),
    ORANGE("Orange", "999637873018015795"),
    YELLOW("Yellow", "999637874070790275"),
    BLUE("Blue", "999637874511187998"),

    // Pronouns
    HE_HIM("He/Him", "999268342575681596"),
    SHE_HER("She/Her", "999268300913643621"),
    HE_THEY("He/They", "999268689574641756"),
    SHE_THEY("She/They", "999268723674333194"),
    THEY_THEM("They/Them", "999268762182238278"),
    ANY("Any pronouns", "999268800367173672"),

    // Pings
    ANNOUNCEMENTS("Announcements", "1002963343738408960"),
    GIVEAWAYS("Giveaways", "1002963407143702579"),

    // Others
    DANK_ACCESS("Dank Grinding access", "1001108236277121126");

    private final String name;
    private final String id;

    Values(String name, String id) {

        this.name = name;
        this.id = id;

    }

    public String getName() { return name; }
    public String getId() { return id; }

}
