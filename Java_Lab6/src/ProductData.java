import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
public class ProductData {
    ArrayList<Product> products;
    ProductData() {
        products = new ArrayList<>();
    }
    static class Product {
        private final String productName;
        private final String productCountry;
        private final int amountOfProduct;
        Product (String productName, String productCountry, int amountOfProduct){
            this.productName = productName;
            this.productCountry = productCountry;
            this.amountOfProduct = amountOfProduct;
        }
        @Override
        public String toString(){
            return productName + ", " + productCountry + ", " + amountOfProduct + "\n";
        }
    }
        public void addProduct (String productName, String productCountry, int amountOfProduct){
            products.add(new Product(productName, productCountry, amountOfProduct));
        }
        public void readData (File file) throws IOException {
            Scanner input = new Scanner(file);
            if(!input.hasNextInt()){
                throw new IOException("Error: the file does not contain the number of products.");
            }
            int size = Integer.parseInt(input.nextLine());
            if (size <= 0) {
                throw  new IOException("Error: the number of products should be a positive integer.");
            }
            for(int i = 0; i < size; i++) {
                if(!input.hasNextLine()){
                    throw new IOException("Error: the file does not contain the required number of characteristics.");
                }
                String productName = input.nextLine();
                if(!input.hasNextLine()) {
                    throw new IOException("Error: the last product characteristic contains only the product name.");
                }
                String productCountry = input.nextLine();
                if(!input.hasNextInt()){
                    throw new IOException("Error: the wrong number of products is indicated in the product specifications.");
                }
                int amountOfProducts = Integer.parseInt(input.nextLine());
                if(amountOfProducts <= 0) {
                    throw new IOException("Error: the amount of products should be a positive integer.");
                }
                products.add(new Product(productName, productCountry, amountOfProducts));
            }
            if (input.hasNext()) {
                throw new IOException("Error: file has extra data.");
            }
        }
    public List<String> importersByTotalImportVolume() {
        Map<String, Integer> importVolumeByCountry = new HashMap<>();
        for (Product product : products) {
            importVolumeByCountry.merge(product.productCountry, product.amountOfProduct, Integer::sum);
        }
        List<String> sortedCountries = importVolumeByCountry.entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        return sortedCountries;
    }
}