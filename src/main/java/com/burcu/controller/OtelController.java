package com.burcu.controller;

import com.burcu.domain.*;
import com.burcu.dto.request.*;
import com.burcu.dto.response.OtelSaveResponseDto;
import com.burcu.service.CategoryService;
import com.burcu.service.OtelService;
import com.burcu.utility.enums.ERoomType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.burcu.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(OTEL)
public class OtelController {

    private final OtelService otelService;
    private final CategoryService categoryService;


    @PostMapping(SAVE_OTEL)
    public ResponseEntity<OtelSaveResponseDto> saveOtel(@RequestBody OtelSaveRequestDto dto){
        return ResponseEntity.ok(otelService.saveOtel(dto));
    }



    @PostMapping(SAVE_CATEGORY)
    public ResponseEntity<Category> saveCategory(@RequestBody CategorySaveRequestDto dto){
        return ResponseEntity.ok(otelService.saveCategory(dto));
    }

    @PostMapping(SAVE_PROPERTIES)
    public ResponseEntity<Properties> saveProperties(@RequestBody PropertiesSaveRequestDto dto){
        return ResponseEntity.ok(otelService.saveProperties(dto));
    }

    @PostMapping(SAVE_CATEGORY_PROPERTIES)
    public ResponseEntity<Category_Properties> saveCategoryProperties(@RequestBody CategoryPropertiesSaveRequestDto dto){
        return ResponseEntity.ok(otelService.saveCategoryProperties(dto));
    }

    @PostMapping(SAVE_ADDRESS)
    public ResponseEntity<Address> saveAddress(@RequestBody AddressSaveRequestDto dto){
        return ResponseEntity.ok(otelService.saveAddress(dto));
    }

    @PostMapping(value = SAVE_ROOM, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Room> saveRoom(@RequestParam("roomType") ERoomType roomType, @ModelAttribute RoomSaveRequestDto dto){
        return ResponseEntity.ok(otelService.saveRoom(dto));
    }

    @PostMapping(SAVE_IMAGE)
    public ResponseEntity<Image> saveImage(@RequestBody ImageSaveRequestDto dto){
        return ResponseEntity.ok(otelService.saveImage(dto));
    }


}
