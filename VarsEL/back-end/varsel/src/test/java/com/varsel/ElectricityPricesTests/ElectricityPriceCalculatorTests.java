package com.varsel.ElectricityPricesTests;
import com.varsel.ElectricityPrices.ElectricityPriceCalculator;
import com.varsel.ElectricityPrices.ElectricityPriceData;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class ElectricityPriceCalculatorTests {

    // Testing functions with an empty list
    
    @Test
    @DisplayName("Calculating lowest price - empty list should throw exception")
    public void calculateLowestPriceTryEmptyList() {
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
    public void calculateHighestPriceTryEmptyList() {
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
    public void calculateAveragePriceTryEmptyList() {
        // Arrange
        ElectricityPriceCalculator calculator = new ElectricityPriceCalculator();
        List<ElectricityPriceData> emptyList = Collections.emptyList();

        // Act and Assert
        Assertions.assertThrows(IllegalArgumentException.class, 
        () -> calculator.calculateAveragePrice(emptyList),
        "Empty list should throw an IllegalArgumentException");
    }

    // Testing functions with electricity prices

    // Creating dummy data for test
    List<ElectricityPriceData> dummyPrices = Arrays.asList(
      new ElectricityPriceData(0.0002, "00:00", "01:00"), 
      new ElectricityPriceData(0.2332, "02:00", "03:00"),
      new ElectricityPriceData(84.00, "04:00", "05:00"),
      new ElectricityPriceData(1999.0002, "06:00", "07:00"),
      new ElectricityPriceData(1.0, "08:00", "09:00"),
      new ElectricityPriceData(4.0, "10:00", "11:00"),
      new ElectricityPriceData(6.0, "12:00", "13:00"),
      new ElectricityPriceData(8.0, "14:00", "15:00"),
      new ElectricityPriceData(3.0, "16:00", "17:00"),
      new ElectricityPriceData(9.0, "18:00", "19:00") 
    );

    @Test
    @DisplayName("Calculating lowest price with dummy values from List 'dummyPrices'")
    public void calculateLowestPriceWithDummyPrices() {
        // Arrange
        ElectricityPriceCalculator calculator = new ElectricityPriceCalculator();
        double expectedLowestPrice = 0.0002;

        // Act
        double calculationResults = calculator.calculateLowestPrice(dummyPrices);

        // Assert
        Assertions.assertEquals(expectedLowestPrice, calculationResults, "Lowest price should be: 0.0002");
    }

    @Test
    @DisplayName("Calculating highest price with dummy values from List 'dummyPrices'")
    public void calculateHighestWithPriceDummyPrices() {
        // Arrange
        ElectricityPriceCalculator calculator = new ElectricityPriceCalculator();
        double expectedHighestPrice = 1999.0002;

        // Act
        double calculationResults = calculator.calculateHighestPrice(dummyPrices);

        // Assert
        Assertions.assertEquals(expectedHighestPrice, calculationResults, "Highest price should be: 1999.0002");
    }

    @Test
    @DisplayName("Calculating average price with dummy values from List 'dummyPrices'")
    public void calculateAveragePriceWithDummyPrices() {
        // Arrange
        ElectricityPriceCalculator calculator = new ElectricityPriceCalculator();
        double expectedAveragePrice = 211.4233;

        // Act
        double calculationResults = calculator.calculateAveragePrice(dummyPrices);

        // Assert
        Assertions.assertEquals(expectedAveragePrice, calculationResults, "Average price should be: 211.4233");
    }

        }

    
