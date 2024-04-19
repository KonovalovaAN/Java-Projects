package Strategy;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamApiImportCountriesStrategy implements ImportCountriesStrategy {
    @Override
    public List<String> getImportCountries(List<ProductData> products) {
        Map<String, Integer> importVolumes = products.stream()
                .collect(Collectors.groupingBy(ProductData::getProductCountry,
                        Collectors.summingInt(ProductData::getAmountOfProduct)));

        return importVolumes.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .map(entry -> entry.getKey() + ": " + entry.getValue())
                .collect(Collectors.toList());
    }
}
