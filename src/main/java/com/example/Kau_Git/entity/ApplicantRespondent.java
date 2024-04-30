package com.example.Kau_Git.entity;

import com.example.Kau_Git.entity.enums.GuideMatchingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantRespondent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APPLICANTRESPONDENT_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "APPLICANT_ID")
    private User applicant;

    @ManyToOne
    @JoinColumn(name = "RESPONDENT_ID")
    private User respondent;

    private String reason;



    @Column(name = "status")
    private GuideMatchingStatus status;

    @CreatedDate
    @Column(name = "match_date")
    private LocalDateTime matchDate;

    public void changeStatus(GuideMatchingStatus guideMatchingStatus) {
        status = guideMatchingStatus;
    }

}
