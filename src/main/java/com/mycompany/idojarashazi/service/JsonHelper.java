/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.idojarashazi.service;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonParseException;
import com.mycompany.idojarashazi.model.Idojaras;
/**
 *
 * @author Denes
 */
public class JsonHelper {
    public Idojaras feldolgoz(String json)
            throws IdojarasKivetel {

        try {
            JsonObject gyoker =
                    JsonParser.parseString(json).getAsJsonObject();

            JsonObject aktualis =
                    gyoker.getAsJsonObject("current");

            if (aktualis == null
                    || !aktualis.has("temperature_2m")
                    || !aktualis.has("wind_speed_10m")
                    || !aktualis.has("time")) {

                throw new IdojarasKivetel(
                        "Hiányzó adatok az API válaszában."
                );
            }

            double homerseklet =
                    aktualis.get("temperature_2m").getAsDouble();

            double szel =
                    aktualis.get("wind_speed_10m").getAsDouble();

            String idopont =
                    aktualis.get("time").getAsString();

            return new Idojaras(
                    homerseklet,
                    szel,
                    idopont
            );

        } catch (IdojarasKivetel e) {
            throw e;
        } catch (JsonParseException
                 | IllegalStateException
                 | UnsupportedOperationException
                 | NumberFormatException e) {

            throw new IdojarasKivetel(
                    "Nem sikerült feldolgozni az időjárási adatokat.",
                    e
            );
        }
    }
}
