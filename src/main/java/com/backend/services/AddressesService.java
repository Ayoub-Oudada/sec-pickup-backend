package com.backend.services;

import com.backend.dtos.AddressDto;
import com.backend.entities.Address;
import com.backend.exceptions.ResourceNotFoundException;
import com.backend.mappers.AddressMapper;
import com.backend.repositories.AddressRepository;
import com.backend.repositories.TrajetsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressesService {
    private final AddressRepository addressRepository;
    private final TrajetsRepository trajetsRepository;


    public List<AddressDto> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll(Sort.by(
                Sort.Direction.DESC,
                "createdAt"
        ));
        return AddressMapper.INSTANCE.addressesToAddressDtos(addresses);
    }

    public Long storeAddress(AddressDto addressDto) {
        var trajet = trajetsRepository.findById(addressDto.getTrajetId())
                .orElseThrow(() -> new ResourceNotFoundException("trajet not found"));


        var add = AddressMapper.INSTANCE.addressDtoToAddress(addressDto);
        add.setTrajet(trajet);

        var address = addressRepository.save(add);
        return address.getId();
    }

    public AddressDto getAddress(Long id) {
        var address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found!"));

        return AddressMapper.INSTANCE.addressToAddressDto(address);
    }

    public void updateAddress(Long id, AddressDto addressDto) {
        var address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("address not found"));

        var trajet = trajetsRepository.findById(addressDto.getTrajetId())
                .orElseThrow(() -> new ResourceNotFoundException("trajet not found"));


        AddressMapper.INSTANCE.updateAddressFromAddressDto(addressDto, address);
        address.setTrajet(trajet);
    }

    public void deleteAddress(Long id) {
        var address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("address not found"));

        addressRepository.delete(address);
    }
}
