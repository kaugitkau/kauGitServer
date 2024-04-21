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
public class GetFestivalService {

    private final RestTemplate rt;

    // API의 기본 URL
    private static final String BASE_URL = "https://apis.data.go.kr/B551011/KorService1/searchFestival1";

    @Value("${api.key}")
    private String apiKey;

    public GetFestivalService(RestTemplate rt) {
        this.rt = rt;
    }

    public List<JSONObject> getFestival() {
        // UriComponentsBuilder를 사용하여 URL 생성
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("MobileOS", "etc")
                .queryParam("MobileApp", "han")
                .queryParam("eventStartDate", "20240411")
                .queryParam("serviceKey", apiKey); // 서비스 키를 직접 제공

        // URI를 문자열로 변환하고, + 문자를 %2B로 변환
        String uriString = builder.build().encode().toUriString().replace("+", "%2B");

        uriString += "&_type=json";

        // 변환된 URI 문자열로 URI 객체 생성
        URI uri = URI.create(uriString);


        // API 요청 및 응답 받기
        String response = rt.getForObject(uri, String.class);

        List<JSONObject> festivalList = new ArrayList<>();

        try {
            JSONParser parser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
            JSONObject jsonResponse = (JSONObject) parser.parse(response);

            JSONObject responseObj = (JSONObject) jsonResponse.get("response");
            JSONObject body = (JSONObject) responseObj.get("body");
            JSONObject items = (JSONObject) body.get("items");
            JSONArray itemArray = (JSONArray) items.get("item");

            for (Object item : itemArray) {
                JSONObject itemObj = (JSONObject) item;
                String addr1 = (String) itemObj.get("addr1");
                String title = (String) itemObj.get("title");
                String firstImage = (String) itemObj.get("firstimage");

                JSONObject festivalInfo = new JSONObject();
                festivalInfo.put("addr1", addr1);
                festivalInfo.put("title", title);
                festivalInfo.put("firstimage", firstImage);

                festivalList.add(festivalInfo);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return festivalList;
    }
}