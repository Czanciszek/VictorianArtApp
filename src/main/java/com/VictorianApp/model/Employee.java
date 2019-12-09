package com.VictorianApp.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee extends User {

    @Builder
    public Employee(Integer id, String login, String haslo, String imie, String nazwisko, String rola ) {

        super(id, login, haslo, imie, nazwisko, rola);
    }

}
