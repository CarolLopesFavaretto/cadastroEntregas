package com.magalu.cadastro.entrega.service;

import com.magalu.cadastro.entrega.entity.Delivery;
import com.magalu.cadastro.entrega.exception.handler.DeliveryNotFoundException;
import com.magalu.cadastro.entrega.mapper.DeliveryMapper;
import com.magalu.cadastro.entrega.mapper.dto.DeliveryDTO;
import com.magalu.cadastro.entrega.mapper.dto.DeliveryRequest;
import com.magalu.cadastro.entrega.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private DeliveryMapper mapper;


    public DeliveryDTO save(DeliveryRequest request) {
        Delivery delivery = mapper.toEntity(request);
        return mapper.toDTO(deliveryRepository.save(delivery));
    }

    public DeliveryDTO findById(Long id) {
        return deliveryRepository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new DeliveryNotFoundException("Delivery not found with id " + id));
    }

    public void delete(Long id) {
        deliveryRepository.deleteById(id);
    }

    public DeliveryDTO update(Long id, DeliveryRequest request) {
        Optional<Delivery> existingDelivery = deliveryRepository.findById(id);
        if (existingDelivery.isPresent()) {
            Delivery newDelivery = mapper.toEntity(request);
            newDelivery.setId(id);
            newDelivery = deliveryRepository.save(newDelivery);
            return DeliveryMapper.INSTANCE.toDTO(newDelivery);
        } else {
            throw new DeliveryNotFoundException("Delivery not found with id " + id);
        }
    }

}
