package com.varsel.ElectricityPrices;

import java.time.LocalDate;

/**
 * Builds a URL to retrieve electricity prices for specified zones and dates.
 * 
 * This class constructs a URL that utilizes the API from which electricity price data is retrieved.
 * 
 * Example usage:
 * <pre>
 * ElectricityPriceUrlBuilder urlBuilder = new ElectricityPriceUrlBuilder();
 * String url = urlBuilder.buildUrl("N01", LocalDate.now());
 * </pre>
 */
public class ElectricityPriceUrlBuilder {

    private static final String URL = "https://www.hvakosterstrommen.no/api/v1/prices/";

    /**
     * Builds a URL-String for getting electricity price data.
     * 
     * @param zone Price zone for where in the country you want to retrieve prices from
     * @param date Date for which day you want to retrieve prices from
     * @return A string with the complete URL for making API requests
     * @throws IllegalArgumentException when trying to build URL with null zone
     * @throws IllegalArgumentException when trying to buil URL with null date
     */

    public String buildUrl(String zone, LocalDate date) {

        if (zone == null) {
            throw new IllegalArgumentException("Zone can't be null");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date can't be null");
        }

        String year = String.valueOf(date.getYear());
        String month = String.format("%02d", date.getMonthValue());
        String day = String.format("%02d", date.getDayOfMonth());

        return URL + year + "/" + month + "-" + day + "_" + zone + ".json";
    }
}
