package com.magalu.cadastro.entrega;

import com.magalu.cadastro.entrega.entity.Address;
import com.magalu.cadastro.entrega.entity.Delivery;

import java.sql.Date;

public class Factory {

    public Delivery createDelivery() {
        Delivery delivery = new Delivery();
        delivery.setDeliveryDeadline(Date.valueOf("2023-12-31").toLocalDate());
        delivery.setNameClient("John Doe");
        delivery.setCpfClient("41246532808");

        Address address = new Address();
        address.setZipCode("0615010");
        address.setUf("SP");
        address.setCity("São Paulo");
        address.setDistrict("Centro");
        address.setStreet("Rua A");
        address.setNumber("123");
        address.setComplement("Apto 1");

        delivery.setAddress(address);
        return delivery;
    }
}
