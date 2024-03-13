package com.burcu.service;

import com.burcu.domain.Address;
import com.burcu.dto.request.AddressSaveRequestDto;
import com.burcu.exception.ErrorType;
import com.burcu.exception.OtelException;
import com.burcu.repository.AddressRepository;
import com.burcu.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService extends ServiceManager<Address, String> {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        super(addressRepository);
        this.addressRepository = addressRepository;
    }

    public Address save(AddressSaveRequestDto dto) {
        Optional<Address> addressOptional = addressRepository.findByDistirctIdAndStreetNumber(dto.getDistirctId(), dto.getStreetNumber());
        if (addressOptional.isPresent()){
            throw new OtelException(ErrorType.ADDRESS_ALREADY_EXISTS);
        }
        Address address = Address.builder()
                .name(dto.getName())
                .distirctId(dto.getDistirctId())
                .streetNumber(dto.getStreetNumber())
                .zipCode(dto.getZipCode())
                .description(dto.getDescription())
                .build();
        return save(address);

    }

    public List<Address> findByNameContainingIgnoreCase(String name) {
        return addressRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Address> findByNameContainingIgnoreCaseOrStreetNumberContainingIgnoreCase(String search) {
        return addressRepository.findByNameContainingIgnoreCaseOrStreetNumberContainingIgnoreCase(search,search);
    }
}