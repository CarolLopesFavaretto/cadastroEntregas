package com.magalu.cadastro.entrega.service;

import com.magalu.cadastro.entrega.entity.Delivery;
import com.magalu.cadastro.entrega.exception.handler.DeliveryNotFoundException;
import com.magalu.cadastro.entrega.mapper.DeliveryMapper;
import com.magalu.cadastro.entrega.mapper.dto.DeliveryDTO;
import com.magalu.cadastro.entrega.mapper.dto.DeliveryRequest;
import com.magalu.cadastro.entrega.repository.DeliveryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliveryService {

    private static final Logger logger = LoggerFactory.getLogger(DeliveryService.class);

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private DeliveryMapper mapper;


    public DeliveryDTO save(DeliveryRequest request) {
        logger.info("Saving delivery: {}", request);
        Delivery delivery = mapper.toEntity(request);
        return mapper.toDTO(deliveryRepository.save(delivery));
    }

    public DeliveryDTO findById(Long id) {
        logger.info("Finding delivery by id: {}", id);
        return deliveryRepository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> {
                    logger.error("Delivery not found with id: {}", id);
                    return new DeliveryNotFoundException("Delivery not found with id " + id);
                });
    }

    public void delete(Long id) {
        logger.info("Deleting delivery by id: {}", id);
        deliveryRepository.deleteById(id);
    }

    public DeliveryDTO update(Long id, DeliveryRequest request) {
        logger.info("Updating delivery with id: {} with data: {}", id, request);
        Optional<Delivery> existingDelivery = deliveryRepository.findById(id);
        if (existingDelivery.isPresent()) {
            Delivery newDelivery = mapper.toEntity(request);
            newDelivery.setId(id);
            newDelivery = deliveryRepository.save(newDelivery);
            return DeliveryMapper.INSTANCE.toDTO(newDelivery);
        } else {
            logger.error("Delivery not found with id: {}", id);
            throw new DeliveryNotFoundException("Delivery not found with id " + id);
        }
    }

}
