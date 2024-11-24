package com.magalu.cadastro.entrega.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public record Address(String cep,
                      String uf,
                      String city,
                      String district,
                      String street,
                      String number,
                      String complement) {
}
