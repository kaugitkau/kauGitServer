package com.example.Kau_Git.service;

import com.example.Kau_Git.service.GetFestivalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

@SpringBootTest
public class GetFestivalTest {
    @Autowired
    private GetFestivalService gf;
    @Autowired
    private RestTemplateBuilder rb;

    @Test
    public void gftest() throws UnsupportedEncodingException, URISyntaxException {
        RestTemplate rt = rb.build();

        // FestivalService 클래스 내부에서 사용되는 RestTemplate 인스턴스를 테스트용으로 교체

        var festivals = gf.getFestival();

        // 결과 출력 (실제로는 더 구체적인 검증 로직을 추가해야 할 수 있습니다.)
        // 결과 출력
        for (var festival : festivals) {
            System.out.println("주소: " + festival.get("addr1"));
            System.out.println("제목: " + festival.get("title"));
            System.out.println("이미지 URL: " + festival.get("firstimage"));
            System.out.println("---------------------------");
        }
    }

}