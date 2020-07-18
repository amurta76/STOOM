package com.murta.stoom;

import com.murta.stoom.controller.EnderecoController;
import com.murta.stoom.entity.Adress;
import com.murta.stoom.service.AdressService;
import com.murta.stoom.util.GsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EnderecoController.class)
@WithMockUser
public class StoomRestTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdressService adressService;


    @Test
    public void contextLoads() throws Exception {
        Adress adress = getAdressComplete();
        put(adress);
        post(adress);
        get(UUID.fromString("70B2525E-298D-4B08-9901-5997AC14104F"));
        delete(adress);
    }

    private void put(Adress adress) throws Exception {

        String content = GsonUtil.toJson(adress);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/v1/endereco")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("UTF-8")
                .content(content);


        this.mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andDo(
                        MockMvcResultHandlers.print()
                );
    }

    private void post(Adress adress) throws Exception {
        String content = GsonUtil.toJson(adress);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/v1/endereco")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("UTF-8")
                .content(content);


        this.mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andDo(
                        MockMvcResultHandlers.print()
                );

    }

    private void get(UUID id) throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/endereco/" + id.toString())
                .accept(MediaType.APPLICATION_JSON);

        this.mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andDo(
                        MockMvcResultHandlers.print()
                );


    }

    private void delete(Adress adress) throws Exception {
        String content = GsonUtil.toJson(adress);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/v1/endereco")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("UTF-8")
                .content(content);


        this.mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andDo(
                        MockMvcResultHandlers.print()
                );
    }

    private Adress getAdressComplete() {

        StringBuilder sAdress = new StringBuilder();
        sAdress.append("{");
        sAdress.append("\"streetName\":\"Zerilo Pereira Lopes\",");
        sAdress.append("\"number\":\"477\",");
        sAdress.append("\"complement\":\"casa 55\",");
        sAdress.append("\"neighbourhood\": \"Pq Alto Taquaral\",");
        sAdress.append("\"city\":\"Campinas\",");
        sAdress.append("\"state\":\"SP\",");
        sAdress.append("\"country\":\"Brasil\" ,");
        sAdress.append("\"zipcode\":\"13087757\"");
        sAdress.append("}");

        return GsonUtil.fromJson(sAdress.toString(), Adress.class);
    }


}
