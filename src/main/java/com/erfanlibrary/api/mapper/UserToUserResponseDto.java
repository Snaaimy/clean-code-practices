package com.erfanlibrary.api.mapper;

import com.erfanlibrary.api.dto.user.UserResponseDto;
import com.erfanlibrary.api.model.User;

public class UserToUserResponseDto {
    public static UserResponseDto toCustomerDto(User user) {
        if (user == null) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setFullName(user.getFullName());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPhoneNumber(user.getPhoneNumber());
        userResponseDto.setAddress(AddressToAddressDto.toAddressDto(user.getAddress()));

        return userResponseDto;
    }
}
