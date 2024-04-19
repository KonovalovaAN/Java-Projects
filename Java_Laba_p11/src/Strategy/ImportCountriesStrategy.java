package Strategy;

import java.util.List;

public interface ImportCountriesStrategy {
    List<String> getImportCountries(List<ProductData> products);
}
