package com.murta.stoom.service;

import com.murta.stoom.util.CommonsUtil;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@Service
public class HttpService {

    public static HttpResponse<String> getStringHttpResponse(String urlServidor,  Map<String, String> parametros, HttpClient client) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request;

        URIBuilder ub = new URIBuilder(urlServidor);
        if (!CommonsUtil.semValor(parametros)) {
            for ( Map.Entry<String, String> valor : parametros.entrySet()) {
                ub.addParameter( valor.getKey(), valor.getValue());
            }
        }

        request = HttpRequest.newBuilder()
                .setHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded")
                .uri(ub.build())
                .build();

        return client.send(request,
                HttpResponse.BodyHandlers.ofString());

    }

    public static HttpResponse<String> putHttpResponse(String urlServidor,  String body
                                                            , HttpClient client) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request;

        URIBuilder ub = new URIBuilder(urlServidor);

        request = HttpRequest.newBuilder()
                .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(body))
                .uri(ub.build())
                .build();

        return client.send(request,
                HttpResponse.BodyHandlers.ofString());

    }


}


