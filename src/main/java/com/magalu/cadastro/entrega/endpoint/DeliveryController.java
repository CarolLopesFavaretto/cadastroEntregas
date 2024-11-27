package com.magalu.cadastro.entrega.endpoint;

import com.magalu.cadastro.entrega.api.DeliveryApi;
import com.magalu.cadastro.entrega.mapper.dto.DeliveryDTO;
import com.magalu.cadastro.entrega.mapper.dto.DeliveryRequest;
import com.magalu.cadastro.entrega.service.DeliveryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DeliveryController implements DeliveryApi {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    public ResponseEntity<DeliveryDTO> save(@RequestBody @Valid DeliveryRequest request) {
        return ResponseEntity.ok(deliveryService.save(request));
    }

    public ResponseEntity<DeliveryDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(deliveryService.findById(id));
    }

    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deliveryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<DeliveryDTO> update(@PathVariable Long id, @Valid @RequestBody DeliveryRequest request) {
        return ResponseEntity.ok(deliveryService.update(id, request));
    }
}
