package com.example.Kau_Git.service;

import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.repository.PostingRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostingService {
    private final PostingRepository postingRepository;


    public SearchResultListDto searchByTitle(String title){
        List<Posting> allByTitle = postingRepository.findAllByTitleContaining(title);

        List<SearchResultDto> searchResultDtoList = new ArrayList<>();
        for (Posting p:allByTitle){
            SearchResultDto build = SearchResultDto.builder()
                    .title(p.getTitle())
                    .shortContent(makeShortContent(p.getContent()))
                    .postingId(p.getPostingId())
                    .createdDate(p.getCreatedAt())
                    .build();
            searchResultDtoList.add(build);

        }

        SearchResultListDto list = SearchResultListDto.builder()
                .searchResultDtoList(searchResultDtoList)
                .build();

        return list;

    }

    public String makeShortContent(String content){
        if (content.length()>20){
            return content.substring(0, 20);
        }
        else return content;
    }

    @Getter
    @Builder
    public static class SearchResultDto{
        Long postingId;
        String title;
        String shortContent;

        LocalDateTime createdDate;
    }





    @Getter
    @Builder
    public static class SearchResultListDto{
        List<SearchResultDto> searchResultDtoList;

    }


    @Transactional
    public void decrementLikeCount(Long postingId) {
        Posting posting = postingRepository.findById(postingId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid posting Id"));
        posting.decrementRecommendedCnt();
        postingRepository.save(posting); // 변경 사항을 저장하여 반영
    }

}





