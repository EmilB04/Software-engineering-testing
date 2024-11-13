package com.varsel.ElectricityPricesTests;
import java.util.List;

import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.varsel.ElectricityPrices.ElectricityPriceData;
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

    @Test 
    @DisplayName("Testing to see if ElectricityPriceParser throws exception when there's null JSON-data to parse")
    public void electricityPriceParserTestNullJsonData() {
        // Arrange 
        ElectricityPriceParser parser = new ElectricityPriceParser();

        // Act and assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> parser.parse(null),
        "Null JSON-data should throw exception");
    }

    @Test 
    @DisplayName("Testing to see if ElectricityPriceParser throws exception when there's only whitespace when trying to parse")
    public void electricityPriceParserTestExceptionWhenWhitespace() {
        // Arrange 
        ElectricityPriceParser parser = new ElectricityPriceParser();
        String jsonData = "        ";

        // Act and assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> parser.parse(jsonData),
        "Only whitespace in JSON-data should throw exception");
    }

    @Test
    @DisplayName("Testing ElectricityPriceParser with valid JSON-data")
    public void electricityPriceParserTestValidJsonData () {
        // Arrange
        ElectricityPriceParser parser = new ElectricityPriceParser();
        String jsonData = "[{\"NOK_per_kWh\": 1.21745, \"time_start\": \"20:00\", \"time_end\": \"21:00\"}]";

        // Act
        List<ElectricityPriceData> testList = parser.parse(jsonData);

        // Assert
        Assertions.assertEquals(1.21745, testList.get(0).getPrice(), "Expected price: 1.21745");
        Assertions.assertEquals("20:00", testList.get(0).getTimeStart(), "Expected time start: 20:00");
        Assertions.assertEquals("21:00", testList.get(0).getTimeStop(), "Expected time end: 21:00");
    }


    @Test
    @DisplayName("Testing ElectricityPriceParser with valid JSON-data - multiple data")
    public void electricityPriceParserTestValidMultipleJsonData () {
        // Arrange
        ElectricityPriceParser parser = new ElectricityPriceParser();
        String jsonData = "["+ "{\"NOK_per_kWh\": 1565, \"time_start\": \"15:00\", \"time_end\": \"16:00\"},"
        + "{\"NOK_per_kWh\": 222, \"time_start\": \"16:00\", \"time_end\": \"17:00\"},"
        + "{\"NOK_per_kWh\": 0.75, \"time_start\": \"17:00\", \"time_end\": \"18:00\"},"
        + "{\"NOK_per_kWh\": 1.63722, \"time_start\": \"18:00\", \"time_end\": \"19:00\"},"
        + "{\"NOK_per_kWh\": 5.234, \"time_start\": \"19:00\", \"time_end\": \"20:00\"}"+ "]";

        // Act
        List<ElectricityPriceData> testList = parser.parse(jsonData);

        // Assert
        Assertions.assertEquals(5, testList.size(), "Expecting 5 entries with data");

        Assertions.assertEquals(1565, testList.get(0).getPrice(), "Expected price: 1565");
        Assertions.assertEquals("15:00", testList.get(0).getTimeStart(), "Expected time start: 15:00");
        Assertions.assertEquals("16:00", testList.get(0).getTimeStop(), "Expected time end: 16:00");
        Assertions.assertEquals(222, testList.get(1).getPrice(), "Expected price: 222");
        Assertions.assertEquals("16:00", testList.get(1).getTimeStart(), "Expected time start: 16:00");
        Assertions.assertEquals("17:00", testList.get(1).getTimeStop(), "Expected time end: 17:00");
        Assertions.assertEquals(0.75, testList.get(2).getPrice(), "Expected price: 0.75");
        Assertions.assertEquals("17:00", testList.get(2).getTimeStart(), "Expected time start: 17:00");
        Assertions.assertEquals("18:00", testList.get(2).getTimeStop(), "Expected time end: 18:00");
        Assertions.assertEquals(1.63722, testList.get(3).getPrice(), "Expected price: 1.63722");
        Assertions.assertEquals("18:00", testList.get(3).getTimeStart(), "Expected time start: 18:00");
        Assertions.assertEquals("19:00", testList.get(3).getTimeStop(), "Expected time end: 19:00");
        Assertions.assertEquals(5.234, testList.get(4).getPrice(), "Expected price: 1.21745");
        Assertions.assertEquals("19:00", testList.get(4).getTimeStart(), "Expected time start: 19:00");
        Assertions.assertEquals("20:00", testList.get(4).getTimeStop(), "Expected time end: 20:00");
    }

    @Test
    @DisplayName("Testing ElectricityPriceParser with invalid JSON-data to see if it throws JSONException")
    public void electricityPriceParserTestInvalidJsonData () {
        // Arrange
        ElectricityPriceParser parser = new ElectricityPriceParser();
        String invalidJsonData = "{NOK_per_kWh: 1.21745, time_start: 20:00, time_end: 21:00}";

        // Act and assert
        Assertions.assertThrows(JSONException.class, () -> parser.parse(invalidJsonData), 
        "Should throw JSONException");
    }

    @Test
    @DisplayName("Testing ElectricityPriceParser with missing JSON-data to see if it throws JSONException")
    public void electricityPriceParserTestmissingJsonData () {
        // Arrange
        ElectricityPriceParser parser = new ElectricityPriceParser();
        String missingJsonData = "[{\"NOK_per_kWh\": 1.21745, \"time_end\": \"21:00\"}]";

        // Act and assert
        Assertions.assertThrows(JSONException.class, () -> parser.parse(missingJsonData), 
        "Should throw JSONException");
    }

}
