package com.example.Kau_Git.service;

import com.example.Kau_Git.dto.pheed.PheedResponseDto;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PheedQueryService {
    private final PostingRepository postingRepository;

    public PheedResponseDto.ListPheedDto getPheeds(){
        List<Posting> p = postingRepository.findAllByClassification('p');
        return PheedResponseDto.ListPheedDto.builder().build();


    }
}
