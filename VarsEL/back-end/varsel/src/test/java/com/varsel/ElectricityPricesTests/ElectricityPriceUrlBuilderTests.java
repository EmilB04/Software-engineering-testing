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
        ElectricityPriceUrlBuilder UrlBuilder = new ElectricityPriceUrlBuilder();
        LocalDate testDate = LocalDate.of(2024, 11, 12);
        String testZone = "N01";

        //Act
        String urlResult = UrlBuilder.buildUrl(testZone, testDate); 

        //Assert
        Assertions.assertEquals(url, urlResult, "Url should be built correctly");
    }
}