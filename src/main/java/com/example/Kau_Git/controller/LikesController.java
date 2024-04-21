package com.example.Kau_Git.controller;

import com.example.Kau_Git.service.LikesCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikesController {

    private final LikesCommandService likesCommandService;
}
