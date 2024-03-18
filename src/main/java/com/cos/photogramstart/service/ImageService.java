package com.cos.photogramstart.service;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class ImageService {

    private final ImageRepository imageRepository;

    @Transactional(readOnly = true) // 영속성 컨텍스트에서 변경감지를 해서, 더티체킹, flush(반영), 읽기 전용이기 때문에 데이터베이스 반영을 하지 않아 성능이 좋아짐. 하지만 트랜젝션을 걸어서 세션을 컨트롤러 단까지 끌고 오는 것은 매우 중요함,
    public Page<Image> imageStory(int principalId, Pageable pageable) {

        Page<Image> images = imageRepository.mStory(principalId, pageable);

        // 2(cos) 로 로그인한 상태라면
        // images 에 좋아요 상태 담아가기
        images.forEach((image -> {

            image.setLikeCount(image.getLikes().size());

            image.getLikes().forEach((like -> {
                if (like.getUser().getId() == principalId) {
                    image.setLikeState(true);
                }
            }));
        }));

        return images;
    }

    // yml 값 가져오기
    @Value("${file.path}")
    private String uploadFolder;

    @Transactional
    public void imageUpload(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
        UUID uuid = UUID.randomUUID(); // uuid
        String imageFileName = uuid + "_" + imageUploadDto.getFile().getOriginalFilename(); // ex) 1.jpg
        System.out.println("이미지 파일 이름 : " + imageFileName);

        Path imageFilePath = Paths.get(uploadFolder + imageFileName);

        // IO, 통신 -> 예외 발생할 수 있다. 런타임 에러 예외처리
        try {
            Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser());
        Image imageEntity = imageRepository.save(image);

//        log.info("imageEntity = {}" ,imageEntity);

//        System.out.println("이미지 엔티티 조회 : " + imageEntity); // 오류가 발생함.


    }
}
