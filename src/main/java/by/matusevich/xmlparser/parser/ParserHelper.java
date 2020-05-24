package by.matusevich.xmlparser.parser;

public enum ParserHelper {
    CANDIES("candies"),
    ID("id"),
    CANDY("candy"),
    TYPE("type"),
    NAME("name"),
    ENERGY("energy"),
    INGREDIENTS("ingredients"),
    WATER("water"),
    SUGAR("sugar"),
    FRUCTOSE("fructose"),
    VALUE("value"),
    PROTEINS("proteins"),
    FATS("fats"),
    CARBOHYDRATES("carbohydrates"),
    PRODUCTION("production"),
    DATE("date");

    private String tag;

    ParserHelper(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public static ParserHelper getTagByValue(String value) {
        ParserHelper[] tags = ParserHelper.values();
        for (ParserHelper tag : tags) {
            if (tag.getTag().equals(value)) {
                return tag;
            }
        }
        throw new IllegalArgumentException();
    }
}