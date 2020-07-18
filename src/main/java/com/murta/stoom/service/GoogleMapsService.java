package com.murta.stoom.service;

import com.murta.stoom.entity.Adress;
import model.GoogleMapsModel;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import util.CommonsUtil;
import util.GsonUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class GoogleMapsService {

    private final String chaveAcessso = "AIzaSyDTK0igIQTCi5EYKL9tzOIJ9N6FUASGZos";

    public void getPositon(Adress adress) throws InterruptedException, IOException, URISyntaxException {

        String parametros = adress.getStreetName() + " " + adress.getNumber() + " " + adress.getComplement() + " " +
                adress.getNeighbourhood() + " " + adress.getCity() + " " + adress.getState() + " " +
                adress.getCountry() + " " + adress.getZipcode();

        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response = null;


        response = getStringHttpResponse("https://maps.googleapis.com/maps/api/geocode/json", parametros, chaveAcessso, client);


        GoogleMapsModel googleMapsModel = GsonUtil.fromJson(response.body(), GoogleMapsModel.class);

        if (!CommonsUtil.semValor(googleMapsModel.getResults())) {
            adress.setLatitude(googleMapsModel.getResults().get(0).getGeometry().getLocation().getLat());
            adress.setLongitude(googleMapsModel.getResults().get(0).getGeometry().getLocation().getLng());
        }

    }

    private static HttpResponse<String> getStringHttpResponse(String urlServidor, String parametros,
                                                              String chaveAcesso, HttpClient client) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request;


        URIBuilder ub = new URIBuilder(urlServidor);
        ub.addParameter("address", parametros);
        ub.addParameter("key", chaveAcesso);

        request = HttpRequest.newBuilder()
                .setHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                .uri(ub.build())
                .build();

        return client.send(request,
                HttpResponse.BodyHandlers.ofString());
    }

}
