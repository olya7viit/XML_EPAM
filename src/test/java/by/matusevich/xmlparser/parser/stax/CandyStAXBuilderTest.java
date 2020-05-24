package by.matusevich.xmlparser.parser.stax;

import org.testng.annotations.Test;

public class CandyStAXBuilderTest {

    @Test
    public void buildCandiesTest() {
        CandyStAXParser parser = new CandyStAXParser();
        parser.buildCandies("src/main/resources/data/candies.xml");
        Object[] candies = parser.getCandies().toArray();
    }

}
