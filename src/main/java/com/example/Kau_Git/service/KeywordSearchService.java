package com.example.Kau_Git.service;

import com.example.Kau_Git.service.AreaCodeService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KeywordSearchService {

    private final RestTemplate rt;
    private final AreaCodeService as;

    @Value("${api.key}")
    private String apiKey;

    public KeywordSearchService(RestTemplate rt, AreaCodeService as) {
        this.rt = rt;
        this.as = as;
    }

    private static final String BASE_URL = "https://apis.data.go.kr/B551011/EngService1/searchKeyword1";

    public List<Map<String, String>> getInfo(String keyword,int areaCode, int sigunguCode) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("MobileOS", "etc")
                .queryParam("MobileApp", "hanzoom")
                .queryParam("keyword", keyword)
                .queryParam("areaCode", areaCode)
                .queryParam("sigunguCode", sigunguCode)
                .queryParam("serviceKey", apiKey);

        String uriString = builder.build().encode().toUriString().replace("+", "%2B");
        uriString += "&_type=json";

        URI uri = URI.create(uriString);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = rt.exchange(uri, HttpMethod.GET, entity, String.class);
        String response = responseEntity.getBody();

        List<Map<String, String>> keywordlist = new ArrayList<>();

        try {
            JSONParser parser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
            JSONObject jsonResponse = (JSONObject) parser.parse(response);

            JSONObject responseObj = (JSONObject) jsonResponse.get("response");
            if (responseObj != null) {
                JSONObject body = (JSONObject) responseObj.get("body");
                if (body != null) {
                    JSONObject items = (JSONObject) body.get("items");
                    if (items != null) {
                        Object itemObj = items.get("item");
                        if (itemObj instanceof JSONArray) {
                            JSONArray itemArray = (JSONArray) itemObj;
                            for (Object item : itemArray) {
                                JSONObject itemJson = (JSONObject) item;
                                Map<String, String> locationInfo = extractLocationInfo(itemJson);
                                keywordlist.add(locationInfo);
                            }
                        } else if (itemObj instanceof JSONObject) {
                            JSONObject itemJson = (JSONObject) itemObj;
                            Map<String, String> locationInfo = extractLocationInfo(itemJson);
                            keywordlist.add(locationInfo);
                        }
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return keywordlist;
    }

    private Map<String, String> extractLocationInfo(JSONObject itemJson) {
        Map<String, String> locationInfo = new HashMap<>();
        locationInfo.put("addr1", (String) itemJson.get("addr1"));
        locationInfo.put("firstimage", (String) itemJson.get("firstimage"));
        locationInfo.put("mapx", (String) itemJson.get("mapx"));
        locationInfo.put("mapy", (String) itemJson.get("mapy"));

        return locationInfo;
    }

    public Map<String, String> getAreaInfo(int areaCode) {
        return as.getInfo(areaCode);
    }

}
