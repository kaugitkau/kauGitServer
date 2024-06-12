package com.example.Kau_Git.controller;

import com.example.Kau_Git.service.*;
import com.example.Kau_Git.service.GetInfoService;
import com.example.Kau_Git.service.KeywordSearchService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import java.awt.geom.Area;
import java.security.Key;
import java.util.List;
import java.util.Map;

@Controller
public class MapController {

    private final GetInfoService gs;
    private final KeywordSearchService ks;

    @Autowired
    public MapController(GetInfoService gs ,KeywordSearchService ks){
        this.gs =gs;
        this.ks=ks;
    }


    @GetMapping("/map")
    public String map(){
        return "map";
    }
    
    @PostMapping("/getCoordinates")
    public ResponseEntity<List<JSONObject>> getCoordinates(@RequestParam("lat") String latitude, @RequestParam("lng") String longitude) {
        try {
            List<JSONObject> info = gs.getInfo(latitude, longitude);
            return new ResponseEntity<>(info, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/getCode")
    public ResponseEntity<Map<String, String>> getCode(@RequestParam("areaCode") int areaCode) {
        try {
            Map<String, String> info = ks.getAreaInfo(areaCode);
            return new ResponseEntity<>(info, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/getKeywords")
    public ResponseEntity<Map<String, String>> getKeyword(@RequestParam("areaCode") int areaCode,@RequestParam("sigunguCode") int sigunguCode, @RequestParam("keyword")String keyword){
        try {
            Map<String, String> Info = (Map<String, String>) ks.getInfo(areaCode,sigunguCode,keyword);
            return new ResponseEntity<>(Info, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




}
