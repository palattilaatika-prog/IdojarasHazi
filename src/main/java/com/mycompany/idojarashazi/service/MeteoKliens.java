/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.idojarashazi.service;

import com.mycompany.idojarashazi.model.Idojaras;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Locale;
/**
 *
 * @author Denes
 */
public class MeteoKliens implements IdojarasSzolgaltatas {
    private final HttpClient httpClient;
    private final JsonHelper feldolgozo;

    public MeteoKliens() {
        httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();

        feldolgozo = new JsonHelper();
    }

    @Override
    public Idojaras aktualis(double lat, double lon)
            throws IdojarasKivetel {

        if (!Double.isFinite(lat) || lat < -90 || lat > 90
                || !Double.isFinite(lon)
                || lon < -180 || lon > 180) {

            throw new IdojarasKivetel(
                    "Érvénytelen koordináták."
            );
        }

        String url = String.format(
                Locale.US,
                "https://api.open-meteo.com/v1/forecast"
                + "?latitude=%.4f&longitude=%.4f"
                + "&current=temperature_2m,wind_speed_10m",
                lat,
                lon
        );

        HttpRequest keres = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        try {
            HttpResponse<String> valasz = httpClient.send(
                    keres,
                    HttpResponse.BodyHandlers.ofString()
            );

            if (valasz.statusCode() != 200) {
                throw new IdojarasKivetel(
                        "Az időjárási API hibát adott: HTTP "
                                + valasz.statusCode()
                );
            }

            return feldolgozo.feldolgoz(valasz.body());

        } catch (IOException e) {
            throw new IdojarasKivetel(
                    "Nem sikerült elérni az időjárási szolgáltatást.",
                    e
            );
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

            throw new IdojarasKivetel(
                    "Az időjárás lekérése megszakadt.",
                    e
            );
        }
    }
}
