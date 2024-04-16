package com.example.Kau_Git.controller;

import com.example.Kau_Git.dto.roomSharing.PostRoomSharingRequestDto;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.service.RoomSharingCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RoomSharingController {

    private final RoomSharingCommandService roomSharingCommandService;

    @PostMapping("/roomShare/regist")
    public Long addSharingPost(PostRoomSharingRequestDto.RegistPostDto registPostDto) {
        Posting posting = roomSharingCommandService.registSharing(registPostDto);
        return posting.getPostId();
    }

}
