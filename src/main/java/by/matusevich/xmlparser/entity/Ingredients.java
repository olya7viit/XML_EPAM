package by.matusevich.xmlparser.entity;

public class Ingredients {
    private int gramOfWater;
    private int gramOfSugar;
    private int gramOfFructose;

    public Ingredients(){}

    public Ingredients(int gramOfWater, int gramOfSugar, int gramOfFructose) {
        this.gramOfWater = gramOfWater;
        this.gramOfSugar = gramOfSugar;
        this.gramOfFructose = gramOfFructose;
    }

    public int getGramOfWater() {
        return gramOfWater;
    }

    public void setGramOfWater(int gramOfWater) {
        this.gramOfWater = gramOfWater;
    }

    public int getGramOfSugar() {
        return gramOfSugar;
    }

    public void setGramOfSugar(int gramOfSugar) {
        this.gramOfSugar = gramOfSugar;
    }

    public int getGramOfFructose() {
        return gramOfFructose;
    }

    public void setGramOfFructose(int gramOfFructose) {
        this.gramOfFructose = gramOfFructose;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Ingredients{")
                .append("gramOfWater=").append(gramOfWater)
                .append(", gramOfSugar=").append(gramOfSugar)
                .append(", gramOfFructose=").append(gramOfFructose)
                .append('}').toString();
    }
}
