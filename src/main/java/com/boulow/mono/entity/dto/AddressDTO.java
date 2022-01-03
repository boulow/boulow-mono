package com.boulow.mono.entity.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

import com.boulow.mono.enumeration.AddressType;

import lombok.Data;

/**
 * A DTO for the {@link com.boulow.user.domain.Address} entity.
 */
@Data
public class AddressDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private Long id;

    private String street;

    private String suite;

    private String city;

    private String province;

    private String zip;

    private Double lat;

    private Double lon;

    private AddressType type;

}
