package com.example.Kau_Git.entity;

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
public class GuideMatching{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "guide_id")
    private User guide;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private User applicant;

    @Column(name = "status")
    private GuideMatchingStatus status;

    @CreatedDate
    @Column(name = "match_date")
    private LocalDateTime matchDate;

    public void changeStatus(GuideMatchingStatus guideMatchingStatus) {
        status = guideMatchingStatus;
    }

}
