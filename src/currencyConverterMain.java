import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class currencyConverterMain {

    public static void main(String args[]) {

        System.out.println();
        System.out.println("Java Currency Converter");
        System.out.println("Available Currencies:\n");

        Map<String, String> availableCurrencies = parseAvailableCurrencies("/home/abbluiz/playground/currencies.csv");

        System.out.println("ISO - Name");
        for(String key: availableCurrencies.keySet())
            System.out.println(key + " - " + availableCurrencies.get(key));

        Scanner input = new Scanner(System.in);
        String fromCurrency, toCurrency;
        double originalValue;

        System.out.println();
        System.out.println("Please, enter amount of money to be converted, ISO code of currency to be converted from and " +
                "ISO code of currency to be converted to");
        System.out.println("Use the following format: X CODE to CODE, being X an integer or decimal number, and CODE " +
                "the ISO code of the currency");
        System.out.println("e.g. \"117.34 USD to CAD\"\n");

        originalValue = input.nextDouble();
        fromCurrency = input.next();
        input.next();
        toCurrency = input.next();

        System.out.println(); // TODO Throw error if chosen currency is not available
        System.out.println("You want to convert " + originalValue + " " + fromCurrency + " to " + toCurrency);

        Currency from = new Currency(availableCurrencies.get(fromCurrency), fromCurrency);
        Currency to = new Currency(availableCurrencies.get(toCurrency), toCurrency);

        from.setConversionRates(parseConversionRates(from.getCode(), "/home/abbluiz/playground/conversion-rates.csv"));

        System.out.println(from);
        System.out.println(to);

    }

    private static Map<String, String> parseAvailableCurrencies(String currenciesFileName) {

        Map<String, String> availableCurrencies = new HashMap<>();

        try {

            FileReader currenciesFile = new FileReader(currenciesFileName);

            CSVReader csvReader = new CSVReaderBuilder(currenciesFile).withSkipLines(1).build();
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null)
                availableCurrencies.put(nextRecord[0], nextRecord[1]);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return availableCurrencies;

    }

    private static Map<String, Double> parseConversionRates(String currencyCode, String conversionRatesFileName) {

        // TODO Implement this method

//        try {
//
//            FileReader conversionRatesFile = new FileReader(conversionRatesFileName);
//
//            CSVReader csvReader = new CSVReader(conversionRatesFile);
//            String[] line;
//
//            while ((line = csvReader.readNext()) != null) {
//
//                for(String cell : line) {
//
//                }
//
//            }
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return null;
    }

}
