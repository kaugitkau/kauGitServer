package com.example.Kau_Git.Service;

import com.example.Kau_Git.service.TravelNumService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@SpringBootTest
public class NumTest {
    @Autowired
    private RestTemplateBuilder rb;

    @Autowired
    private TravelNumService ts;

    @Test
    public void ttest(){
        RestTemplate rt = rb.build();

        var info = ts.getInfo();

        for(Map.Entry<String, Long> entry : info.entrySet()){
            System.out.println("지자체: " + entry.getKey() + ", 관광객 수: " + entry.getValue());
        }

    }


}
