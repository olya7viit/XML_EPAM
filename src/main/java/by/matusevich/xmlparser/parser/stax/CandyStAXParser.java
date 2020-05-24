package by.matusevich.xmlparser.parser.stax;

import by.matusevich.xmlparser.entity.Candy;
import by.matusevich.xmlparser.entity.CandyType;
import by.matusevich.xmlparser.entity.Ingredients;
import by.matusevich.xmlparser.entity.ValueSweet;
import by.matusevich.xmlparser.parser.ParserHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class CandyStAXParser {
  private static final String DATE_FORMAT = "yyyy-mm-dd";
  private HashSet<Candy> candies = new HashSet<>();
  private XMLInputFactory inputFactory;
  private static final Logger LOGGER = LogManager.getLogger();

  public CandyStAXParser() {
    inputFactory = XMLInputFactory.newInstance();
  }

  public Set<Candy> getCandies() {
    return candies;
  }

  public void buildCandies(String fileName) {
    FileInputStream inputStream = null;
    XMLStreamReader reader;
    String name;
    try {
      inputStream = new FileInputStream(new File(fileName));
      reader = inputFactory.createXMLStreamReader(inputStream);
      while (reader.hasNext()) {
        int type = reader.next();
        if (type == XMLStreamConstants.START_ELEMENT) {
          name = reader.getLocalName();
          if (ParserHelper.valueOf(name.toUpperCase()) == ParserHelper.CANDY) {
            Candy candy = buildCandy(reader);
            candies.add(candy);
          }
        }
      }
    } catch (XMLStreamException ex) {
      LOGGER.error("StAX parsing error!");
    } catch (FileNotFoundException ex) {
      LOGGER.error("File " + fileName + " not found! ");
    } finally {
      try {
        if (inputStream != null) {
          inputStream.close();
        }
      } catch (IOException e) {
        LOGGER.error("Impossible close file " + fileName);
      }
    }
  }

  private Candy buildCandy(XMLStreamReader reader) throws XMLStreamException {
    Candy candy = new Candy();
    candy.setId(reader.getAttributeValue(null, ParserHelper.ID.getTag()));
    String candyType = reader.getAttributeValue(null, ParserHelper.TYPE.getTag());
    candy.setCandyType(CandyType.getCandyTypeByValue(candyType));
    String name;
    while (reader.hasNext()) {
      int type = reader.next();
      switch (type) {
        case XMLStreamConstants.START_ELEMENT:
          name = reader.getLocalName();
          switch (ParserHelper.valueOf(name.toUpperCase())) {

            case NAME:
              candy.setName(getXMLText(reader));
              break;
            case ENERGY:
              name = getXMLText(reader);
              candy.setEnergy(Integer.parseInt(name));
              break;
            case INGREDIENTS:
              candy.setIngredients(getXMLIngredients(reader));
              break;
            case VALUE:
              candy.setValueSweet(getXMLValue(reader));
              break;
            case PRODUCTION:
              candy.setProduction(getXMLText(reader));
              break;
            case DATE:
              name = getXMLText(reader);
              Date date = null;
              SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
              try {
                date = formatter.parse(name);
              } catch (ParseException e) {
                LOGGER.error("parser exception");
              }
              candy.setDate(date);
          }
          break;
        case XMLStreamConstants.END_ELEMENT:
          name = reader.getLocalName();
          if (ParserHelper.valueOf(name.toUpperCase()) == ParserHelper.CANDY) {
            return candy;
          }
          break;
      }
    }
    throw new XMLStreamException("Unknown element");
  }

  private Ingredients getXMLIngredients(XMLStreamReader reader) throws XMLStreamException {
    Ingredients ingredients = new Ingredients();
    int type;
    String name;
    while (reader.hasNext()) {
      type = reader.next();
      switch (type) {
        case XMLStreamConstants.START_ELEMENT:
          name = reader.getLocalName();
          switch (ParserHelper.valueOf(name.toUpperCase())) {
            case WATER:
              ingredients.setGramOfWater(Integer.parseInt(getXMLText(reader)));
              break;
            case SUGAR:
              ingredients.setGramOfSugar(Integer.parseInt(getXMLText(reader)));
              break;
            case FRUCTOSE:
              ingredients.setGramOfFructose(Integer.parseInt(getXMLText(reader)));
              break;
          }
          break;
        case XMLStreamConstants.END_ELEMENT:
          name = reader.getLocalName();
          if (ParserHelper.valueOf(name.toUpperCase()) == ParserHelper.INGREDIENTS) {
            return ingredients;
          }
          break;
      }
    }
    throw new XMLStreamException("Unknown element");//TODO
  }

  private ValueSweet getXMLValue(XMLStreamReader reader) throws XMLStreamException {
    ValueSweet valueSweet = new ValueSweet();
    int type;
    String name;
    while (reader.hasNext()) {
      type = reader.next();
      switch (type) {
        case XMLStreamConstants.START_ELEMENT:
          name = reader.getLocalName();
          switch (ParserHelper.valueOf(name.toUpperCase())) {
            case PROTEINS:
              valueSweet.setProteins(Double.parseDouble(getXMLText(reader)));
              break;
            case FATS:
              valueSweet.setFats(Double.parseDouble(getXMLText(reader)));
              break;
            case CARBOHYDRATES:
              valueSweet.setCarbohydrates(Double.parseDouble(getXMLText(reader)));
              break;
          }
          break;
        case XMLStreamConstants.END_ELEMENT:
          name = reader.getLocalName();
          if (ParserHelper.valueOf(name.toUpperCase()) == ParserHelper.VALUE) {
            return valueSweet;
          }
          break;
      }
    }
    throw new XMLStreamException("Unknown element");//TODO
  }

  private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
    String text = null;
    if (reader.hasNext()) {
      reader.next();
      text = reader.getText();
    }
    return text;
  }
}
