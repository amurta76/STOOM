package com.murta.stoom.service;

import com.murta.stoom.entity.Adress;
import com.murta.stoom.model.GoogleMapsModel;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import com.murta.stoom.util.CommonsUtil;
import com.murta.stoom.util.GsonUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@Service
public class GoogleMapsService {
    HttpService httpService;

    public GoogleMapsService(HttpService httpService) {
        this.httpService = httpService;
    }

    private final String chaveAcessso = "AIzaSyDTK0igIQTCi5EYKL9tzOIJ9N6FUASGZos";

    public void getPositon(Adress adress) throws InterruptedException, IOException, URISyntaxException {

        String endereco = adress.getStreetName() + " " + adress.getNumber() + " " + adress.getComplement() + " " +
                adress.getNeighbourhood() + " " + adress.getCity() + " " + adress.getState() + " " +
                adress.getCountry() + " " + adress.getZipcode();

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response = null;

        Map<String, String> parametros = new HashMap<>(0);
        parametros.put("key", chaveAcessso);
        parametros.put("address", endereco );

        response = httpService.getStringHttpResponse("https://maps.googleapis.com/maps/api/geocode/json", parametros, client);

        GoogleMapsModel googleMapsModel = GsonUtil.fromJson(response.body(), GoogleMapsModel.class);

        if (!CommonsUtil.semValor(googleMapsModel.getResults())) {
            adress.setLatitude(googleMapsModel.getResults().get(0).getGeometry().getLocation().getLat());
            adress.setLongitude(googleMapsModel.getResults().get(0).getGeometry().getLocation().getLng());
        }

    }



}
