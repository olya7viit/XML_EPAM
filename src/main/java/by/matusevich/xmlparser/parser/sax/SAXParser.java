package by.matusevich.xmlparser.parser.sax;

import by.matusevich.xmlparser.entity.Candy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

public class SAXParser {
    private Set<Candy> candies;
    private CandyHandler handler;
    private XMLReader reader;
    private static final Logger LOG = LogManager.getLogger();

    public SAXParser(){
        handler = new CandyHandler();
        candies = new HashSet<>();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        } catch (SAXException e) {
            LOG.error(e);
        }
    }

    public Set<Candy> getCandies() {
        return candies;
    }

    public void buildCandies(String fileName){
        try {
            reader.parse(fileName);
            //reader.parse(new InputSource(file));
            candies.addAll(handler.getCandies());
        } catch (IOException | SAXException e) {
            LOG.error(e);
        }
    }
}