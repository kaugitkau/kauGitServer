package com.example.Kau_Git.service.posting;

import com.example.Kau_Git.dto.GetS3Res;
import com.example.Kau_Git.dto.pheed.PheedRequestDto;
import com.example.Kau_Git.entity.Hashtag;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.entity.PostingHashtag;
import com.example.Kau_Git.entity.User;
import com.example.Kau_Git.repository.HashtagRepository;
import com.example.Kau_Git.repository.PostingHashtagRepository;
import com.example.Kau_Git.repository.PostingRepository;
import com.example.Kau_Git.repository.UserRepository;
import com.example.Kau_Git.service.FilesService;
import com.example.Kau_Git.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PheedCommandService {
    private final PostingRepository postingRepository;
    private final HashtagRepository hashtagRepository;
    private final UserRepository userRepository;
    private final S3Service s3Service;
    private final FilesService filesService;
    private final PostingHashtagRepository postingHashtagRepository;

    public Posting createPosting(PheedRequestDto.MakePostingDto makePostingDto, List<MultipartFile> multipartFiles, Long writerId){

        User user = userRepository.findByUserId(writerId);

        Posting posting = Posting.builder()
                .writer(user)
                .title(makePostingDto.getContent())
                .content(makePostingDto.getContent())
                .classification('P')
                .fileList(new ArrayList<>())
                .build();



        save(posting);
        if (multipartFiles != null) {
            List<GetS3Res> imgUrls = uploadFilesToS3(multipartFiles);
            saveActivityFiles(imgUrls, posting);
        }
        List<String> hashtags = makePostingDto.getHashtags();
        if (hashtags!=null) saveHashtag(posting, hashtags);

        return posting;
    }

    public void save(Posting posting) {
        postingRepository.save(posting);
    }
    private List<GetS3Res> uploadFilesToS3(List<MultipartFile> multipartFiles){
        // S3 파일 업로드 로직 구현
        return s3Service.uploadFile(multipartFiles);
    }

    private void saveActivityFiles(List<GetS3Res> imgUrls, Posting posting) {
        // ActivityFile 저장 로직 구현
        filesService.saveAllActivityFileByActivity(imgUrls, posting);
    }

    private void saveHashtag(Posting posting, List<String> hashtags) {
        hashtags.stream()
                .forEach(tag -> {
                    Optional<Hashtag> existingHashtag = hashtagRepository.findByWord(tag);
                    Hashtag hashtag;
                    if (existingHashtag.isPresent()) {
                        hashtag = existingHashtag.get();
                    } else {
                        hashtag = Hashtag.builder()
                                .word(tag)
                                .build();
                        hashtag = hashtagRepository.save(hashtag);
                    }

                    PostingHashtag postingHashtag = new PostingHashtag(posting, hashtag);
                    postingHashtagRepository.save(postingHashtag);
                });

    }
}
