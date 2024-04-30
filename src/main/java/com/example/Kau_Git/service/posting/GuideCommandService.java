package com.example.Kau_Git.service.posting;


import com.example.Kau_Git.dto.guide.GuideRequestDto;
import com.example.Kau_Git.entity.ApplicantRespondent;
import com.example.Kau_Git.entity.enums.GuideMatchingStatus;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.entity.User;
import com.example.Kau_Git.repository.GuideMatchingRepository;
import com.example.Kau_Git.repository.PostingRepository;
import com.example.Kau_Git.repository.UserRepository;
import com.example.Kau_Git.service.AbstractPostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GuideCommandService extends AbstractPostingService {

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

    public void applyMatching(Long applicantId, Long respondentId){
        User applicant = userRepository.findByUserId(applicantId);
        User respondent = userRepository.findByUserId(respondentId);
        ApplicantRespondent build = ApplicantRespondent.builder()
                .applicant(applicant)
                .respondent(respondent)
                .status(GuideMatchingStatus.WAITING)
                .build();
        guideMatchingRepository.save(build);

    }
    public void acceptMatching(Long guideMatchingId){
        Optional<ApplicantRespondent> byId = guideMatchingRepository.findById(guideMatchingId);//예외처리 필요
        byId.get().changeStatus(GuideMatchingStatus.ACCEPTED);

    }

    public List<ApplicantRespondent> getMatchingList(Long guideId) {
        User guide = userRepository.findByUserId(guideId);
        return guideMatchingRepository.findAllByRespondent(guide);
    }
}
