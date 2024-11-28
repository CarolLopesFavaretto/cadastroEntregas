package com.magalu.cadastro.entrega.mapper.dto;

public record AddressDTO(String zipCode,
                         String uf,
                         String city,
                         String district,
                         String street,
                         String number,
                         String complement) {
}
