package com.murta.stoom;

//import org.junit.jupiter.api.Test;

import com.murta.stoom.controller.EnderecoController;
import com.murta.stoom.entity.Adress;
import com.murta.stoom.response.Response;
import com.murta.stoom.util.GsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@DataJpaTest
@ComponentScan("com.murta.stoom.*")
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class StoomJpaTests {


    @Autowired
    EnderecoController enderecoController;


    @Test
    public void contextLoads() {
        Adress adress = getAdressComplete();


        adress = salva(adress);

        adress.setCity("CAMPINAS");

        atualiza(adress);

        Adress adress1 = findById(adress.getId());
        if (!"CAMPINAS".equals(adress1.getCity())) {
            new Exception("Erro atualizar");
        }

        exclui(adress);

        adress1 = findById(adress.getId());
        if (adress1 != null) {
            new Exception("Erro excluir");
        }


    }

    private Adress salva(Adress adress) {
        ResponseEntity<Response<Adress>> responseEntity = enderecoController.create(adress);

        if (responseEntity.getStatusCodeValue() == 200) {
            Adress clientResponse = responseEntity.getBody().getData();
            if (clientResponse.getId() == null
                    || clientResponse.getLatitude() == null
                    || clientResponse.getLongitude() == null) {
                new Exception("Erro put");
            }
            return clientResponse;
        }
        return null;

    }

    private void atualiza(Adress adress) {
        ResponseEntity<Response<Adress>> responseEntity = enderecoController.update(adress);

        if (responseEntity.getStatusCodeValue() == 200) {
            Adress clientResponse = responseEntity.getBody().getData();
            if (!"CAMPINAS".equals(clientResponse.getCity())) {
                new Exception("Erro atualizar");
            }
        }
    }


    private Adress findById(UUID id) {
        ResponseEntity<Response<Adress>> responseEntity = enderecoController.findById(id);

        if (responseEntity.getStatusCodeValue() == 200) {
            Adress clientResponse = responseEntity.getBody().getData();
            return clientResponse;
        }
        return null;

    }

    private void exclui(Adress adress) {
        ResponseEntity<Response<Boolean>> responseEntity = enderecoController.delete(adress);

        if (responseEntity.getStatusCodeValue() != 200) {
            new Exception("Erro excluir");
        }

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
