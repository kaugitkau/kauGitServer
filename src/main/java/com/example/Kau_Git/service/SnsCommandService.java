package com.example.Kau_Git.service;

import com.example.Kau_Git.dto.GetS3Res;
import com.example.Kau_Git.dto.pheed.PheedRequestDto;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.entity.User;
import com.example.Kau_Git.repository.PostingRepository;
import com.example.Kau_Git.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SnsCommandService {
    private final PostingRepository postingRepository;
    private final UserRepository userRepository;
    private final S3Service s3Service;
    private final FilesService filesService;

    public Posting createPosting(PheedRequestDto.MakePostingDto makePostingDto, List<MultipartFile> multipartFiles, Long writerId){

        User user = userRepository.findByUserId(writerId);

        Posting posting = Posting.builder()
                .writer(user)
                .content(makePostingDto.getContent())
                .classification('P')
                .build();

        save(posting);
        if (multipartFiles != null) {
            List<GetS3Res> imgUrls = uploadFilesToS3(multipartFiles);
            saveActivityFiles(imgUrls, posting);
        }

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
}
