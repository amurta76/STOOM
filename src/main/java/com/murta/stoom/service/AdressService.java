package com.murta.stoom.service;

import com.murta.stoom.entity.Adress;
import com.murta.stoom.repository.AdressRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import util.CommonsUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.UUID;

/**
 * @author Alexandre Murta - amurta@gmail.com
 */
@Service
public class AdressService {

    AdressRepository adressRepository;

    GoogleMapsService googleMapsService;

    public AdressService(AdressRepository adressRepository,
                         GoogleMapsService googleMapsService) {
        this.adressRepository = adressRepository;
        this.googleMapsService = googleMapsService;
    }

    public Adress findById(UUID id) {
        return adressRepository.getOne(id);
    }

    public Page<Adress> findAll(Adress filtro, Pageable pageable) {

        ExampleMatcher matcher = ExampleMatcher.matching().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase();
        Example<Adress> example = Example.of(filtro, matcher);
        Page<Adress> adresses = adressRepository.findAll(example, pageable);

        return adresses;
    }

    public Adress save(Adress adress) throws InterruptedException, IOException, URISyntaxException {

        if (CommonsUtil.semValor(adress.getLongitude()) || CommonsUtil.semValor(adress.getLatitude())) {
            googleMapsService.getPositon(adress);
        }

        return adressRepository.save(adress);
    }

    public void delete(Adress adress) {
        adressRepository.delete(adress);
    }
}
