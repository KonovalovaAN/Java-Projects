package Strategy;

import java.util.*;

public class NonStreamApiImportCountriesStrategy implements ImportCountriesStrategy {
    @Override
    public List<String> getImportCountries(List<ProductData> products) {
        Map<String, Integer> importVolumes = new HashMap<>();
        for (ProductData product : products) {
            String country = product.getProductCountry();
            int volume = product.getAmountOfProduct();
            importVolumes.put(country, importVolumes.getOrDefault(country, 0) + volume);
        }

        List<Map.Entry<String, Integer>> importList = new ArrayList<>(importVolumes.entrySet());
        importList.sort(Comparator.<Map.Entry<String, Integer>>comparingInt(Map.Entry::getValue).reversed());

        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : importList) {
            result.add(entry.getKey() + ": " + entry.getValue());
        }
        return result;
    }
}
