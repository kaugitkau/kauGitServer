package com.example.Kau_Git.service;

import com.example.Kau_Git.dto.GetS3Res;
import com.example.Kau_Git.entity.Files;
import com.example.Kau_Git.entity.Posting;
import com.example.Kau_Git.repository.FilesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FilesService {
    private final FilesRepository filesRepository;
    @Transactional
    public void saveActivityFile(List<Files> files) {
        filesRepository.saveAll(files);
    }
    @Transactional
    public void saveAllActivityFileByActivity(List<GetS3Res> getS3ResList, Posting posting) {
        // ActivityFile 리스트를 받아옴
        List<Files> files = new ArrayList<>();
        for (GetS3Res getS3Res : getS3ResList) {
            Files newFile = Files.builder()
                    .fileUrl(getS3Res.getFileUrl())
                    .fileName(getS3Res.getFileName())
                    .posting(posting)
                    .build();
            files.add(newFile);
            posting.addToFileList(newFile);
        }
        saveActivityFile(files);

    }
}
