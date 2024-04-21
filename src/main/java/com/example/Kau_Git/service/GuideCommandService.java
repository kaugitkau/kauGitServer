package com.example.Kau_Git.service;


import com.example.Kau_Git.dto.guide.GuideRequestDto;
import com.example.Kau_Git.entity.GuideMatching;
import com.example.Kau_Git.entity.GuideMatchingStatus;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.entity.User;
import com.example.Kau_Git.repository.GuideMatchingRepository;
import com.example.Kau_Git.repository.PostingRepository;
import com.example.Kau_Git.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GuideCommandService {

    private final PostingRepository postingRepository;
    private final GuideMatchingRepository guideMatchingRepository;
    private final UserRepository userRepository;

    public void registGuiding(GuideRequestDto.RegistGuidePostingDto registGuidePostingDto, Long userId) {
        Posting build = Posting.builder()
                .title(registGuidePostingDto.getTitle())
                .content(registGuidePostingDto.getContent())
                .classification('S')
                .build();
        postingRepository.save(build);

    }

    public void applyMatching(Long applicantId, Long guideId){
        User applicant = userRepository.findByUserId(applicantId);
        User guide = userRepository.findByUserId(guideId);
        GuideMatching build = GuideMatching.builder()
                .applicant(applicant)
                .guide(guide)
                .status(GuideMatchingStatus.WAITING)
                .build();
        guideMatchingRepository.save(build);

    }
    public void acceptMatching(Long guideMatchingId){
        Optional<GuideMatching> byId = guideMatchingRepository.findById(guideMatchingId);//예외처리 필요
        byId.get().changeStatus(GuideMatchingStatus.ACCEPTED);

    }

    public List<GuideMatching> getMatchingList(Long guideId) {
        User guide = userRepository.findByUserId(guideId);
        return guideMatchingRepository.findAllByGuide(guide);
    }
}
