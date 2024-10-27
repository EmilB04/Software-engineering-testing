import java.util.List;

public class ElectricityPriceCalculator {

    // Regner ut gjennomsnittsprisen til strømprisen for et døgn 
    public double calculateAveragePrice(List<ElectricityPriceData> prices) {
        if (prices.isEmpty()) {
            throw new IllegalArgumentException("Kunne ikke kalkulere gjennomsnittspris da listen er tom. Undersøk inputlisten");
        }

        double sum = 0; 
        for (ElectricityPriceData data : prices) {
            sum += data.getPrice();
        }

        double average = sum / prices.size();
        return average;
    }

    // Regner ut høyeste pris fra prislisten 
    public double calculateHighestPrice(List<ElectricityPriceData> prices) {
        if (prices.isEmpty()) {
            throw new IllegalArgumentException("Kunne ikke kalkulere høyest pris da listen er tom. Undersøk inputlisten");
        }

        double highest = Double.MIN_VALUE; 
        for (ElectricityPriceData data : prices) {
            if (data.getPrice() > highest) {
                highest = data.getPrice();
            }
        }

        return highest;
    }

    // Regner ut laveste pris fra prisListen
    public double calculateLowestPrice(List <ElectricityPriceData> prices) {
        if (prices.isEmpty()) {
            throw new IllegalArgumentException("Kunne ikke kalkulere laveste pris da listen er tom. Undersøk inputlisten");
        }

        double lowest = Double.MAX_VALUE;
        for (ElectricityPriceData data : prices) {
            if (data.getPrice() < lowest) {
                lowest = data.getPrice();
            }
        }

        return lowest;
    }
}
