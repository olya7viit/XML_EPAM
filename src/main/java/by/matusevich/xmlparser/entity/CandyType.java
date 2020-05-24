package by.matusevich.xmlparser.entity;

public enum  CandyType {
    JELLY("желейная"),
    CHOCOLATE("шоколадная"),
    CARAMEL("карамель"),
    IRIS("ирис"),
    DEFAULT_TYPE("шоколадная");

    private String candyType;

    CandyType(String candyType){
        this.candyType = candyType;
    }

    public String getTouristVoucherType() {
        return candyType;
    }

    public static CandyType getCandyTypeByValue(String value) {
        CandyType[] candyTypes = CandyType.values();
        for (CandyType touristVoucherType : candyTypes) {
            if(touristVoucherType.getTouristVoucherType().equals(value)){
                return touristVoucherType;
            }
        }
        throw new IllegalArgumentException();
    }
}
