package com.magalu.cadastro.entrega;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.magalu.cadastro.entrega.mapper.DeliveryMapper;
import com.magalu.cadastro.entrega.mapper.dto.DeliveryDTO;
import com.magalu.cadastro.entrega.mapper.dto.DeliveryRequest;
import com.magalu.cadastro.entrega.repository.DeliveryRepository;
import com.magalu.cadastro.entrega.service.DeliveryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = DeliveryApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class DeliveryControllerTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer(DockerImageName.parse("postgres:14.3-alpine"));

    @Autowired
    private DeliveryService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private DeliveryMapper mapper;

    @Mock
    private DeliveryRepository repository;


    @Test
    public void shouldCreatedDelivery() throws Exception {
        Factory factory = new Factory();
        DeliveryRequest delivery = factory.createDelivery();

        mvc.perform(MockMvcRequestBuilders.post("/v1/delivery")
                        .content(objectMapper.writeValueAsString(delivery))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    DeliveryDTO deliveryDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                            DeliveryDTO.class);
                    assertThat(deliveryDTO).isNotNull();
                    assertThat(deliveryDTO.id()).isNotNull();
                    assertThat(deliveryDTO.deliveryDeadline()).isEqualTo(delivery.deliveryDeadline());
                    assertThat(deliveryDTO.cpfClient()).isEqualTo(delivery.cpfClient());
                });
    }

    @Test
    public void shouldFindDeliveryById() throws Exception {
        Factory factory = new Factory();
        DeliveryRequest delivery = factory.createDelivery();
        DeliveryDTO savedDelivery = service.save(delivery);

        mvc.perform(MockMvcRequestBuilders.get("/v1/delivery/" + savedDelivery.id())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    DeliveryDTO deliveryDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                            DeliveryDTO.class);
                    assertThat(deliveryDTO).isNotNull();
                    assertThat(deliveryDTO.id()).isEqualTo(savedDelivery.id());
                    assertThat(deliveryDTO.deliveryDeadline()).isEqualTo(savedDelivery.deliveryDeadline());
                    assertThat(deliveryDTO.cpfClient()).isEqualTo(savedDelivery.cpfClient());
                });
    }

    @Test
    public void shouldUpdateDelivery() throws Exception {
        Factory factory = new Factory();
        DeliveryRequest delivery = factory.createDelivery();
        DeliveryDTO savedDelivery = service.save(delivery);
        DeliveryRequest updateRequest = factory.updateDelivery();

        mvc.perform(MockMvcRequestBuilders.put("/v1/delivery/" + savedDelivery.id())
                        .content(objectMapper.writeValueAsString(updateRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    DeliveryDTO updatedDelivery = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                            DeliveryDTO.class);
                    assertThat(updatedDelivery).isNotNull();
                    assertThat(updatedDelivery.id()).isEqualTo(savedDelivery.id());
                    assertThat(updatedDelivery.quantityPackages()).isEqualTo(updateRequest.quantityPackages());
                    assertThat(updatedDelivery.address().number()).isEqualTo(updateRequest.address().number());
                });
    }

    @Test
    public void shouldDeleteDelivery() throws Exception {
        Factory factory = new Factory();
        DeliveryRequest delivery = factory.createDelivery();
        DeliveryDTO savedDelivery = service.save(delivery);

        mvc.perform(MockMvcRequestBuilders.delete("/v1/delivery/" + savedDelivery.id())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        mvc.perform(MockMvcRequestBuilders.get("/v1/delivery/" + savedDelivery.id())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnNotFoundForNonExistentDelivery() throws Exception {
        Long nonExistentId = 999L;

        mvc.perform(MockMvcRequestBuilders.get("/v1/delivery/" + nonExistentId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnNotFoundForUpdateNonExistentDelivery() throws Exception {
        Long nonExistentId = 999L;
        DeliveryRequest updateRequest = new Factory().updateDelivery();

        mvc.perform(MockMvcRequestBuilders.put("/v1/delivery/" + nonExistentId)
                        .content(objectMapper.writeValueAsString(updateRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}