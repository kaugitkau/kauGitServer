package com.example.Kau_Git.service.posting;

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

    //게시물 보여주기
    public GuideResponseDto.ShowPostingDto showPost(Long postId) {
        Posting byPostId = postingRepository.findByPostingId(postId);
        User user = byPostId.getWriter();
        //멘토의 정보를 가지고 있는 DTO
        PostingProfileDto build = PostingProfileDto.builder()
                .nickname(user.getNickname())
                .region(user.getAddress())
                .religion(user.getReligion())
                .language(user.getLanguage())
                .photo(user.getProfileImage())
                .avgRated(user.getMentoringAvgRated())
                .build();
        //멘토가 작성한 글에 대한 DTO
        GuideResponseDto.ShowPostingDto build1 = GuideResponseDto.ShowPostingDto.builder()
                .title(byPostId.getTitle())
                .content(byPostId.getContent())
                .createdAt(byPostId.getCreatedAt())
                .userId(byPostId.getWriter().getUserId())
                .postingProfileDto(build)
                .build();

        return build1;
    }

    //목록에서 모든 모집글 조회하기
    public GuideResponseDto.ShowAllPostDto showAllPost(){
        List<Posting> all = postingRepository.findAllByClassification('M');
        List<GuideResponseDto.GuidePreviewDto> guidePreviewDtoList =
        all.stream()
                .map(a-> GuideResponseDto.GuidePreviewDto.builder()
                        .postingId(a.getPostingId())
                        .language(a.getWriter().getLanguage())
                        .region(a.getWriter().getAddress())
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
