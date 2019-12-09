package com.VictorianApp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetails {

    //Zamowienie
    public Integer id_zamowienia;
    public Integer id_produktu;
    public Integer ilosc;
    public String data_danych;
    public String data_projektu;
    public String data_zatwierdzenia;
    public String data_wydrukowania;
    public String data_wykonania;
    public Boolean gotowosc;

    //Produkt
    public String nazwa;
    public String kategoria;
    public Float cena;
    public Integer typ;

    //Procedura
    public String nick;
    public String adres;
    public String email;
    public String telefon;
    public String data_zamowienia;
    public String data_wysylki;
    public Boolean gotowosc_zamowienia;
    public  Boolean oplata;

    //Wysyłka
    public Integer id_wysylki;
    public String wysylka;


}
