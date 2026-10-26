/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test.com.mycompany.idojarashazi.service;
import com.mycompany.idojarashazi.model.Idojaras;
import com.mycompany.idojarashazi.service.IdojarasKivetel;
import com.mycompany.idojarashazi.service.JsonHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author Denes
 */
class JsonHelperTest {

    private final JsonHelper feldolgozo = new JsonHelper();

    @Test
    void helyesJsonFeldolgozasa() throws IdojarasKivetel {
        String json = """
                {
                    "current": {
                        "temperature_2m": 18.5,
                        "wind_speed_10m": 12.3,
                        "time": "2026-10-26T15:00"
                    }
                }
                """;

        Idojaras eredmeny = feldolgozo.feldolgoz(json);

        assertEquals(18.5, eredmeny.homerseklet());
        assertEquals(12.3, eredmeny.szel());
        assertEquals("2026-10-26T15:00", eredmeny.idopont());
    }

    @Test
    void hibasJsonSajatKiveteltDob() {
        String json = "{ hibas json }";

        assertThrows(
                IdojarasKivetel.class,
                () -> feldolgozo.feldolgoz(json)
        );
    }

    @Test
    void hianyzoAdatokSajatKiveteltDob() {
        String json = """
                {
                    "current": {
                        "temperature_2m": 18.5
                    }
                }
                """;

        assertThrows(
                IdojarasKivetel.class,
                () -> feldolgozo.feldolgoz(json)
        );
    }
}
