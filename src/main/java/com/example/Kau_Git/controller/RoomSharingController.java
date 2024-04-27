package com.example.Kau_Git.controller;

import com.example.Kau_Git.Oauth.Login;
import com.example.Kau_Git.Oauth.SessionUser;
import com.example.Kau_Git.dto.roomSharing.PostRoomSharingRequestDto;
import com.example.Kau_Git.dto.roomSharing.PostRoomSharingResponseDto;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.entity.User;
import com.example.Kau_Git.repository.UserRepository;
import com.example.Kau_Git.service.RoomSharingCommandService;
import com.example.Kau_Git.service.RoomSharingQueryService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RoomSharingController {

    private final RoomSharingCommandService roomSharingCommandService;
    private final RoomSharingQueryService roomSharingQueryService;


    private final Long testId = 1L;


    @PostMapping("/roomShare/regist")
    public Long addSharingPost(@RequestBody PostRoomSharingRequestDto.RegistPostDto registPostDto) {
//        SessionUser user = (SessionUser) httpSession.getAttribute("user"); //이건 일단 남겨놓음.
        Posting posting = roomSharingCommandService.registSharing(registPostDto, testId);
        return posting.getPostId();
    }

    @GetMapping("/roomShare/{postingId}")//오류발생: name과 변수명이 동일한 경우에는 생략 가능하지 않나? -> 일단 아닌걸로 하자.
    // 컴파일 시에는 debugging enabled가 되어야 스프링이 찾을 수 있다.
    public PostRoomSharingResponseDto.PostResultDto showPost(@PathVariable (name = "postingId") Long postingId) {

        return roomSharingQueryService.showPosting(postingId);
    }

    @GetMapping("/roomShare/allpost")
    public PostRoomSharingResponseDto.AllPostDto showAllPost(@Login SessionUser sessionUser) {
        System.out.println(sessionUser.getUserId());
        return roomSharingQueryService.showAllPosting();
    }
//
//    @PostMapping("/roomShare/regist2")  //@Login 이방식으로 추후에 할계획임.
//    public String addSharingPost2(@Login SessionUser sessionUser) {
//        System.out.println(sessionUser.getUserId());
//        Posting posting = roomSharingCommandService.registSharing(registPostDto);
//        return posting.getPostId();
//        return "good";
//    }


}
