import java.util.Map;

public class Currency {

    private String name;
    private String code;
    private Map<String, Double> conversionRates;

    Currency(String name, String code) {

        this.name = name;
        this.code = code;
        this.conversionRates = null;

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

    @Override
    public String toString() {
        return this.name + " (" + this.code + ")";
    }

}
