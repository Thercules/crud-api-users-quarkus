package org.acme.hercules.entity;

import jakarta.persistence.*;
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

    public String name;
    public String email;
    public String password;
    public String cpf;
    public String rg;
    public String address;

    @Column(length = 20) // Adjust the length as needed
    public String phoneNumber;

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
