package com.example.Kau_Git.service;

import com.example.Kau_Git.dto.PostingProfileDto;
import com.example.Kau_Git.dto.guide.GuideRequestDto;
import com.example.Kau_Git.dto.guide.GuideResponseDto;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.entity.User;
import com.example.Kau_Git.repository.PostingRepository;
import com.example.Kau_Git.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class GuideQueryService {
    private final UserRepository userRepository;
    private final PostingRepository postingRepository;

    public GuideResponseDto.ShowPostingDto showPost(Long postId) {
        Posting byPostId = postingRepository.findByPostId(postId);
        User user = byPostId.getWriter();
        PostingProfileDto build = PostingProfileDto.builder()
                .nickname(user.getNickname())
                .region(user.getRegion())
                .religion(user.getReligion())
                .motto(user.getMotto())
                .language(user.getLanguage())
                .photo(user.getProfileImage())
                .avgRated(user.getAvgRated())
                .build();
        GuideResponseDto.ShowPostingDto build1 = GuideResponseDto.ShowPostingDto.builder()
                .title(byPostId.getTitle())
                .content(byPostId.getContent())
                .createdAt(byPostId.getCreatedAt())
                .userId(byPostId.getWriter().getUserId())
                .postingProfileDto(build)
                .build();
        return build1;
    }
    public GuideResponseDto.ShowAllPostDto showAllPost(){
        List<Posting> all = postingRepository.findAllByClassification('G');
        List<GuideResponseDto.GuidePreviewDto> guidePreviewDtoList =
        all.stream()
                .map(a-> GuideResponseDto.GuidePreviewDto.builder()
                        .postingId(a.getPostId())
                        .language(a.getWriter().getLanguage())
                        .region(a.getWriter().getRegion())
                        .nickname(a.getWriter().getNickname())
                        .religion(a.getWriter().getReligion())
                        .build())
                .toList();

        GuideResponseDto.ShowAllPostDto showAllPostDtoBuilder = GuideResponseDto.ShowAllPostDto.builder()
                .elemDtoList(guidePreviewDtoList)
                .build();
        return showAllPostDtoBuilder;


    }
}
