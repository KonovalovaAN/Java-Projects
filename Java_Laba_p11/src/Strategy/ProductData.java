package Strategy;

public class ProductData {
    String productName;
    String productCountry;
    int amountOfProduct;
    public ProductData(String productName, String productCountry, int amountOfProduct) {
        this.productName = productName;
        this.productCountry = productCountry;
        this.amountOfProduct = amountOfProduct;
    }
    public String getProductName() {
        return productName;
    }
    public String getProductCountry() {
        return productCountry;
    }
    public int getAmountOfProduct() {
        return amountOfProduct;
    }
    @Override
    public String toString() {
        return productName + ", " + productCountry + ", " + amountOfProduct + " units\n";
    }
}