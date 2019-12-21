package com.VictorianApp.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Procedure {

    @Id
    @GeneratedValue
    public Integer id_zamowienia;
    public String adres;
    public String nick;
    public String email;
    public String telefon;
    public String data_zamowienia;
    public Boolean gotowosc_zamowienia;
    public Integer id_wysylki;
    public String wysylka;
    public Boolean oplata;
    public String data_wysylki;
    public Float cena_zamowienia;

}
