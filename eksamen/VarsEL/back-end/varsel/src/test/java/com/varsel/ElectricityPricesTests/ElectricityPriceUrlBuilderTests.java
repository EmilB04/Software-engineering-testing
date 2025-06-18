package com.varsel.ElectricityPricesTests;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.varsel.ElectricityPrices.ElectricityPriceUrlBuilder;

public class ElectricityPriceUrlBuilderTests {
    
    @Test
    @DisplayName("Test to see if ElectricityPriceUrlBuilder builds the URL in the right format")
    public void ElectricityPriceUrlBuilderTestBuilding() {
        //Arrange
        String url = "https://www.hvakosterstrommen.no/api/v1/prices/2024/11-12_N01.json";
        ElectricityPriceUrlBuilder urlBuilder = new ElectricityPriceUrlBuilder();
        LocalDate testDate = LocalDate.of(2024, 11, 12);
        String testZone = "N01";

        //Act
        String urlResult = urlBuilder.buildUrl(testZone, testDate); 

        //Assert
        Assertions.assertEquals(url, urlResult, "Url should be built correctly");
    }

    @Test
    @DisplayName("Test to see if ElectricityPriceUrlBuilder can use different zones")
    public void ElectricityPriceUrlBuilderTestDifferentZones() {
        //Arrange
        ElectricityPriceUrlBuilder urlBuilder = new ElectricityPriceUrlBuilder();
        LocalDate testDate = LocalDate.of(2024, 11, 12);
        String ostNorge = "N01";
        String sorNorge = "N02";
        String midtNorge = "N03";
        String nordNorge = "N04";
        String vestNorge = "N05";

        //Act and assert
        Assertions.assertEquals("https://www.hvakosterstrommen.no/api/v1/prices/2024/11-12_N01.json", urlBuilder.buildUrl(ostNorge, testDate),
        "URL with zone N01 should be built correctly");
        Assertions.assertEquals("https://www.hvakosterstrommen.no/api/v1/prices/2024/11-12_N02.json", urlBuilder.buildUrl(sorNorge, testDate),
        "URL with zone N02 should be built correctly");
        Assertions.assertEquals("https://www.hvakosterstrommen.no/api/v1/prices/2024/11-12_N03.json", urlBuilder.buildUrl(midtNorge, testDate),
        "URL with zone N03 should be built correctly");
        Assertions.assertEquals("https://www.hvakosterstrommen.no/api/v1/prices/2024/11-12_N04.json", urlBuilder.buildUrl(nordNorge, testDate),
        "URL with zone N04 should be built correctly");
        Assertions.assertEquals("https://www.hvakosterstrommen.no/api/v1/prices/2024/11-12_N05.json", urlBuilder.buildUrl(vestNorge, testDate),
        "URL with zone N05 should be built correctly");
    }

    @Test
    @DisplayName("Test to see if ElectricityPriceUrlBuilder can use different dates and historical dates")
    public void ElectricityPriceUrlBuilderTestDifferentDates() {
        //Arrange
        ElectricityPriceUrlBuilder urlBuilder = new ElectricityPriceUrlBuilder();
        LocalDate firstTestDate = LocalDate.of(2023, 11, 12);
        LocalDate secondTestDate = LocalDate.of(2022, 06, 12);
        LocalDate thirdTestDate = LocalDate.of(2021, 01, 06);
        LocalDate fourthTestDate = LocalDate.of(2024, 03, 02);
        LocalDate fifthTestDate = LocalDate.of(2024, 10, 07);
        String zone = "N01";
        

        //Act and assert
        Assertions.assertEquals("https://www.hvakosterstrommen.no/api/v1/prices/2023/11-12_N01.json", urlBuilder.buildUrl(zone, firstTestDate),
        "URL for November, 11, 2023 should be built correctly");
        Assertions.assertEquals("https://www.hvakosterstrommen.no/api/v1/prices/2022/06-12_N01.json", urlBuilder.buildUrl(zone, secondTestDate),
        "URL for November, 11, 2023 should be built correctly");
        Assertions.assertEquals("https://www.hvakosterstrommen.no/api/v1/prices/2021/01-06_N01.json", urlBuilder.buildUrl(zone, thirdTestDate),
        "URL for November, 11, 2023 should be built correctly");
        Assertions.assertEquals("https://www.hvakosterstrommen.no/api/v1/prices/2024/03-02_N01.json", urlBuilder.buildUrl(zone, fourthTestDate),
        "URL for November, 11, 2023 should be built correctly");
        Assertions.assertEquals("https://www.hvakosterstrommen.no/api/v1/prices/2024/10-07_N01.json", urlBuilder.buildUrl(zone, fifthTestDate),
        "URL for November, 11, 2023 should be built correctly");
    }
    @Test
    @DisplayName("Test to see if ElectricityPriceUrlBuilder throws IllegalArgumentException when building URL with null zone")
    public void ElectricityPriceUrlBuilderTestBuildingWithNullZone() {
        //Arrange
        ElectricityPriceUrlBuilder urlBuilder = new ElectricityPriceUrlBuilder();
        LocalDate testDate = LocalDate.of(2024, 11, 12);

        // Act and assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> urlBuilder.buildUrl(null, testDate), 
        "Trying to build URL with null zone should throw IllegalArgumentException");
    }

    @Test
    @DisplayName("Test to see if ElectricityPriceUrlBuilder throws IllegalArgumentException when building URL with null date")
    public void ElectricityPriceUrlBuilderTestBuildingWithNullDate() {
        //Arrange
        ElectricityPriceUrlBuilder urlBuilder = new ElectricityPriceUrlBuilder();
        String zone = "N01";

        // Act and assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> urlBuilder.buildUrl(zone, null), 
        "Trying to build URL with null date should throw IllegalArgumentException");
    }

}