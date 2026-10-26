/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.idojarashazi.Controller;

import com.mycompany.idojarashazi.model.Idojaras;
import com.mycompany.idojarashazi.service.IdojarasKivetel;
import com.mycompany.idojarashazi.service.IdojarasSzolgaltatas;

/**
 *
 * @author Denes
 */
public class IdojarasPresenter {
     private final IdojarasSzolgaltatas szolgaltatas;

    public IdojarasPresenter(
            IdojarasSzolgaltatas szolgaltatas) {

        this.szolgaltatas = szolgaltatas;
    }

    public Idojaras lekeres(double lat, double lon)
            throws IdojarasKivetel {

        return szolgaltatas.aktualis(lat, lon);
    }
}
