package com.example.Kau_Git.controller;

import com.example.Kau_Git.service.GetInfoService;
import com.example.Kau_Git.service.KeywordSearchService;
import com.example.Kau_Git.service.TravelNumService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController // REST API 컨트롤러로 지정
public class MapController {

    private final GetInfoService gs;
    private final TravelNumService ts;
    private final KeywordSearchService ks;

    @Autowired
    public MapController(GetInfoService gs, TravelNumService ts, KeywordSearchService ks) {
        this.gs = gs;
        this.ts = ts;
        this.ks = ks;
    }

    @GetMapping("/map")
    public ResponseEntity<String> map() {
        return ResponseEntity.ok("map");
    }

    @PostMapping("/getCoordinates")
    public ResponseEntity<?> getCoordinates(@RequestBody Coordinates coordinates) {
        try {
            List<JSONObject> info = gs.getInfo(coordinates.getLatitude(), coordinates.getLongitude());
            return ResponseEntity.ok(info);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing coordinates: " + e.getMessage());
        }
    }

    @GetMapping("/getTravelInfo")
    public ResponseEntity<Map<String, Long>> getTravelInfo() {
        try {
            Map<String, Long> travelInfo = ts.getInfo();
            return ResponseEntity.ok(travelInfo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getKeywordInfo")
    public ResponseEntity<List<JSONObject>> getKeywordInfo() {
        try {
            List<JSONObject> keywordInfo = ks.getInfo();
            return ResponseEntity.ok(keywordInfo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    // 요청 바디에서 사용될 클래스
    static class Coordinates { //원래 위도 경도 받아오는건 카카오 맵 상에 클릭으로 진행....
        private String latitude;
        private String longitude;

        public Coordinates() {
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }
    }
}