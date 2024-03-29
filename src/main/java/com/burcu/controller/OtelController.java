package com.burcu.controller;

import com.burcu.domain.*;
import com.burcu.dto.request.*;
import com.burcu.dto.response.FilterListResponseDto;
import com.burcu.dto.response.OtelHomePageResponseDto;
import com.burcu.dto.response.OtelSaveResponseDto;
import com.burcu.service.CategoryService;
import com.burcu.service.OtelService;
import com.burcu.utility.enums.ERoomType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.burcu.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(OTEL)
public class OtelController {

    private final OtelService otelService;
    private final CategoryService categoryService;


    @PostMapping(SAVE_OTEL)
    @CrossOrigin("*")
    public ResponseEntity<OtelSaveResponseDto> saveOtel(@RequestBody OtelSaveRequestDto dto){
        return ResponseEntity.ok(otelService.saveOtel(dto));
    }

    @PostMapping(SAVE_CATEGORY)
    @CrossOrigin("*")
    public ResponseEntity<Category> saveCategory(@RequestBody CategorySaveRequestDto dto){
        return ResponseEntity.ok(otelService.saveCategory(dto));
    }

    @PostMapping(SAVE_PROPERTIES)
    @CrossOrigin("*")
    public ResponseEntity<Properties> saveProperties(@RequestBody PropertiesSaveRequestDto dto){
        return ResponseEntity.ok(otelService.saveProperties(dto));
    }

    @PostMapping(SAVE_CATEGORY_PROPERTIES)
    @CrossOrigin("*")
    public ResponseEntity<Category_Properties> saveCategoryProperties(@RequestBody CategoryPropertiesSaveRequestDto dto){
        return ResponseEntity.ok(otelService.saveCategoryProperties(dto));
    }

    @PostMapping(SAVE_ADDRESS)
    @CrossOrigin("*")
    public ResponseEntity<Address> saveAddress(@RequestBody AddressSaveRequestDto dto){
        return ResponseEntity.ok(otelService.saveAddress(dto));
    }

    @PostMapping(value = SAVE_ROOM, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @CrossOrigin("*")
    public ResponseEntity<Room> saveRoom(@RequestParam("roomType") ERoomType roomType, @ModelAttribute RoomSaveRequestDto dto){
        return ResponseEntity.ok(otelService.saveRoom(dto));
    }

    @PostMapping(SAVE_IMAGE)
    @CrossOrigin("*")
    public ResponseEntity<Image> saveImage(@RequestBody ImageSaveRequestDto dto){
        return ResponseEntity.ok(otelService.saveImage(dto));
    }

    @PostMapping(SAVE_TAGS)
    @CrossOrigin("*")
    public ResponseEntity<Tags> saveTags(@RequestBody TagsSaveRequestDto dto){
        return ResponseEntity.ok(otelService.saveTags(dto));
    }


    @GetMapping("/filter-list")
    @CrossOrigin("*")
    public ResponseEntity<Map<Properties, List<Properties>>> filterList() {
        return ResponseEntity.ok(otelService.filterList());
    }

    @GetMapping("/find-all-by-point")
    @CrossOrigin("*")
    public ResponseEntity<List<Otel>> findAllByPoint(){
        return ResponseEntity.ok(otelService.findAllByPoint());
    }


    @PostMapping("/find-otel-by-name")
    @CrossOrigin("*")
    public ResponseEntity<List<OtelHomePageResponseDto>> findOtelByName(@RequestParam String name){
        return ResponseEntity.ok(otelService.findOtelByName(name));
    }
    @PostMapping("/find-otel-by-address")
    @CrossOrigin("*")
    public ResponseEntity<List<OtelHomePageResponseDto>> findOtelByAddress(@RequestParam String search){
        return ResponseEntity.ok(otelService.findOtelByAddress(search));
    }

    @GetMapping("/find-otel-by-id")
    @CrossOrigin("*")
    public ResponseEntity<Otel> findOtelById(@RequestParam String id){
        return ResponseEntity.ok(otelService.findOtelById(id));
    }



}
