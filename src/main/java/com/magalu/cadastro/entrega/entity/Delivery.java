package com.magalu.cadastro.entrega.entity;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantityPackages;
    private LocalDate deliveryDeadline;
    private String nameClient;
    @CPF
    private String cpfClient;
    @Embedded
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantityPackages() {
        return quantityPackages;
    }

    public void setQuantityPackages(int quantityPackages) {
        this.quantityPackages = quantityPackages;
    }

    public LocalDate getDeliveryDeadline() {
        return deliveryDeadline;
    }

    public void setDeliveryDeadline(LocalDate deliveryDeadline) {
        this.deliveryDeadline = deliveryDeadline;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getCpfClient() {
        return cpfClient;
    }

    public void setCpfClient(String cpfClient) {
        this.cpfClient = cpfClient;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Delivery(Long id, int quantityPackages, LocalDate deliveryDeadline, String nameClient, String cpfClient, Address address) {
        this.id = id;
        this.quantityPackages = quantityPackages;
        this.deliveryDeadline = deliveryDeadline;
        this.nameClient = nameClient;
        this.cpfClient = cpfClient;
        this.address = address;
    }

    public Delivery() {
    }
}
