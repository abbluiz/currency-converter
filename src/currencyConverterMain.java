import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class currencyConverterMain {

    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);

        System.out.println();
        System.out.println("Java Currency Converter");
        System.out.println("Please, enter the .csv file which contains the available currencies");

        /*
         * Reads file and fetch all possible currencies that the program can convert
         * User must provide the file; it must be under the program specifications format
         * Available Currencies File Format: must be comma separated values (CSV)
         * First row is the header (will be ignored by program; useful for human readability)
         * First column is the ISO code associated with each available currency
         * Second column is currency full name
         */
        Map<String, String> availableCurrencies = parseAvailableCurrencies(
                input.next());

        // Display available currencies
        System.out.println("Available Currencies:\n");
        System.out.println("ISO - Name");
        for(String key: availableCurrencies.keySet())
            System.out.println(key + " - " + availableCurrencies.get(key));

        String fromCurrency, toCurrency;
        double originalValue;

        System.out.println();
        System.out.println("Please, enter amount of money to be converted, ISO code of currency to be converted from and " +
                "ISO code of currency to be converted to");
        System.out.println("Use the following format: X CODE to CODE, being X an integer or decimal number, and CODE " +
                "the ISO code of the currency");
        System.out.println("e. g. \"117.34 USD to CAD\"\n");

        // Reads input from user: amount to be converted and ISO codes of currencies to be converted from and to
        originalValue = input.nextDouble(); // Reads amount to be converted from
        fromCurrency = input.next(); // Reads ISO code of currency to be converted from
        input.next(); // Reads "to" string and ignores it
        toCurrency = input.next(); // Reads ISO code of currency to be converted to

        System.out.println();

        // Create currency objects
        Currency from = new Currency(availableCurrencies.get(fromCurrency), fromCurrency);
        Currency to = new Currency(availableCurrencies.get(toCurrency), toCurrency);

        System.out.println("You want to convert " + from.getCode() + " (" + from.getName() + ") to " +
                to.getCode() + " (" + to.getName() + ")" + "\n");

        /*
         * Reads file and fetch all possible conversion rates for the currencies which are being converted from/to
         * User must provide the file; it must be under the program specifications format
         * Conversion Rates File Format: must be comma separated values (CSV)
         * Each column in header must contain ISO codes of available currencies which will be converted from
         * Each row in first column must contain ISO codes of available currencies which will be converted to
         * e. g. The conversion rate used to convert 1 CAD to EUR will be located in column CAD and row EUR
         * The method parseConversionRates should return a map containing all conversion rates to convert from a
         * specific currency to all other available currency
         */
        System.out.println("Please, enter the .csv file which contains the conversion rates");
        from.setConversionRates(parseConversionRates(from.getCode(),
                input.next())); // Sets Currency map
        // (e.g. if converting from CAD, it returns and sets a map that contains the conversion rates from CAD to
        // all other available currencies)

        System.out.println("\nResult: " + originalValue + " " + from.getCode() + " equals to " +
                (originalValue * from.getConversionRates().get(to.getCode())) + " " + to.getCode());

    }

    private static Map<String, String> parseAvailableCurrencies(String currenciesFileName) {

        Map<String, String> availableCurrencies = new HashMap<>();

        try {

            FileReader currenciesFile = new FileReader(currenciesFileName);

            // Read CSV file and skips header (first row)
            CSVReader csvReader = new CSVReaderBuilder(currenciesFile).withSkipLines(1).build();
            String[] row;

            // Reads each row and stores values into map; first cell is ISO code and second cell is currency full name
            // Each row is a list of cells represented by the array row
            while ((row = csvReader.readNext()) != null)
                availableCurrencies.put(row[0], row[1]);


        } catch (Exception e) {
            e.printStackTrace();
        }

        // Returns list of available currencies as a Map, with ISO code as key and currency full name as value
        return availableCurrencies;

    }

    private static Map<String, Double> parseConversionRates(String currencyCode, String conversionRatesFileName) {


        Map<String, Double> conversionRates = null;

        try {

            FileReader conversionRatesFile = new FileReader(conversionRatesFileName);

            CSVReader csvReader = new CSVReader(conversionRatesFile);
            String[] row;
            int convertFromCurrencyIndex = 0;

            conversionRates = new HashMap<>();

            row = csvReader.readNext();

            // Iterates through the header and find the index of currency to convert from
            if(row != null) {

                for(String cell : row) {

                    if(currencyCode.equals(cell))
                        break;

                    convertFromCurrencyIndex++;

                }

            }

            // Iterates through the rows and put conversion rates into map, using the index previously found
            while ((row = csvReader.readNext()) != null) {

                String convertToCurrency = row[0];

                conversionRates.put(convertToCurrency, Double.parseDouble(row[convertFromCurrencyIndex]));

            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        // It should return a map with ISO codes as keys and conversion rates as values
        return conversionRates;

    }

}
