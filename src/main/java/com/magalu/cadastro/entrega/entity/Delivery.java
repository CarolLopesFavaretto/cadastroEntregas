package com.magalu.cadastro.entrega.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public record Delivery (@Id
                        @GeneratedValue(strategy = GenerationType.IDENTITY)
                        Long id,
                        int quantityPackages,
                        LocalDate deliveryDeadline,
                        String nameClient,
                        String cpfClient,
                        @Embedded
                        Address address){

}
