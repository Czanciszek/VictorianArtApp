package com.VictorianApp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {

    public Integer id_zamowienia;
    public Integer id_produktu;
    public Integer ilosc;
    public String data_danych;
    public String data_projektu;
    public String data_zatwierdzenia;
    public String data_wydrukowania;
    public String data_wykonania;
    public Boolean gotowosc;

    //Szczegóły
    public Integer ilosc_produktow;
    public Float cena_zamowienia;
}
