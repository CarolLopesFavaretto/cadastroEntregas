package com.magalu.cadastro.entrega.mapper;

import com.magalu.cadastro.entrega.entity.Delivery;
import com.magalu.cadastro.entrega.mapper.dto.DeliveryDTO;
import com.magalu.cadastro.entrega.mapper.dto.DeliveryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeliveryMapper {

    DeliveryMapper INSTANCE = Mappers.getMapper(DeliveryMapper.class);

    DeliveryDTO toDTO(Delivery delivery);

    Delivery toEntity(DeliveryRequest request);

}
