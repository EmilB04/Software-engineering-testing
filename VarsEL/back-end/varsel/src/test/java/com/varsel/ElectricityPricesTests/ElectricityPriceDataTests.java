package com.varsel.ElectricityPricesTests;
import com.varsel.ElectricityPrices.ElectricityPriceData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ElectricityPriceDataTests {

    @Test
    @DisplayName("Test for testing getters in ElectricityPriceData")
    public void electricityPriceDataTestGetterMethods () {
        // Arrange
        double expectedPrice = 1.0423;
        String expectedTimeStart = "20.00";
        String expectedTimeStop = "21.00";

        // Act
        ElectricityPriceData data = new ElectricityPriceData(expectedPrice, expectedTimeStart, expectedTimeStop);

        // Assert
        Assertions.assertEquals(expectedPrice, data.getPrice(), "Expected price should be: 1.0423");
        Assertions.assertEquals(expectedTimeStart, data.getTimeStart(), "Expected time start should be: 20.00");
        Assertions.assertEquals(expectedTimeStop, data.getTimeStop(), "Expected time stop should be: 21.00");
    }

    @Test
    @DisplayName("Checks if getter works with negative price values")
    public void electricityPriceDataTestNegativePriceValues() {
        // Arrange 
        double negativePrice = -1.3872;
        String timeStart = "08.00";
        String timeStop = "09.00";

        // Act
        ElectricityPriceData data = new ElectricityPriceData(negativePrice, timeStart, timeStop);

        // Assert
        Assertions.assertEquals(negativePrice, data.getPrice(), "Expected price should be: -1.3872");
        Assertions.assertEquals(timeStart, data.getTimeStart(), "Expected time start should be: 08.00");
        Assertions.assertEquals(timeStop, data.getTimeStop(), "Expected time stop should be: 09.00");
    }


    @Test
    @DisplayName("Checks if getter works with 'null' values in timeStart and timeStop")
    public void electricityPriceDataTestNullValuesInTimeParameters() {
        // Arrange 
        double price = 21.382;
        String timeStartNull = null;
        String timeStopNull = null;

        // Act
        ElectricityPriceData data = new ElectricityPriceData(price, timeStartNull, timeStopNull);

        // Assert
        Assertions.assertEquals(price, data.getPrice(), "Expected price should be: 21.382");
        Assertions.assertEquals(timeStartNull, data.getTimeStart(), "Expected time start should be: null");
        Assertions.assertEquals(timeStopNull, data.getTimeStop(), "Expected time stop should be: null");
    }





}
