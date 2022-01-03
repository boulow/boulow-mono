package com.boulow.mono.entity.dto;

import java.time.LocalDate;

import com.boulow.mono.entity.Address;
import com.boulow.mono.enumeration.Gender;

import lombok.Data;

/**
 * A DTO representing a user, with only the public attributes.
 */
@Data
public class UserDTO {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String imageUrl;
    private String bio;
    private Gender gender;
    private String phone;
    private LocalDate dob;
    private Address address;
    private String uid;
    private String issuer;

}
