package com.github.dimka9910.etlservices.elasticservice.geocashing.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dimka9910.etlservices.elasticservice.geocashing.module.dto.geo.GeoData;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class CoordinatesService {


    Map<String, GeoPoint> coordinatesMap = new HashMap<>();
    String token = "b1d77c77c27e4105a90999e6355e9ca8";
    String country = "Russia";

    public GeoPoint getCoordinates(String city) {
        if (coordinatesMap.containsKey(city))
            return coordinatesMap.get(city);
        try {

            URL obj = new URL("https://api.geoapify.com/v1/geocode/search?text=" + city + "%20" + country + "&apiKey=" + token);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                System.out.println(response.toString());
                GeoData geoData = new ObjectMapper().readValue(response.toString(), GeoData.class);
                List<String> list = geoData.getFeatures().get(0).getGeometry().getCoordinates();
                GeoPoint geoPoint = new GeoPoint(Double.parseDouble(list.get(1)), Double.parseDouble(list.get(0)));
                coordinatesMap.put(city, geoPoint);
                return geoPoint;
            } else {
                System.out.println("GET request did not work.");
                return new GeoPoint();
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new GeoPoint();
    }

}
