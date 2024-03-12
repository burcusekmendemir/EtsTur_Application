package com.burcu.service;


import com.burcu.domain.Image;
import com.burcu.dto.request.ImageSaveRequestDto;
import com.burcu.exception.ErrorType;
import com.burcu.exception.OtelException;
import com.burcu.repository.ImageRepository;
import com.burcu.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageService extends ServiceManager<Image, String> {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        super(imageRepository);
        this.imageRepository = imageRepository;
    }

    public Image save(ImageSaveRequestDto dto) {
        Optional<Image> imageOptional = imageRepository.findByName(dto.getName());
        if (imageOptional.isPresent()){
            throw new OtelException(ErrorType.IMAGE_ALREADY_EXISTS);
        }
        Image image = Image.builder()
                .roomId(dto.getRoomId())
                .url(dto.getUrl())
                .category(dto.getCategory())
                .build();
        return save(image);
    }
}
