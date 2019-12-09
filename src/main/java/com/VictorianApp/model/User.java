package com.VictorianApp.model;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    public Integer id;
    public String login;
    public String haslo;
    public String imie;
    public String nazwisko;
    public String rola;

}
