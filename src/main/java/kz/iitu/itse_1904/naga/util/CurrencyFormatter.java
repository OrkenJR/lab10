package kz.iitu.itse_1904.naga.util;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class CurrencyFormatter implements Formatter<String> {
    @Override
    public String parse(String text, Locale locale) throws ParseException {
        String[] s = text.split(":");// supposing USD:546
        String currencyCode = s[0];
        String amount = s[1];
        switch (currencyCode){
            case "USD":
                return String.valueOf(Long.parseLong(amount)*520);
            case "RUB":
                return String.valueOf(Long.parseLong(amount)*5);
            case "KZT":
                return amount;
            default: return amount;
        }
    }

    @Override
    public String print(String object, Locale locale) {
        return object;
    }
}
