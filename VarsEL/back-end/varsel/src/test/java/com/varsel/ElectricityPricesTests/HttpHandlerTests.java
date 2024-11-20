package com.varsel.ElectricityPricesTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    void HttpHandlerGetJsonDataFromValidUrl() throws IOException, URISyntaxException {
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

    @Test
    @DisplayName("Testing to see if IOException is thrown when network errors occur")
    void HttpHandlerTestExceptionWhenNetworkError() throws IOException, URISyntaxException {
        // Arrange
        String testUrl = "https://api.examplesite.com";
        when(mockHttpClient.get(testUrl)).thenThrow(new IOException("Network error"));

        // Act and assert
        IOException exception = assertThrows(IOException.class, () -> httpHandler.getJSONDataFromUrl(testUrl));
        assertEquals("Network error", exception.getMessage());
        verify(mockHttpClient, times(1)).get(testUrl);
    }

    @Test
    @DisplayName("Testing to see if URISynstaxException is thrown if an invalid URL is used")
    void HttpHandlerTestExceptionWhenInvalidUrl() throws IOException, URISyntaxException {
        // Arrange
        String invalidUrl = "invalid.dummy.url";
        when(mockHttpClient.get(invalidUrl)).thenThrow(new URISyntaxException(invalidUrl, "Invalid URI"));

        // Act and assert
        URISyntaxException exception = assertThrows(URISyntaxException.class, () -> httpHandler.getJSONDataFromUrl(invalidUrl));
        assertEquals("Invalid URI", exception.getReason());
        verify(mockHttpClient, times(1)).get(invalidUrl);
    }

}
