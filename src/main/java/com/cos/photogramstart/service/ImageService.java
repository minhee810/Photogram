package com.cos.photogramstart.service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class ImageService {

    private final ImageRepository imageRepository;

    // yml 값 가져오기
    @Value("${file.path}")
    private String uploadFolder;

    public void imageUpload(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
        UUID uuid = UUID.randomUUID(); // uuid
        String imageFileName = uuid + "_" + imageUploadDto.getFile().getOriginalFilename(); // ex) 1.jpg
        System.out.println("이미지 파일 이름 : " + imageFileName);

        Path imageFilePath = Paths.get(uploadFolder+imageFileName);

        // IO, 통신 -> 예외 발생할 수 있다. 런타임 에러 예외처리
        try {
            Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser());
        Image imageEntity = imageRepository.save(image);

        log.info("imageEntity = {}" ,imageEntity);

    }
}
