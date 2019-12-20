package com.VictorianApp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderManageData {

    public Integer id_zamowienia;
    public Integer id_produktu;
    public String data_typ_przed;
    public String data_typ_po;
    public Integer typ_produktu;
}
