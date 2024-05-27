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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SharingController {

    private final RoomSharingCommandService roomSharingCommandService;
    private final RoomSharingQueryService roomSharingQueryService;

    //쉐어링 글 등록
    @PostMapping("/roomShare")
    public Long addSharingPost(@RequestPart(value = "sharingDto") PostRoomSharingRequestDto.RegistPostDto registPostDto,
                               @RequestPart(value = "image", required = false) List<MultipartFile> multipartFiles,
                               @Login SessionUser sessionUser) {//이건 일단 남겨놓음.
        Long userId = sessionUser.getUserId();
        Posting posting = roomSharingCommandService.registSharing(registPostDto,multipartFiles,  userId);
        return posting.getPostingId();
    }

    //쉐어링 글 상세내용 조회
    @GetMapping("/roomShare/{postingId}")//오류발생: name과 변수명이 동일한 경우에는 생략 가능하지 않나? -> 일단 아닌걸로 하자.
    // 컴파일 시에는 debugging enabled가 되어야 스프링이 찾을 수 있다.
    public PostRoomSharingResponseDto.PostResultDto showPost(@PathVariable (name = "postingId") Long postingId) {

        return roomSharingQueryService.showPosting(postingId);
    }

    //쉐어링 글 목록 조회
    @GetMapping("/roomShare/allpost")
    public PostRoomSharingResponseDto.AllPostDto showAllPost(@Login SessionUser sessionUser) {
        System.out.println(sessionUser.getUserId());
        return roomSharingQueryService.showAllPosting();
    }


}
