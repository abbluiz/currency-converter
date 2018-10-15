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

        StringBuilder result = new StringBuilder(this.name + " (" + this.code + ")" + "\n\n");

        if(this.conversionRates != null) {

            for(Map.Entry m:this.conversionRates.entrySet())
                result.append("1 ").append(this.code).append(" equals ").append(m.getValue()).append(" ").append(m.getKey()).append("\n");

        } else {
            result.append("No conversion rates stored");
        }

        result.append("\n");

        return result.toString();

    }

}
