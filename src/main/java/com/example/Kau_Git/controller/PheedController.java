package com.example.Kau_Git.controller;

import com.example.Kau_Git.dto.sns.SnsRequestDto;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.service.SnsCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PheedController {

    private final SnsCommandService snsCommandService;

    private final Long testId = 1L;
    @PostMapping("/sns/regist")
    public Long addSnsPost(@RequestBody SnsRequestDto.MakePostingDto makePostingDto,
                           @RequestPart(value = "image", required = false) List<MultipartFile> multipartFiles) {
//        SessionUser user = (SessionUser) httpSession.getAttribute("user"); //이건 일단 남겨놓음.
        Posting posting = snsCommandService.createPosting(makePostingDto, multipartFiles, testId  );
        return posting.getPostId();
    }

}
