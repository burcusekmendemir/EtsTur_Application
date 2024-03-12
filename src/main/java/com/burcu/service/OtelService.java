package com.burcu.service;


import com.burcu.domain.*;
import com.burcu.dto.request.*;
import com.burcu.dto.response.OtelSaveResponseDto;
import com.burcu.exception.ErrorType;
import com.burcu.exception.OtelException;
import com.burcu.mapper.OtelMapper;
import com.burcu.repository.OtelRepository;
import com.burcu.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OtelService extends ServiceManager<Otel, String> {

    private final OtelRepository otelRepository;
    private final CategoryService categoryService;
    private final PropertiesService propertiesService;
    private final CategoryPropertiesService categoryPropertiesService;
    private final AddressService addressService;
    private final RoomService roomService;
    private final ImageService imageService;
    public OtelService(OtelRepository otelRepository, CategoryService categoryService, PropertiesService propertiesService, CategoryPropertiesService categoryPropertiesService, AddressService addressService, RoomService roomService, ImageService imageService) {
        super(otelRepository);
        this.otelRepository = otelRepository;
        this.categoryService = categoryService;
        this.propertiesService = propertiesService;
        this.categoryPropertiesService = categoryPropertiesService;
        this.addressService = addressService;
        this.roomService = roomService;
        this.imageService = imageService;
    }

    public OtelSaveResponseDto saveOtel(OtelSaveRequestDto dto) {
        Optional<Otel> otelOptional=otelRepository.findByNameAndAddressId(dto.getName(),dto.getAddressId());
        if (otelOptional.isPresent()){
            throw new OtelException(ErrorType.OTEL_ALREADY_EXISTS);
        }

        Otel otel= OtelMapper.INSTANCE.fromOtelSaveRequestDtoToOtel(dto);
        save(otel);
        return OtelMapper.INSTANCE.fromOtelToOtelSaveResponseDto(otel);
    }
    public Category saveCategory(CategorySaveRequestDto dto) {
        return categoryService.save(dto);
    }

    public Properties saveProperties(PropertiesSaveRequestDto dto) {
        return propertiesService.save(dto);
    }

    public Category_Properties saveCategoryProperties(CategoryPropertiesSaveRequestDto dto) {
        return categoryPropertiesService.save(dto);
    }

    public Address saveAddress(AddressSaveRequestDto dto) {
        return addressService.save(dto);
    }

    public Room saveRoom(RoomSaveRequestDto dto) {
        return roomService.save(dto);
    }

    public Image saveImage(ImageSaveRequestDto dto) {
        return imageService.save(dto);
    }


}
