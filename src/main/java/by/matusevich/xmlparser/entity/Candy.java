package by.matusevich.xmlparser.entity;

import java.util.Date;

public class Candy {
    private String id;
    private CandyType candyType;
    private String name;
    private int energy;
    private Ingredients ingredients;
    private ValueSweet valueSweet;
    private String production;
    private Date date;

    public Candy(){}

    public Candy(String id, CandyType candyType, String name, int energy, Ingredients ingredients, ValueSweet valueSweet, String production, Date date) {
        this.id = id;
        this.candyType = candyType;
        this.name = name;
        this.energy = energy;
        this.ingredients = ingredients;
        this.valueSweet = valueSweet;
        this.production = production;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CandyType getCandyType() {
        return candyType;
    }

    public void setCandyType(CandyType candyType) {
        this.candyType = candyType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
    }

    public ValueSweet getValueSweet() {
        return valueSweet;
    }

    public void setValueSweet(ValueSweet valueSweet) {
        this.valueSweet = valueSweet;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Candy{")
                .append("id='").append(id).append('\'')
                .append(", candyType=").append(candyType)
                .append(", name='").append(name).append('\'')
                .append(", energy=").append(energy)
                .append(", ingredients=").append(ingredients)
                .append(", valueSweet=").append(valueSweet)
                .append(", production='").append(production).append('\'')
                .append(", date=").append(date).append('}').toString();
    }
}
