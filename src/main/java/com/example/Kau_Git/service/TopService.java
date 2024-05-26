package com.example.Kau_Git.service;

import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.entity.User;
import com.example.Kau_Git.repository.PostingRepository;
import com.example.Kau_Git.repository.UserRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopService {
    private final UserRepository userRepository;
    private final PostingRepository postingRepository;

    public ListTopMentorsDto findTopMentors(){
        List<User> top3Mentors = userRepository.findTop3ByOrderByMentoringAvgRatedDesc();
        List<TopMentorDto> list = top3Mentors.stream()

                .map(mentor -> TopMentorDto.builder()
                        .name(mentor.getName())
                        .region(mentor.getAddress())
                        .religion(mentor.getReligion())
                        .build())
                .toList();
        ListTopMentorsDto build = ListTopMentorsDto.builder()
                .topMentorDtos(list)
                .build();
        return build;

    }

    public ListTopHousingsDto findTopHousings() {
        List<Posting> top3HousePostings = postingRepository.findTop3ByWeightedScore();

        List<TopHousingDto> list = top3HousePostings.stream()
                .map(posting -> {
                    String firstFileUrl = null;
                    if (!posting.getFileList().isEmpty()) {
                        firstFileUrl = posting.getFileList().get(0).getFileUrl(); // 첫 번째 파일의 URL 가져오기
                    }
                    return TopHousingDto.builder()
                            .name(posting.getTitle())
                            .region(posting.getWriter().getAddress())
                            .sharingAvgRated(posting.getWriter().getSharingAvgRated())
                            .fileUrl(firstFileUrl)
                            .build();
                        })
                .collect(Collectors.toList());

        ListTopHousingsDto build = ListTopHousingsDto.builder()
                .topHousingDtos(list)
                .build();
        return build;


    }


    public ListHotCommunityPostingsDto findHotPostings() {
        List<Posting> hotPostings = postingRepository.findHotPostings();

        List<HotCommunityPostingDto> collect = hotPostings.stream()
                .map(posting -> HotCommunityPostingDto.builder()
                        .recommendedCnt(posting.getRecommendedCnt())
                        .createdAt(posting.getCreatedAt())
                        .writer(posting.getWriter().getName())
                        .title(posting.getTitle())
                        .commentCnt(posting.getCommentCnt())
                        .build())
                .collect(Collectors.toList());

        ListHotCommunityPostingsDto build = ListHotCommunityPostingsDto.builder()
                .hotCommunityPostingDtos(collect)
                .build();
        return build;

    }


    @Getter
    @Builder
    public static class ListTopMentorsDto{
        List<TopMentorDto> topMentorDtos;
    }

    @Builder
    @Getter
    public static class TopMentorDto {
        String name;
        String region;
        String religion;
    }

    @Getter
    @Builder
    public static class ListTopHousingsDto{
        List<TopHousingDto> topHousingDtos;
    }

    @Builder
    @Getter
    public static class TopHousingDto {
        String name;
        String region;
        Double sharingAvgRated;
        String fileUrl;


    }

    @Builder
    @Getter
    public static class HotCommunityPostingDto{
        String title;
        String writer;
        Integer commentCnt;
        Integer recommendedCnt;
        LocalDateTime createdAt;


    }
    @Builder
    @Getter
    public static class ListHotCommunityPostingsDto{
        List<HotCommunityPostingDto> hotCommunityPostingDtos;

    }




}
