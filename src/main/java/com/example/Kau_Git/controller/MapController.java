package com.example.Kau_Git.controller;

import com.example.Kau_Git.service.GetInfoService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // REST API 컨트롤러로 지정
public class MapController {

    private final GetInfoService gs;

    @Autowired
    public MapController(GetInfoService gs){
        this.gs = gs;
    }

    @GetMapping("/map")
    public String map(){
        return "map";
    }

    @PostMapping("/getCoordinates")
    public List<JSONObject> getCoordinates(@RequestBody Coordinates coordinates){
        // JSON 형태의 요청 바디에서 위도와 경도를 받음
        try {
            // 문자열을 적절한 숫자 형식으로 변환 (예시)
            List<JSONObject> info = gs.getInfo(coordinates.getLatitude(), coordinates.getLongitude());
            return info; // JSON 형태로 정보 반환
        } catch (Exception e) {
            // 기타 예외 처리
            return null; // 실패한 경우 null이나 적절한 에러 메시지 반환
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