package by.matusevich.xmlparser.parser.sax;

import by.matusevich.xmlparser.entity.Candy;
import by.matusevich.xmlparser.entity.CandyType;
import by.matusevich.xmlparser.entity.Ingredients;
import by.matusevich.xmlparser.entity.ValueSweet;
import by.matusevich.xmlparser.parser.ParserHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CandyHandler extends DefaultHandler {
    private static final Logger LOGGER = LogManager.getLogger();
    private Set<Candy> candySet;
    private Candy current;
    private ParserHelper currentTag;
    private static final int ID_INDEX = 0;
    private static final int TYPE_INDEX = 1;
    private static final String DATE_FORMAT = "yyyy-mm-dd";
    private static final int COUNT_ATTRIBUTES = 2;

    public CandyHandler() {
        candySet = new HashSet<>();
    }

    public Set<Candy> getCandies() {
        return candySet;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (ParserHelper.CANDY.getTag().equals(localName)) {
            current = new Candy();
            current.setId(attributes.getValue(ID_INDEX));
            if (attributes.getLength() == COUNT_ATTRIBUTES) {
                current.setCandyType(CandyType.getCandyTypeByValue(attributes.getValue(TYPE_INDEX)));
            }else {
                current.setCandyType(CandyType.DEFAULT_TYPE);
            }
        } else {
            currentTag = ParserHelper.getTagByValue(localName);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (ParserHelper.CANDY.getTag().equals(localName)) {
            candySet.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException { //TODO
        String value = new String(ch, start, length).trim();
        if (currentTag != null) {
            switch (currentTag) {
                case CANDIES:
                    break;
                case NAME:
                    current.setName(value);
                    break;
                case ENERGY:
                    current.setEnergy(Integer.valueOf(value));
                    break;
                case INGREDIENTS:
                    current.setIngredients(new Ingredients());
                    break;
                case WATER:
                    current.getIngredients().setGramOfWater(Integer.parseInt(value));
                    break;
                case SUGAR:
                    current.getIngredients().setGramOfSugar(Integer.parseInt(value));
                    break;
                case FRUCTOSE:
                    current.getIngredients().setGramOfFructose(Integer.parseInt(value));
                    break;
                case VALUE:
                    current.setValueSweet(new ValueSweet());
                    break;
                case PROTEINS:
                    current.getValueSweet().setProteins(Double.parseDouble(value));
                    break;
                case FATS:
                    current.getValueSweet().setFats(Double.parseDouble(value));
                    break;
                case CARBOHYDRATES:
                    current.getValueSweet().setCarbohydrates(Double.parseDouble(value));
                    break;
                case PRODUCTION:
                    current.setProduction(value);
                    break;
                case DATE:
                    Date date = null;
                    SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
                    try {
                        date = formatter.parse(value);
                    } catch (ParseException e) {
                        LOGGER.error("error");
                    }
                    current.setDate(date);
                    break;
                default:
                    throw new SAXException("Unexpected tag");
            }
            currentTag = null;
        }

    }
}