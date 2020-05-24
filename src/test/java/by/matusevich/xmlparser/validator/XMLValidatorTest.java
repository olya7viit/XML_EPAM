package by.matusevich.xmlparser.validator;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class XMLValidatorTest {

    XMLValidator validator;
    String xmlFileName;
    String xsdFileName;
    String incorrectXmlLink;

    @BeforeClass
    public void setUp(){
        validator = new XMLValidator();
        xmlFileName = "src/main/resources/data/candies.xml";
        xsdFileName = "src/main/resources/data/candies.xsd";
    }

    @Test
    public void validateXMLFileTest(){
        boolean actual = validator.validateXmlFile(xmlFileName,xsdFileName);
        Assert.assertTrue(actual);
    }
}