package by.matusevich.xmlparser.entity;

public class ValueSweet {
    private double proteins;
    private double fats;
    private double carbohydrates;

    public ValueSweet(){}

    public ValueSweet(double proteins, double fats, double carbohydrates) {
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("ValueSweet{")
                .append("proteins=").append(proteins)
                .append(", fats=").append(fats).append(", carbohydrates=")
                .append(carbohydrates).append('}').toString();
    }
}
