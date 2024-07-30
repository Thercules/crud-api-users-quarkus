package org.acme.hercules.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

@Entity
@Table(name = "Users")
public class User extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank(message = "Name cannot be blank")
    public String name;

    // @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    public String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    public String password;

    @NotBlank(message = "CPF cannot be blank")
    //  @Pattern(regexp = "\\d{11}", message = "CPF should be exactly 11 digits")
    public String cpf;

    @NotBlank(message = "RG cannot be blank")
    public String rg;

    @NotBlank(message = "Address cannot be blank")
    public String address;

    @Column(length = 20) // Adjust the length as needed
    @NotBlank(message = "Phone number cannot be blank")
    public String phoneNumber;

    @Past(message = "Date of birth must be in the past")
    public LocalDate dateOfBirth;

    public String gender;
    public String role;
    public boolean isActive;

    public void encryptSensitiveData() {
        this.name = encrypt(name);
        this.email = encrypt(email);
        this.password = encrypt(password);
        this.cpf = encrypt(cpf);
        this.rg = encrypt(rg);
        this.address = encrypt(address);
        this.phoneNumber = encrypt(phoneNumber);
    }

    private String encrypt(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashInBytes = md.digest(data.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashInBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
