package com.example.Kau_Git.dto.guide;

import com.example.Kau_Git.dto.PostingProfileDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class GuideResponseDto {
    @Builder
    @Getter
    public static class ShowPostingDto{
        //제목 / 프로필 (이름, 사진, 종교, 가능 언어 (여러 개일 수도), 좌우명) / 프로필 누르면
        // 멘토프로필로 이동(그간의 활동을 신청하는 사람이 대강이라도 확인할수있도록) / 지역 / 본문 /평균 평점 / 신청 버튼
        // (여기에서 기록 남김 →                                                                                                                                           이걸 눌러야 상대의 연락처(이메일) 보임) / 평점 등록칸 (3달 이내 거래자에게만 할 수 있음) /

        private String title;
        private String content;
        private LocalDateTime createdAt;
        private Long userId;
        private PostingProfileDto postingProfileDto;
    }

    @Builder
    @Getter
    public static class ShowAllPostDto{
        List<GuidePreviewDto> elemDtoList;


    }

    @Builder
    @Getter
    public static class GuidePreviewDto{
        private Long postingId;
        private String nickname;
        private String religion;
        private String language;
        private String region;

    }
}
