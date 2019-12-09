package com.VictorianApp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
public class SendMethod {

    @Id
    public Integer id_wysylki;
    public String wysylka;
    public Float cena;

}
