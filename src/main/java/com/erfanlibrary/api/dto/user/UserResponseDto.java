package com.erfanlibrary.api.dto.user;

import com.erfanlibrary.api.dto.AddressDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
public class UserResponseDto {
    private String fullName;
    private String email;
    private String phoneNumber;
    private AddressDto address;


}
