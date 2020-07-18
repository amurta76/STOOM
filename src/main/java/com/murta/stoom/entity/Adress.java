package com.murta.stoom.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * @author Alexandre Murta - amurta@gmail.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Adress {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uniqueidentifier")
    @Type(type = "uuid-char")
    private UUID id;

    @NotNull
    private String streetName;

    @NotNull
    @Size(max=20)
    private String number;

    @Size(max=100)
    private String complement;

    @NotNull
    @Size(max=150)
    private String neighbourhood;

    @NotNull
    @Size(max=150)
    private String city;

    @NotNull
    @Size(max=150)
    private String state;

    @NotNull
    @Size(max=150)
    private String country;

    @NotNull
    @Size(max=8, min = 8)
    private String zipcode;

    private Double latitude;

    private Double longitude;

}