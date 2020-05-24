package by.matusevich.xmlparser.parser.dom;

import org.testng.annotations.Test;

public class CandyDOMParserTest {

    @Test
    public void buildCandiesTest(){

        CandyDOMParser parser = new CandyDOMParser();
        parser.buildCandies("src/main/resources/data/candies.xml");
        Object[] candies = parser.getCandies().toArray();
    }
}
