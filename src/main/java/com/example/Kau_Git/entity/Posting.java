package com.example.Kau_Git.entity;

import com.example.Kau_Git.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "POSTING")
@Builder
@AllArgsConstructor
public class Posting extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long postId;

    @Column(name = "TYPE", length = 1)
    private char type;

    @Column(name = "POST_ORD")
    private Short postOrd;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "CLASSIFICATION", length = 1)
    private char classification;

    @Column(name = "CONTENT", length = 4000)
    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "WRITER_ID", referencedColumnName = "USERID", foreignKey = @ForeignKey(name = "FK_WRITER_ID"))
    private User writer;

    @Column(name = "HASHTAG", length = 1000)
    private String hashtag;

    @Column(name = "REPORT_CNT")
    private Short reportCnt;


    private Integer viewCnt;

    private Integer recommendedCnt=0;

    @OneToMany(mappedBy = "posting", cascade = CascadeType.ALL)
    private List<Files> fileList = new ArrayList<>();

    public void addFileList(Files files) {
        fileList.add(files);
        files.createPosting(this);

    }

    @Column(name = "IS_HIDE", nullable = false)
    private boolean isHide = false;

    public void decrementRecommendedCnt(){
        this.recommendedCnt-=1;
    }

    public void incrementRecommendedCnt(){
        this.recommendedCnt+=1;
    }
}
