package com.example.Kau_Git.controller.posting;

import com.example.Kau_Git.Oauth.Login;
import com.example.Kau_Git.Oauth.SessionUser;
import com.example.Kau_Git.dto.roomSharing.PostRoomSharingRequestDto;
import com.example.Kau_Git.dto.roomSharing.PostRoomSharingResponseDto;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.service.posting.RoomSharingCommandService;
import com.example.Kau_Git.service.posting.RoomSharingQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SharingController {

    private final RoomSharingCommandService roomSharingCommandService;
    private final RoomSharingQueryService roomSharingQueryService;

    @PostMapping("/roomShare")
    public Long addSharingPost(@RequestBody PostRoomSharingRequestDto.RegistPostDto registPostDto,
                               @Login SessionUser sessionUser) {//이건 일단 남겨놓음.
        Long userId = sessionUser.getUserId();
        Posting posting = roomSharingCommandService.registSharing(registPostDto, userId);
        return posting.getPostingId();
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


}
