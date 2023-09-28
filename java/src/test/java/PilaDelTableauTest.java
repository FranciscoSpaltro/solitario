import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PilaDelTableauTest {
    @Test
    public void testRepartirPila(){
        // Arrange
        Mazo mazo = new Mazo();
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
        Mazo mazo = new Mazo();
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
            assertEquals(i, klondike.pilasTableau.get(i).obtenerId());
        }

    }
}
