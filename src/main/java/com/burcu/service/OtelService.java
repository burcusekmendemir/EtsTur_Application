package com.burcu.service;


import com.burcu.domain.*;
import com.burcu.domain.Properties;
import com.burcu.dto.request.*;
import com.burcu.dto.response.OtelHomePageResponseDto;
import com.burcu.dto.response.OtelSaveResponseDto;
import com.burcu.exception.ErrorType;
import com.burcu.exception.OtelException;
import com.burcu.mapper.OtelMapper;
import com.burcu.repository.OtelRepository;
import com.burcu.utility.ServiceManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OtelService extends ServiceManager<Otel, String> {

    private final OtelRepository otelRepository;
    private final CategoryService categoryService;
    private final PropertiesService propertiesService;
    private final CategoryPropertiesService categoryPropertiesService;
    private final AddressService addressService;
    private final RoomService roomService;
    private final ImageService imageService;
    private final OtelTagsService otelTagsService;
    private final CommentService commentService;
    private final TagsService tagsService;
    private final CacheManager cacheManager;



    public OtelService(OtelRepository otelRepository, CategoryService categoryService, PropertiesService propertiesService, CategoryPropertiesService categoryPropertiesService, AddressService addressService, RoomService roomService, ImageService imageService, OtelTagsService otelTagsService, CommentService commentService, TagsService tagsService, CacheManager cacheManager) {
        super(otelRepository);
        this.otelRepository = otelRepository;
        this.categoryService = categoryService;
        this.propertiesService = propertiesService;
        this.categoryPropertiesService = categoryPropertiesService;
        this.addressService = addressService;
        this.roomService = roomService;
        this.imageService = imageService;
        this.otelTagsService = otelTagsService;
        this.commentService = commentService;
        this.tagsService = tagsService;
        this.cacheManager = cacheManager;
    }

    public OtelSaveResponseDto saveOtel(OtelSaveRequestDto dto) {
        Optional<Otel> otelOptional=otelRepository.findByNameAndAddressId(dto.getName(),dto.getAddressId());
        if (otelOptional.isPresent()){
            throw new OtelException(ErrorType.OTEL_ALREADY_EXISTS);
        }

        Otel otel= OtelMapper.INSTANCE.fromOtelSaveRequestDtoToOtel(dto);
        save(otel);
        Objects.requireNonNull(cacheManager.getCache("find-otel-by-name")).evict(otel);
        Objects.requireNonNull(cacheManager.getCache("find-all-by-point")).evict(otel);
        Objects.requireNonNull(cacheManager.getCache("find-otel-by-address")).evict(otel);
        Objects.requireNonNull(cacheManager.getCache("find-otel-by-id")).evict(otel);
        Objects.requireNonNull(cacheManager.getCache("find-otel-by-otelId")).evict(otel);

        return OtelMapper.INSTANCE.fromOtelToOtelSaveResponseDto(otel);
    }
    public Category saveCategory(CategorySaveRequestDto dto) {
        return categoryService.save(dto);
    }

    public Properties saveProperties(PropertiesSaveRequestDto dto) {
        Objects.requireNonNull(cacheManager.getCache("filter-list")).evict(dto);
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

    public Tags saveTags(TagsSaveRequestDto dto) {
        return tagsService.save(dto);
    }


    @Cacheable(value = "filter-list")
    public Map<Properties, List<Properties>> filterList() {
        return propertiesService.filterList();
    }



    @Cacheable(value = "find-all-by-point")
    public List<Otel> findAllByPoint() {
        try {
            Thread.sleep(3000);
            return otelRepository.findAllByOrderByPointDesc();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * otel/find-search (arama çubuğuna yazılan ifadeye göre filtreleme yaparak otel listesi dönecek)
     * @return
     */
    @Cacheable(value = "find-otel-by-name")
    public List<OtelHomePageResponseDto> findOtelByName(String name) {
        List<OtelHomePageResponseDto> otelDtoList=new ArrayList<>();
        List<Otel> otelList = otelRepository.findByNameContainingIgnoreCase(name);

        otelList.stream()
                .map(this::getHomePageOtelResponseDto)
                .sorted(Comparator.comparingDouble(OtelHomePageResponseDto::getPoint).reversed())
                .forEach(otelDtoList::add);

        return otelDtoList;
    }


    @Cacheable(value = "find-otel-by-address")
    public List<OtelHomePageResponseDto> findOtelByAddress(String search) {
        List<OtelHomePageResponseDto> otelDtoList = new ArrayList<>();
        List<Address> addressList = addressService.findByNameContainingIgnoreCaseOrStreetNumberContainingIgnoreCase(search);
        List<String> addressListId = addressList.stream().map(x -> x.getId()).collect(Collectors.toList());
        otelRepository.findAllByAddressIdIn(addressListId).stream()
                .map(this::getHomePageOtelResponseDto)
                .sorted(Comparator.comparingDouble(OtelHomePageResponseDto::getPoint).reversed())
                .forEach(otelDtoList::add);

        return otelDtoList;

    }

    private OtelHomePageResponseDto getHomePageOtelResponseDto(Otel otel) {
        Optional<Address> addressOptional = addressService.findById(otel.getAddressId());
        List<Comment> commentList = commentService.findByOtelId(otel.getId());
        List<Otel_Tags> otel_tagsList = otelTagsService.findByOtelId(otel.getId());


        List<String> tagsList=otel_tagsList.stream().map(x->x.getTagId()).toList();
        List<Tags> tags =tagsService.findByIdContaining(tagsList);
        return OtelHomePageResponseDto.builder()
                .name(otel.getName())
                .point(otel.getPoint())
                .addressName(addressOptional.get().getName())
                .tags(tags)
                .commentNumber(commentList.size())  //TODO: comment oluşturunca burayı kontrol et.
                .build();

    }

    @Cacheable(value = "find-otel-by-id")
    public Otel findOtelById(String id) {
        return otelRepository.findById(id).orElseThrow(() -> new OtelException(ErrorType.OTEL_NOT_FOUND));
    }

    @Cacheable(value = "find-otel-by-otelId")
    public List<Otel> findAllByOtelIdIn(List<String> favOtels) {
        return otelRepository.findAllByIdIn(favOtels);
    }
}
