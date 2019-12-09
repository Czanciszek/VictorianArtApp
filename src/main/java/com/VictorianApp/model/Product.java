package com.VictorianApp.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue
    public Integer id_produktu;
    public String nazwa;
    public String kategoria;
    public Integer typ;
    public Integer podatek_vat;
    public Float ryczalt;
    public Integer stan_magazynu;
    public Float cena;

}
