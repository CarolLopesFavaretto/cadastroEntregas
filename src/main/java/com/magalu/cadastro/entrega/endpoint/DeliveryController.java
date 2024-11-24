package com.magalu.cadastro.entrega.endpoint;

import com.magalu.cadastro.entrega.entity.Delivery;
import com.magalu.cadastro.entrega.mapper.dto.DeliveryDTO;
import com.magalu.cadastro.entrega.service.DeliveryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping("/save")
    public ResponseEntity<DeliveryDTO> save(@RequestBody @Valid Delivery delivery) {
        return ResponseEntity.ok(deliveryService.save(delivery));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(deliveryService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deliveryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DeliveryDTO> update(@PathVariable Long id, @Valid @RequestBody Delivery delivery) {
        return ResponseEntity.ok(deliveryService.update(id, delivery));
    }
}
