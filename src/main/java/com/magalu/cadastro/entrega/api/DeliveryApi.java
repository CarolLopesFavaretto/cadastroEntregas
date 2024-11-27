package com.magalu.cadastro.entrega.api;

import com.magalu.cadastro.entrega.endpoint.DeliveryController;
import com.magalu.cadastro.entrega.exception.handler.ErrorMessage;
import com.magalu.cadastro.entrega.mapper.dto.DeliveryDTO;
import com.magalu.cadastro.entrega.mapper.dto.DeliveryRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("v1/delivery")
@Tag(name = "delivery", description = "API de cadastro de entrega")
public interface DeliveryApi {

    @Operation(summary = "Cadastra uma entrega", description = "Cadastra uma entrega", tags = {"delivery"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cadastro de entrega criada com sucesso",
                    content = @Content(schema = @Schema(implementation = DeliveryController.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content =
            @Content(schema = @Schema(implementation = ErrorMessage.class)))})
    @PostMapping
    ResponseEntity<DeliveryDTO> save(@RequestBody @Valid DeliveryRequest request);


    @Operation(summary = "Buscar entrega", description = "Busca de entrega por id", tags = {"delivery"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = DeliveryController.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content =
            @Content(schema = @Schema(implementation = ErrorMessage.class)))})
    @GetMapping("/{id}")
    ResponseEntity<DeliveryDTO> findById(@PathVariable Long id);


    @Operation(summary = "Excluir entrega", description = "Excluir entrega por id", tags = {"delivery"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = DeliveryController.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content =
            @Content(schema = @Schema(implementation = ErrorMessage.class)))})
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);


    @Operation(summary = "Atualizar cadastro de entrega", description = "Atualizar o cadastro de uma  entrega por id",
            tags = {"delivery"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = DeliveryController.class))),
            @ApiResponse(responseCode = "404", description = "Not Found", content =
            @Content(schema = @Schema(implementation = ErrorMessage.class)))})
    @PutMapping("/{id}")
    ResponseEntity<DeliveryDTO> update(@PathVariable Long id, @Valid @RequestBody DeliveryRequest request);
}
