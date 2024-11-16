package com.varsel.ElectricityPricesTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.varsel.ElectricityPrices.HttpHandler;
import com.varsel.Utility.HttpClient;

public class HttpHandlerTests {

    @Mock
    private HttpClient mockHttpClient; 

    @InjectMocks
    private HttpHandler httpHandler; 

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        httpHandler = new HttpHandler(mockHttpClient);
    }

    @Test
    @DisplayName("Testing to see if JSON data can extracted from a valid URL")
    void HttpHandlerGetJsonDataFromUrl() throws IOException, URISyntaxException {
        // Arrange
        String testUrl = "https://api.examplesite.com";
        String testResponse = "{\"key\": \"value\"}";
        when(mockHttpClient.get(testUrl)).thenReturn(testResponse);

        // Act
        String response = httpHandler.getJSONDataFromUrl(testUrl);

        // Assert
        assertEquals(testResponse, response);
        verify(mockHttpClient, times(1)).get(testUrl);

        
    }
}
