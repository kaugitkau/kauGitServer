package com.example.Kau_Git.service;

import com.example.Kau_Git.dto.pheed.PheedResponseDto;
import com.example.Kau_Git.entity.Files;
import com.example.Kau_Git.entity.Hashtag;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.repository.CommentRepository;
import com.example.Kau_Git.repository.PheedHashtagRepository;
import com.example.Kau_Git.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PheedQueryService {
    private final PostingRepository postingRepository;
    private final CommentRepository commentRepository;
    private final PheedHashtagRepository pheedHashtagRepository;


    public PheedResponseDto.ListPheedDto getPheeds() {
        List<Posting> pheeds = postingRepository.findAllByClassification('p');
        List<PheedResponseDto.PheedDto> pheedDtoList = pheeds.stream()
                .map(this::convertToPheedDto)
                .collect(Collectors.toList());

        return PheedResponseDto.ListPheedDto.builder()
                .pheedDtoList(pheedDtoList)
                .build();
    }
    private PheedResponseDto.PheedDto convertToPheedDto(Posting pheed) {
        Integer commentCnt = commentRepository.countByPosting_PostingId(pheed.getPostingId());
        List<String> fileUrls = extractFileUrls(pheed);
        List<String> hashtags = extractHashtags(pheed);
        return PheedResponseDto.PheedDto.builder()
                .postingId(pheed.getPostingId())
                .writer(pheed.getWriter().getNickname())
                .createdAt(pheed.getCreatedAt())
                .content(pheed.getContent())
                .fileUrls(fileUrls)
                .hashtags(hashtags)
                .commentCnt(commentCnt)
                .recommendedCnt(pheed.getRecommendedCnt())
                .build();
    }

    public List<String> extractFileUrls(Posting pheed) {
        return pheed.getFileList().stream()
                .map(Files::getFileUrl)
                .collect(Collectors.toList());
    }

    public List<String> extractHashtags(Posting pheed){
        List<Hashtag> hashtagsByPosting = pheedHashtagRepository.findHashtagsByPosting(pheed);
        return hashtagsByPosting.stream()
                .map(Hashtag::getWord)
                .collect(Collectors.toList());
    }
}
