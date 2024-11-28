package com.magalu.cadastro.entrega;

import com.magalu.cadastro.entrega.mapper.dto.AddressDTO;
import com.magalu.cadastro.entrega.mapper.dto.DeliveryRequest;

import java.sql.Date;

public class Factory {

    public DeliveryRequest createDelivery() {
        AddressDTO address = new AddressDTO(
                "0615010",
                "SP",
                "São Paulo",
                "Centro",
                "Rua A",
                "123",
                "Apto 1"
        );

        DeliveryRequest delivery = new DeliveryRequest(
                1,
                Date.valueOf("2023-12-31").toLocalDate(),
                "John Doe",
                "41246532808",
                address
        );

        return delivery;
    }

    public DeliveryRequest updateDelivery() {
        DeliveryRequest updateRequest = new DeliveryRequest(
                1,
                Date.valueOf("2024-11-25").toLocalDate(),
                "John Doe",
                "41246532808",
                new AddressDTO(
                        "0615010",
                        "SP",
                        "São Paulo",
                        "Centro",
                        "Rua B",
                        "1234",
                        "Apto 3"
                )
        );
        return updateRequest;
    }

    public DeliveryRequest invalidRequest() {
        DeliveryRequest invalidRequest = new DeliveryRequest(
                1,
                Date.valueOf("2024-11-25").toLocalDate(),
                "John Doe",
                "",
                new AddressDTO(
                        "0615010",
                        "SP",
                        "São Paulo",
                        "Centro",
                        "Rua B",
                        "1234",
                        "Apto 3"
                )
        );
        return invalidRequest;
    }
}
