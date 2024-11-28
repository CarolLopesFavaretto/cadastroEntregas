package com.magalu.cadastro.entrega.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    private String zipCode;
    private String uf;
    private String city;
    private String district;
    private String street;
    private String number;
    private String complement;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public Address(String zipCode, String uf, String city, String district, String street, String number, String complement) {
        this.zipCode = zipCode;
        this.uf = uf;
        this.city = city;
        this.district = district;
        this.street = street;
        this.number = number;
        this.complement = complement;
    }

    public Address() {
    }
}
