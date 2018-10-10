import java.util.Map;

public class Currency {

    private String name;
    private String code;
    private Map<String, Double> conversionRates;

    public Currency(String name, String code, Map<String, Double> conversionRates) {

        this.name = name;
        this.code = code;
        this.conversionRates = conversionRates;

    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public Map<String, Double> getConversionRates() {
        return this.conversionRates;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setCode(String newCode) {
        this.code = newCode;
    }

    public void setConversionRates(Map<String, Double> newConversionRates) {
        this.conversionRates = newConversionRates;
    }

}
