/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.idojarashazi.service;
import com.mycompany.idojarashazi.model.Idojaras;


/**
 *
 * @author Denes
 */
public interface IdojarasSzolgaltatas {

    Idojaras aktualis(double lat, double lon)
            throws IdojarasKivetel;
}