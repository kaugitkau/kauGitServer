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
public class AreaCodeService {
    private final RestTemplate rt;

    @Value("${api.key}")
    private String apiKey;

    public AreaCodeService(RestTemplate rt){
        this.rt = rt;
    }

    private static final String BASE_URL ="https://apis.data.go.kr/B551011/EngService1/areaCode1";

    public Map<String, String> getInfo(int areaCode) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("numOfRows", 100) // 총 몇개가 존재하는가?
                .queryParam("pageNo", 1)
                .queryParam("MobileOS", "etc")
                .queryParam("areaCode",areaCode) // 1~8, 31~39
                .queryParam("MobileApp", "hanzoom")
                .queryParam("serviceKey", apiKey);

        String uriString = builder.build().encode().toUriString().replace("+", "%2B");
        uriString += "&_type=json";

        URI uri = URI.create(uriString);

        String response = rt.getForObject(uri, String.class);

        Map<String, String> areaCodeMap = new HashMap<>();

        try {
            JSONParser parser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
            JSONObject jsonResponse = (JSONObject) parser.parse(response);

            JSONObject responseObj = (JSONObject) jsonResponse.get("response");
            JSONObject body = (JSONObject) responseObj.get("body");
            JSONObject items = (JSONObject) body.get("items");
            JSONArray itemArray = (JSONArray) items.get("item");

            for (Object item : itemArray) {
                JSONObject itemObj = (JSONObject) item;
                String code = String.valueOf(itemObj.get("code"));
                String name = (String) itemObj.get("name");
                areaCodeMap.put(code,name);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return areaCodeMap;
    }
}



