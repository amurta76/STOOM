package com.murta.stoom.controller;


import com.murta.stoom.entity.Adress;
import com.murta.stoom.response.Response;
import com.murta.stoom.service.AdressService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

/**
 * @author Alexandre Murta - amurta@gmail.com
 */
@RestController
@RequestMapping("/api/v1/endereco")
@CrossOrigin(origins = "*")
public class EnderecoController {

    AdressService adressService;

    public EnderecoController(AdressService adressService) {
        this.adressService = adressService;
    }

    @PostMapping(value = "listar")
    public ResponseEntity<Response<Page<Adress>>> list(@RequestBody Adress filtro, Pageable pageable) {
        Response<Page<Adress>> response = new Response<>();

        try {
            Page<Adress> adresses = adressService.findAll(filtro, pageable);
            response.setData(adresses);
        } catch (Exception e) {
            response.getErrors().add(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Response<Adress>> findById(@PathVariable("id") UUID id) {
        Response<Adress> response = new Response<>();

        try {
            Adress adress = adressService.findById(id);
            response.setData(adress);
        } catch (Exception e) {
            response.getErrors().add(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response<Adress>> create(@RequestBody @Valid Adress adress) {
        return save(adress);
    }

    @PutMapping
    public ResponseEntity<Response<Adress>> update(@RequestBody @Valid Adress adress) {
        return save(adress);
    }

    @DeleteMapping()
    public ResponseEntity<Response<Boolean>> delete(@RequestBody Adress adress) {
        Response<Boolean> response = new Response<>();

        try {
            adressService.delete(adress);
            response.setData(true);
        } catch (Exception e) {
            response.getErrors().add(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }


    private ResponseEntity<Response<Adress>> save(@RequestBody @Valid Adress adress) {
        Response<Adress> response = new Response<>();
        try {
            Adress adressSave = adressService.save(adress);
            response.setData(adressSave);
        } catch (Exception e) {
            response.getErrors().add(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }


}
