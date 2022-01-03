package com.boulow.mono.entity.dto;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import com.boulow.mono.entity.Address;
import com.boulow.mono.enumeration.Gender;

import lombok.Data;

/**
 * DTO used to update a user information, with only the public attributes.
 */
@Data
public class UserUpdateDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String bio;
    private Gender gender;
    private String phone;
    private LocalDate dob;
    private String langKey;
    private MultipartFile avatar;
    private String uid;
    private Address address;

}