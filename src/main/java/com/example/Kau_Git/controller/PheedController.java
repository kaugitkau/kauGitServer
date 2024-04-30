package com.example.Kau_Git.controller;

import com.example.Kau_Git.Oauth.Login;
import com.example.Kau_Git.Oauth.SecurityConfig;
import com.example.Kau_Git.Oauth.SessionUser;
import com.example.Kau_Git.dto.pheed.PheedRequestDto;
import com.example.Kau_Git.dto.pheed.PheedResponseDto;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.service.PheedQueryService;
import com.example.Kau_Git.service.SnsCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PheedController {

    private final SnsCommandService snsCommandService;
    private final PheedQueryService pheedQueryService;

    @PostMapping("/pheed/regist")
    public Long addPheed(@RequestPart(value = "pheedDto") PheedRequestDto.MakePostingDto makePostingDto,
                           @RequestPart(value = "image", required = false) List<MultipartFile> multipartFiles,
                           @Login SessionUser sessionUser) {

        Long userId = sessionUser.getUserId();
        Posting posting = snsCommandService.createPosting(makePostingDto, multipartFiles, userId);
        return posting.getPostingId();
    }

    @GetMapping("/pheed")
    public PheedResponseDto.ListPheedDto getPheeds() {

        return pheedQueryService.getPheeds();

    }

}
