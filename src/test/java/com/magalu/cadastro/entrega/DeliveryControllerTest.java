package com.magalu.cadastro.entrega;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.magalu.cadastro.entrega.entity.Delivery;
import com.magalu.cadastro.entrega.mapper.DeliveryMapper;
import com.magalu.cadastro.entrega.mapper.dto.DeliveryDTO;
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
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = CadastroEntregaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
        Delivery delivery = factory.createDelivery();
//        DeliveryDTO save = service.save(delivery);

        mvc.perform(MockMvcRequestBuilders.post("/delivery/save")
                        .content(objectMapper.writeValueAsString(delivery))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(mvcResult -> {
                    DeliveryDTO deliveryDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),
                            DeliveryDTO.class);
                    assertThat(deliveryDTO).isNotNull();

                });

    }

}