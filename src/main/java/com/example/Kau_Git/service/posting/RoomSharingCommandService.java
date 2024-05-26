package com.example.Kau_Git.service.posting;

import com.example.Kau_Git.dto.GetS3Res;
import com.example.Kau_Git.dto.roomSharing.PostRoomSharingRequestDto;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.entity.User;
import com.example.Kau_Git.repository.PostingRepository;
import com.example.Kau_Git.repository.UserRepository;
import com.example.Kau_Git.service.AbstractPostingService;
import com.example.Kau_Git.service.FilesService;
import com.example.Kau_Git.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomSharingCommandService extends AbstractPostingService {
    private final PostingRepository postingRepository;
    private final UserRepository userRepository;
    private final S3Service s3Service;
    private final FilesService filesService;

    private void save(Posting post){ //private 제어자는 해당 멤버를 선언한 클래스 내에서만 접근할 수 있도록 제한한다.
        postingRepository.save(post);
    }

    public Posting registSharing(PostRoomSharingRequestDto.RegistPostDto registPost, List<MultipartFile> multipartFiles, Long userId){
        User user = userRepository.findByUserId(userId);
        Posting posting = Posting.builder()
                .title(registPost.getTitle())
                .content(registPost.getContent())
                .writer(user)
                .classification('S')
                .fileList(new ArrayList<>())
                .build();
        save(posting);
        List<GetS3Res> getS3Res = uploadFilesToS3(multipartFiles);
        saveActivityFiles(getS3Res, posting);

        return posting;
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
