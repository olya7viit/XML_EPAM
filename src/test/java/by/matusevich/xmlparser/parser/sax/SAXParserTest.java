package by.matusevich.xmlparser.parser.sax;

import org.testng.annotations.Test;

public class SAXParserTest {

    @Test
    public void buildCandiesTest() {
        SAXParser parser = new SAXParser();
        parser.buildCandies("src/main/resources/data/candies.xml");
        Object[] candies = parser.getCandies().toArray();
    }

}
