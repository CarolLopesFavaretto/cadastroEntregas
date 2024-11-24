package com.magalu.cadastro.entrega.mapper.dto;

import java.time.LocalDate;

public record DeliveryDTO(Long id,
                          int quantityPackages,
                          LocalDate deliveryDeadline,
                          String nameClient,
                          String cpfClient,
                          AddressDTO address) {
}
