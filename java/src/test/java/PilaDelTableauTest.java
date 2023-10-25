import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class PilaDelTableauTest {
    @Test
    public void testRepartirPila(){
        // Arrange
        var palos = new ArrayList<Palo>(Arrays.asList(Palo.values()));
        Mazo mazo = new Mazo(palos, 1);
        PilaDelTableau pila = new PilaDelTableau(1);

        // Act
        mazo.mezclar();
        ArrayList<Carta> cartasExtraidas = mazo.extraerUltimasN(7);
        pila.anexarCartas(cartasExtraidas);
        Carta carta1 = pila.obtenerCarta(5);
        pila.darVueltaIndex(4);
        Carta carta2 = pila.obtenerCarta(4);

        // Assert
        assertEquals(7, pila.cantidadCartas());
        assertFalse(carta1.estaBocaArriba());
        assertTrue(carta2.estaBocaArriba());
    }

    @Test
    public void testEliminarCartas(){
        // Arrange
        var palos = new ArrayList<Palo>(Arrays.asList(Palo.values()));
        Mazo mazo = new Mazo(palos, 1);
        PilaDelTableau pila = new PilaDelTableau(0);

        // Act
        mazo.mezclar();
        pila.anexarCartas(mazo.extraerUltimasN(7));
        ArrayList<Carta> cartas = pila.extraerUltimasN(3);

        // Assert
        assertEquals(4, pila.cantidadCartas());
    }

    @Test
    public void testChequearId(){
        // Arrange
        Klondike klondike = new Klondike(Variante.KLONDIKE);
        klondike.inicializarJuego();

        // Act

        // Assert
        for (int i = 0; i < klondike.cantidadPilasDelTableau(); i++) {
            assertEquals(i, klondike.obtenerPilaDelTableau(i).obtenerId());
        }

    }
}
