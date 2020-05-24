package by.matusevich.xmlparser.parser.dom;

import by.matusevich.xmlparser.entity.Candy;
import by.matusevich.xmlparser.entity.CandyType;
import by.matusevich.xmlparser.entity.Ingredients;
import by.matusevich.xmlparser.entity.ValueSweet;
import by.matusevich.xmlparser.parser.ParserHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CandyDOMParser {
    private Set<Candy> candies;
    private DocumentBuilder documentBuilder;
    private static final String DATE_FORMAT = "yyyy-mm-dd";
    private static final SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
    private static final Logger LOGGER = LogManager.getLogger();

    public CandyDOMParser(){
        this.candies = new HashSet<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public Set<Candy> getCandies() {
        return candies;
    }

    public void buildCandies(String fileName) {
        Document document;
        File file = new File(fileName);
        try {
            document = documentBuilder.parse(file);
            Element root = document.getDocumentElement();
            NodeList candyList = root.getElementsByTagName(ParserHelper.CANDY.getTag());
            for (int i = 0; i < candyList.getLength(); i++) {
                Element candyElement = (Element) candyList.item(i);
                Candy candy = buildCandy(candyElement);
                candies.add(candy);
            }
        } catch (SAXException e) {
            LOGGER.error("error");
        } catch (IOException e) {
            LOGGER.error("error");
        }
    }

    private Candy buildCandy(Element element) {
        Candy candy = new Candy();
        candy.setId(element.getAttribute(ParserHelper.ID.getTag()));
        candy.setCandyType(CandyType.getCandyTypeByValue(element.getAttribute(ParserHelper.TYPE.getTag())));
        candy.setName(getElementTextContent(element, ParserHelper.NAME.getTag()));
        Integer energy = Integer.parseInt(getElementTextContent(element, ParserHelper.ENERGY.getTag()));
        candy.setEnergy(energy);
        Ingredients ingredients = new Ingredients();
        Element ingredientsElement = (Element) element.getElementsByTagName(ParserHelper.INGREDIENTS.getTag()).item(0);
        ingredients.setGramOfWater(Integer.parseInt(getElementTextContent(ingredientsElement, ParserHelper.WATER.getTag())));
        ingredients.setGramOfSugar(Integer.parseInt(getElementTextContent(ingredientsElement, ParserHelper.SUGAR.getTag())));
        ingredients.setGramOfFructose(Integer.parseInt(getElementTextContent(ingredientsElement, ParserHelper.FRUCTOSE.getTag())));
        candy.setIngredients(ingredients);

        ValueSweet valueSweet = new ValueSweet();
        Element valueSweetElement = (Element) element.getElementsByTagName(ParserHelper.VALUE.getTag()).item(0);
        valueSweet.setProteins(Double.parseDouble(getElementTextContent(valueSweetElement, ParserHelper.PROTEINS.getTag())));
        valueSweet.setFats(Double.parseDouble(getElementTextContent(valueSweetElement, ParserHelper.FATS.getTag())));
        valueSweet.setCarbohydrates(Double.parseDouble(getElementTextContent(valueSweetElement, ParserHelper.CARBOHYDRATES.getTag())));
        candy.setValueSweet(valueSweet);
        candy.setProduction(getElementTextContent(element, ParserHelper.PRODUCTION.getTag()));

        Date date = null;
        try {
            date = formatter.parse(getElementTextContent(element, ParserHelper.DATE.getTag()));
        } catch (ParseException e) {
            LOGGER.error("error");
        }
        candy.setDate(date);

        return candy;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }

}