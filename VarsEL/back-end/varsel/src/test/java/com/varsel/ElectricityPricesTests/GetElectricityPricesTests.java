package com.varsel.ElectricityPricesTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.varsel.ElectricityPrices.ElectricityPriceData;
import com.varsel.ElectricityPrices.ElectricityPriceParser;
import com.varsel.ElectricityPrices.ElectricityPriceUrlBuilder;
import com.varsel.ElectricityPrices.GetElectricityPrices;
import com.varsel.ElectricityPrices.HttpHandler;

public class GetElectricityPricesTests {
    
    @Mock
    private ElectricityPriceParser mockParser;

    @Mock 
    private HttpHandler mockHttpHandler;

    @Mock 
    private ElectricityPriceUrlBuilder mockUrlBuilder; 

    @InjectMocks
    private GetElectricityPrices getElectricityPrices;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        getElectricityPrices = new GetElectricityPrices(mockParser, mockHttpHandler, mockUrlBuilder);
    }

    @Test 
    @DisplayName("Testing to see if we can fetch electricity prices")
    public void GetElectricityPricesTestFetchingPrices () throws Exception {

        //Arrange
        String testZone = "N01";
        LocalDate today = LocalDate.now();
        String mockUrl = "https://api.examplesite.com";
        String mockJsonData = "[{\"price\":0.46143}, \"time_start\":\"2024-11-18T12:02:00\", \"time_stop\":\"2024-11-18T13:03:00\"]";
        List<ElectricityPriceData> mockParsedData = Arrays.asList(new ElectricityPriceData(0.46143, "2024-11-18T12:02:00", "2024-11-18T13:03:00"));

        when(mockUrlBuilder.buildUrl(testZone, today)).thenReturn(mockUrl);
        when(mockHttpHandler.getJSONDataFromUrl(mockUrl)).thenReturn(mockJsonData);
        when(mockParser.parse(mockJsonData)).thenReturn(mockParsedData);

        // Act
        List<ElectricityPriceData> testResults = getElectricityPrices.fetchElectricityPrices(testZone);

        // Assert
        assertEquals(1, testResults.size());
        assertEquals("2024-11-18T12:02:00", testResults.get(0).getTimeStart());
        assertEquals("2024-11-18T13:03:00", testResults.get(0).getTimeStop());
        assertEquals(0.46143, testResults.get(0).getPrice());

        verify(mockUrlBuilder).buildUrl(testZone, today);
        verify(mockHttpHandler).getJSONDataFromUrl(mockUrl);
        verify(mockParser).parse(mockJsonData);
    }

    @Test
    @DisplayName("Testing fetchElectricityPrices when UrlBuilder fails")
    public void GetElectricityPricesTestWhenUrlBuilderFails () throws Exception {
    
        //Arrange
        String testZone = "N01"; 

        when(mockUrlBuilder.buildUrl(eq(testZone), any(LocalDate.class))).thenThrow(new RuntimeException("Mock URL exception")); 

        // Act 
        List<ElectricityPriceData> testResults = getElectricityPrices.fetchElectricityPrices(testZone);

        // Assert
        assertEquals(Collections.emptyList(), testResults);
        verify(mockUrlBuilder).buildUrl(eq(testZone), any(LocalDate.class));
        verifyNoInteractions(mockHttpHandler);
        verifyNoInteractions(mockParser);
    }

    @Test
    @DisplayName("Testing fetchElectricityPrices when HttpHandler fails")
    public void GetElectricityPricesTestWhenHttpHandlerFails () throws Exception {
    
        //Arrange
        String testZone = "N01"; 
        String mockUrl = "https://api.examplesite.com";

        when(mockUrlBuilder.buildUrl(testZone, LocalDate.now())).thenReturn(mockUrl);
        when(mockHttpHandler.getJSONDataFromUrl(mockUrl)).thenThrow(new RuntimeException("Mock HTTP Exception"));

        // Act 
        List<ElectricityPriceData> testResults = getElectricityPrices.fetchElectricityPrices(testZone);

        // Assert
        assertEquals(Collections.emptyList(), testResults);
        verify(mockUrlBuilder).buildUrl(eq(testZone), any(LocalDate.class));
        verify(mockHttpHandler).getJSONDataFromUrl(mockUrl);
        verifyNoInteractions(mockParser);
    }



}