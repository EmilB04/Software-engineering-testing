package com.varsel.ElectricityPricesTests;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.varsel.ElectricityPrices.ElectricityPriceCalculator;
import com.varsel.ElectricityPrices.ElectricityPriceData;

public class ElectricityPriceCalculatorTests {

    // Testing functions with a empty list
    
    @Test
    @DisplayName("Calculating lowest price - empty list should throw exception")
    public void testCalculateLowestPriceTryEmptyList() {
        // Arrange
        ElectricityPriceCalculator calculator = new ElectricityPriceCalculator();
        List<ElectricityPriceData> emptyList = Collections.emptyList();

        // Act and Assert
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> calculator.calculateLowestPrice(emptyList),
                "Empty list should throw an IllegalArgumentException");
    }


    @Test
    @DisplayName("Calculating highest price - empty list should throw exception")
    public void testCalculateHighestPriceTryEmptyList() {
        // Arrange
        ElectricityPriceCalculator calculator = new ElectricityPriceCalculator();
        List<ElectricityPriceData> emptyList = Collections.emptyList();

        // Act and Assert
        Assertions.assertThrows(IllegalArgumentException.class, 
        () -> calculator.calculateHighestPrice(emptyList),
        "Empty list should throw an IllegalArgumentException");
    }


    @Test
    @DisplayName("Calculating average price - empty list should throw exception")
    public void testCalculateAveragePriceTryEmptyList() {
        // Arrange
        ElectricityPriceCalculator calculator = new ElectricityPriceCalculator();
        List<ElectricityPriceData> emptyList = Collections.emptyList();

        // Act and Assert
        Assertions.assertThrows(IllegalArgumentException.class, 
        () -> calculator.calculateAveragePrice(emptyList),
        "Empty list should throw an IllegalArgumentException");
    }


        }

    
