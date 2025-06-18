package com.varsel.UtilityTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.varsel.Utility.HttpGetClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class HttpGetClientTests {

    @Mock
    private HttpURLConnection mockConnection;

    @InjectMocks
    private HttpGetClient httpGetClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test GET request with mocked connection")
    public void HttpHetClientTestGetRequestMock() throws IOException, URISyntaxException {
        // Arrange
        String testUrl = "https://jsonplaceholder.typicode.com/posts/1";
        String mockResponse = "{\"id\":1,\"title\":\"Mock Title\"}";

        when(mockConnection.getInputStream()).thenReturn(new ByteArrayInputStream(mockResponse.getBytes()));

        // Act
        String result = httpGetClient.get(testUrl);

        // Assert
        assertEquals(mockResponse, result);

        verify(mockConnection).setRequestMethod("GET");
        verify(mockConnection).disconnect();
    }

    @Test
    @DisplayName("Test IOException during GET request")
    public void HttpGetClientTestingIOExceptionGetRequest() throws IOException, URISyntaxException {
        // Arrange
        String testUrl = "https://jsonplaceholder.typicode.com/posts/1";
        when(mockConnection.getInputStream()).thenThrow(new IOException("Mock IOException"));

        // Act & Assert
        IOException exception = assertThrows(IOException.class, () -> httpGetClient.get(testUrl));

        assertEquals("Mock IOException", exception.getMessage());
        verify(mockConnection).disconnect();
    }


    @Test
    @DisplayName("Test URISyntaxException for invalid URI")
    public void HttpGetClientTestURISyntaxException() {
        // Arrange
        String invalidUrl = "http://examplesite|api";

        // Act & Assert
        URISyntaxException exception = assertThrows(URISyntaxException.class, () -> {
            httpGetClient.get(invalidUrl);});
        
        assertEquals("Illegal character in authority at index 18: http://examplesite|api", exception.getMessage());
    }

    @Test
    @DisplayName("Test MalformedURLException for invalid URL")
    public void HttpGetClientTestMalformedURLException() {
        // Arrange
        String invalidUrl = "htp:/invalid-url";

        // Act & Assert
        MalformedURLException exception = assertThrows(MalformedURLException.class, () -> {
            httpGetClient.get(invalidUrl);});
        
        assertEquals("unknown protocol: htp", exception.getMessage());
    }

}