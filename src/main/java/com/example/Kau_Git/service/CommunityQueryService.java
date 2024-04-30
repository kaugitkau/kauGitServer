package com.example.Kau_Git.service;

import com.example.Kau_Git.dto.community.CommunityResponseDto;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityQueryService {

    private final PostingRepository postingRepository;

    public CommunityResponseDto.PostingDto showPosting(Long postId) {
        Posting byPostId = postingRepository.findByPostingId(postId);
        CommunityResponseDto.PostingDto build = CommunityResponseDto.PostingDto.builder()
                .title(byPostId.getTitle())
                .content(byPostId.getContent())
                .region(byPostId.getWriter().getRegion())
                .viewCnt(byPostId.getViewCnt())
                .recommendedCnt(byPostId.getRecommendedCnt())
                .createdAt(byPostId.getCreatedAt())
                .build();
        return build;

    }

    public CommunityResponseDto.ListDto showList(){
        List<Posting> postings = postingRepository.findAllByClassification('C');
        List<CommunityResponseDto.PreviewDto> collect = postings.stream()
                .map(posting -> CommunityResponseDto.PreviewDto.builder()
                        .title(posting.getTitle())
                        .createdAt(posting.getCreatedAt())
                        .build())
                .toList();
        CommunityResponseDto.ListDto build = CommunityResponseDto.ListDto.builder()
                .previewDtoList(collect)
                .build();
        return build;
    }



}
