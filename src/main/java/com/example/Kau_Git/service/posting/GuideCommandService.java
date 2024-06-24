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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GuideCommandService extends AbstractPostingService {

    private final PostingRepository postingRepository;
    private final GuideMatchingRepository guideMatchingRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public boolean checkUserPoints(Long userId, int requiredPoints) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found with id: " + userId);
        }
        return user.getPoint() >= requiredPoints;
    }

    @Transactional
    public void registGuiding(GuideRequestDto.RegistGuidePostingDto registGuidePostingDto, Long userId) {
        //userRepository에서 작성자를 찾아옴
        User writer = userRepository.findByUserId(userId);
        //Entity 생성
        Posting build = Posting.builder()
                .writer(writer) //작성자
                .title(registGuidePostingDto.getTitle()) //제목
                .content(registGuidePostingDto.getContent()) //
                .classification('M') //분류
                .build();
        postingRepository.save(build);

    }
    //Matching 요청을 DB에 저장
    @Transactional
    public void applyMatching(Long applicantId, Long respondentId){
        //신청자 ID를 찾아옴
        User applicant = userRepository.findByUserId(applicantId);
        //가이드 ID를 찾아옴
        User respondent = userRepository.findByUserId(respondentId);
        //ApplicantRespondent 생성
        ApplicantRespondent build = ApplicantRespondent.builder()
                .applicant(applicant)
                .respondent(respondent)
                .status(GuideMatchingStatus.WAITING)
                .build();
        guideMatchingRepository.save(build);//생성후 저장
    }

    //Matching을 수락하는 경우
    @Transactional
    public void acceptMatching(Long guideMatchingId) {
        ApplicantRespondent matching = guideMatchingRepository.findById(guideMatchingId)
                .orElseThrow(() -> new IllegalArgumentException("Matching not found with id: " + guideMatchingId));

        matching.changeStatus(GuideMatchingStatus.ACCEPTED);
        guideMatchingRepository.save(matching);
    }


    @Transactional(readOnly = true)
    public List<ApplicantRespondent> getMatchingList(Long guideId) {

        User guide = userRepository.findByUserId(guideId);

        return guideMatchingRepository.findAllByRespondent(guide);
    }
}
