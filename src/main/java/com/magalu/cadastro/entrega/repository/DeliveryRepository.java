package com.magalu.cadastro.entrega.repository;

import com.magalu.cadastro.entrega.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
