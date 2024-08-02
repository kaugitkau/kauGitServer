package com.example.Kau_Git.controller;

import com.example.Kau_Git.Oauth.SessionUser;
import com.example.Kau_Git.service.*;
import com.example.Kau_Git.service.GetInfoService;
import com.example.Kau_Git.service.KeywordSearchService;
import com.example.Kau_Git.service.TravelNumService;
import jakarta.servlet.http.HttpServletRequest;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.*;

import java.awt.geom.Area;
import java.security.Key;
import java.util.List;
import java.util.Map;

@RestController
public class MapController {

    private final GetInfoService gs;
    private final KeywordSearchService ks;

    private final TravelNumService ts;

    @Autowired
    public MapController(GetInfoService gs ,KeywordSearchService ks, TravelNumService ts){
        this.gs =gs;
        this.ks=ks;
        this.ts = ts;
    }

    @GetMapping("/map")//앙아아앙아아아
    public String map(){
        return "map";
    }

    @PostMapping("/getCoordinates") //ok
    public ResponseEntity<List<JSONObject>> getCoordinates(HttpServletRequest request, @RequestParam("lat") String latitude, @RequestParam("lng") String longitude) {
        try {
            SessionUser user = (SessionUser) request.getAttribute("user");
            List<JSONObject> info = gs.getInfo(latitude, longitude);
            return new ResponseEntity<>(info, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/getCode") //ok
    public ResponseEntity<Map<String, String>> getCode(@RequestParam("areaCode") int areaCode) {
        try {
            Map<String, String> info = ks.getAreaInfo(areaCode);
            return new ResponseEntity<>(info, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/getKeywords") //OK
    public ResponseEntity<List<Map<String, String>>> getKeyword(@RequestParam("keyword") String keyword,@RequestParam("areaCode") int areaCode, @RequestParam("sigunguCode") int sigunguCode) {
        try {
            List<Map<String, String>> info = ks.getInfo(keyword, areaCode, sigunguCode);
            return new ResponseEntity<>(info, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getTravelNumbers") //ok
    public ResponseEntity<Map<String, Long>> getTravelNumbers() {
        try {
            Map<String, Long> info = ts.getInfo();
            return new ResponseEntity<>(info, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
