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
import java.util.ArrayList;
import java.util.List;

@Service
public class KeywordSearchService {

    private final RestTemplate rt;

    @Value("${api.key}")
    private String apiKey;

    public KeywordSearchService(RestTemplate rt) {
        this.rt = rt;
    }


    private static final String BASE_URL = "https://apis.data.go.kr/B551011/EngService1/searchKeyword1";


    public List<JSONObject> getInfo() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("MobileOS", "etc")
                .queryParam("MobileApp", "hanzoom")
                .queryParam("keyword", "pharmacy")
                .queryParam("serviceKey", apiKey); // 서비스 키를 직접 제공

        // URI를 문자열로 변환하고, + 문자를 %2B로 변환
        String uriString = builder.build().encode().toUriString().replace("+", "%2B");

        uriString += "&_type=json";

        // 변환된 URI 문자열로 URI 객체 생성
        URI uri = URI.create(uriString);

        String response = rt.getForObject(uri, String.class);

        List<JSONObject> keywordlist = new ArrayList<>();

        try {
            JSONParser parser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
            JSONObject jsonResponse = (JSONObject) parser.parse(response);

            JSONObject responseObj = (JSONObject) jsonResponse.get("response");
            JSONObject body = (JSONObject) responseObj.get("body");
            JSONObject items = (JSONObject) body.get("items");
            JSONArray itemArray = (JSONArray) items.get("item"); //여기 까지는 무조건 똑같음

            for (Object item : itemArray) {
                JSONObject itemObj = (JSONObject) item;
                String addr1 = (String) itemObj.get("addr1");
                String firstImage = (String) itemObj.get("firstimage");
                String mapx = (String) itemObj.get("mapx");
                String mapy = (String) itemObj.get("mapy");

                JSONObject locationInfo = new JSONObject();
                locationInfo.put("addr1", addr1);
                locationInfo.put("firstimage", firstImage);
                locationInfo.put("mapx", mapx);
                locationInfo.put("mapy", mapy);

                keywordlist.add(locationInfo);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return keywordlist;

    }

}
