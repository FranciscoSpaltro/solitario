import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PilaDelTableauTest {
    @Test
    public void testRepartirPila(){
        // Arrange
        Mazo mazo = new Mazo();
        PilaDelTableau pila = new PilaDelTableau(0);

        // Act
        mazo.mezclar();
        pila.anexarCartas(mazo.extraerUltimasN(7));
        Carta carta1 = pila.obtenerCarta(6);
        pila.darVueltaIndex(6);
        Carta carta2 = pila.obtenerCarta(6);

        // Assert
        assertEquals(7, pila.cantidadCartas());
        assertEquals(carta1.verValor(), carta2.verValor());
        assertEquals(carta1.verPalo(), carta2.verPalo());
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
}
