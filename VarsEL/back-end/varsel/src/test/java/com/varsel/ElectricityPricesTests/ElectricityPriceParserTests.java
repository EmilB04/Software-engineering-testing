package com.varsel.ElectricityPricesTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.varsel.ElectricityPrices.ElectricityPriceParser;

public class ElectricityPriceParserTests {

    @Test
    @DisplayName("Testing to see if ElectricityPriceParser throws exception when there's no JSON-data to parse")
    public void electricityPriceParserTestZeroJsonData() {
        // Arrange
        ElectricityPriceParser parser = new ElectricityPriceParser();
        String jsonData = "";

        // Act and assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> parser.parse(jsonData),
        "Empty JSON-data should throw exception");
    }
    
}
