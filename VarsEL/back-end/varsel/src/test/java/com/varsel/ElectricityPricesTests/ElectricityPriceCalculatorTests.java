package com.varsel.ElectricityPricesTests;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ElectricityPrices.ElectricityPriceCalculator;
import ElectricityPrices.ElectricityPriceData;

public class ElectricityPriceCalculatorTests {
    
    @Test
    @DisplayName("Calculate average price - See if empty list throws exception")
    void calculateAveragePriceEmptyListThrowsException () {
        // Arrange
        ElectricityPriceCalculator calculator = new ElectricityPriceCalculator();
        List<ElectricityPriceData> prices = Collections.emptyList();

        // Act and Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateAveragePrice(prices);
        });
    }
}
