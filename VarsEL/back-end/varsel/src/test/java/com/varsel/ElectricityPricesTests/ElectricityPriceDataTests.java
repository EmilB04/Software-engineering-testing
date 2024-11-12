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

}
