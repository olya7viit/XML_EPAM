package by.matusevich.xmlparser.exception;

public class CandyParserException extends Throwable {

    public CandyParserException() {
        super();
    }

    public CandyParserException(String message) {
        super(message);
    }

    public CandyParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public CandyParserException(Throwable cause) {
        super(cause);
    }

    protected CandyParserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}