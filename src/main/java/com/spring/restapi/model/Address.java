package com.spring.restapi.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer addressId;

    @Column(name = "address_line")
    @NotNull
    @Size(min = 2, max = 50)
    private String addressLine;

    @NotNull
    @Size(min = 2, max = 20)
    private String city;

    @Size(max = 45)
    private String state;

    @Column(name = "zip_code")
    @NotNull
    @Size(min = 5, max = 5)
    private String zipCode;

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public @NotNull @Size(min = 2, max = 50) String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(@NotNull @Size(min = 2, max = 50) String addressLine) {
        this.addressLine = addressLine;
    }

    public @NotNull @Size(min = 2, max = 20) String getCity() {
        return city;
    }

    public void setCity(@NotNull @Size(min = 2, max = 20) String city) {
        this.city = city;
    }

    public @Size(max = 45) String getState() {
        return state;
    }

    public void setState(@Size(max = 45) String state) {
        this.state = state;
    }

    public @NotNull @Size(min = 5, max = 5) String getZipCode() {
        return zipCode;
    }

    public void setZipCode(@NotNull @Size(min = 5, max = 5) String zipCode) {
        this.zipCode = zipCode;
    }
}
