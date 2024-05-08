package com.example.Kau_Git.service;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class TravelNumService {

    private final RestTemplate rt;

    @Value("${api.key}")
    private String apiKey;

    public TravelNumService(RestTemplate rt){
        this.rt = rt;
    }

    private static final String BASE_URL = "https://apis.data.go.kr/B551011/DataLabService/metcoRegnVisitrDDList";

    public Map<String, Long> getInfo() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("numOfRows", 5500) // 총 몇개가 존재하는가?
                .queryParam("pageNo", 1)
                .queryParam("MobileOS", "etc")
                .queryParam("MobileApp", "hanzoom")
                .queryParam("serviceKey", apiKey)
                .queryParam("startYmd", "20240101")
                .queryParam("endYmd", "20240401");

        String uriString = builder.build().encode().toUriString().replace("+", "%2B");
        uriString += "&_type=json";

        URI uri = URI.create(uriString);

        String response = rt.getForObject(uri, String.class);

        Map<String, Long> areaVisitorMap = new HashMap<>();

        try {
            JSONParser parser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
            JSONObject jsonResponse = (JSONObject) parser.parse(response);

            JSONObject responseObj = (JSONObject) jsonResponse.get("response");
            JSONObject body = (JSONObject) responseObj.get("body");
            JSONObject items = (JSONObject) body.get("items");
            JSONArray itemArray = (JSONArray) items.get("item");

            for (Object item : itemArray) {
                JSONObject itemObj = (JSONObject) item;
                String areaNm = (String) itemObj.get("areaNm");
                double touNumDouble = Double.parseDouble(String.valueOf(itemObj.get("touNum")));
                long touNum = Math.round(touNumDouble);

                // 지자체별 관광객 수를 누적합니다.
                areaVisitorMap.merge(areaNm, touNum, Long::sum);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return areaVisitorMap;
    }
}
