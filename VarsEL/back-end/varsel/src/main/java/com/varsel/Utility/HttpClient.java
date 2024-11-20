package com.varsel.Utility;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * This interface show how to interact with an HTTP client
 * 
 * Classes that use this interface have to provide a way to send a HTTP to a URL and return the  api response as a String. 
 * 
 * Example usage:
 * <pre>
 * HttpCLient client = new HttpGetClient(); 
 * String apiResponse = client.get("https://api.apiexamplesite.com")
 * </pre>
 */
public interface HttpClient {
    /**
     * Sends a GET request to the URL and returns the response/data as a String
     * @param urlInput, the URL you want to send a GET request to
     * @return Data from the URL as a String
     * @throws Exception if something wrong happens when making the request or handling the response/data
     */
    String get(String urlInput) throws IOException, URISyntaxException;
}
