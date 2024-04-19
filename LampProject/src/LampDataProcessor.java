import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class LampDataProcessor {
    public static List<Lamp> sortByPriceAscending(ArrayList<Lamp> lamps) {
        return lamps.stream()
                .sorted(Comparator.comparingInt(Lamp::calculatePrice))
                .collect(Collectors.toList());
    }
    public static List<Lamp> sortByPriceToPowerRatioDescending(ArrayList<Lamp> lamps) {
        return lamps.stream()
                .sorted(Comparator.comparingDouble(lamp -> lamp.calculatePrice() / lamp.power))
                .collect(Collectors.toList());
    }
    public static List<String> getManufacturersStartingWithC(ArrayList<Lamp> lamps) {
        return lamps.stream()
                .map(lamp -> lamp.manufacturer)
                .filter(manufacturer -> manufacturer.startsWith("C"))
                .distinct()
                .collect(Collectors.toList());
    }
    public static double calculateAveragePriceByManufacturer(ArrayList<Lamp> lamps, String manufacturer) {
        return lamps.stream()
                .filter(lamp -> lamp.manufacturer.equals(manufacturer))
                .mapToDouble(Lamp::calculatePrice)
                .average()
                .orElse(0.0);
    }
}