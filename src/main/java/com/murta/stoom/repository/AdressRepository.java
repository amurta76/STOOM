package com.murta.stoom.repository;

import com.murta.stoom.entity.Adress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Alexandre Murta - amurta@gmail.com
 */
@Repository
public interface AdressRepository extends JpaRepository<Adress, UUID> {


}