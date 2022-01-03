package com.boulow.mono.entity;

import com.boulow.mono.config.Constants;
import com.boulow.mono.enumeration.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * A user.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String uid;

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String username;

    @Size(max = 50)
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 50)
    @Column(name = "last_name")
    private String lastName;
    
    private boolean isEmailVerified = false;
	
    @Size(max = 50)
    private String issuer;

    @Email
    @Size(min = 5, max = 254)
    private String email;

    @NotNull
    private boolean isActive = false;

    @Size(min = 2, max = 10)
    @Column(name = "lang_key")
    private String langKey;

    @Size(max = 256)
    @Column(name = "image_url")
    private String imageUrl;
    
    @NotNull(message = "must not be null")
    @Column(name = "is_tech")
    private Boolean isTech;

    @Size(max = 300)
    @Column(name = "bio")
    private String bio;

    @NotNull(message = "must not be null")
    @Column(name = "gender")
    private Gender gender;

    @Size(max = 20)
    @Column(name = "phone")
    private String phone;

    @Column(name = "dob")
    private LocalDate dob;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

}
