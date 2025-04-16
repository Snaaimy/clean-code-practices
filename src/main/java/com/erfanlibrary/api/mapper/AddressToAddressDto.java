package com.erfanlibrary.api.mapper;

import com.erfanlibrary.api.dto.AddressDto;
import com.erfanlibrary.api.model.Address;

public class AddressToAddressDto {

    public static AddressDto toAddressDto(Address address) {
        if (address == null) {
            return null;
        }

        AddressDto addressDto = new AddressDto();
        addressDto.setAddressLine1(address.getAddressLine1());
        addressDto.setAddressLine2(address.getAddressLine2());
        addressDto.setCity(address.getCity());
        addressDto.setPostalCode(address.getPostalCode());
        addressDto.setState(address.getState());
        addressDto.setCountry(address.getCountry());

        return addressDto;
    }
}
