import org.junit.Test;

import static org.junit.Assert.*;

public class KlondikeTest {

    @Test
    public void testInicializarJuego() {
        // Arrange
        Klondike klondike = new Klondike(Variante.KLONDIKE);

        // Assert
        assertEquals(52, klondike.mazo.cantidadCartas());

        // Act
        klondike.inicializarJuego();

        // Assert
        assertEquals(24, klondike.mazo.cantidadCartas());
        assertEquals(4, klondike.cimientos.size());
        assertEquals(7, klondike.pilasTableau.size());
        assertEquals(0, klondike.basura.cantidadCartas());
        assertEquals(0, klondike.puntos);
        int i = 1;
        for (PilaDelTableau pila : klondike.pilasTableau) {
            assertEquals(i, pila.cantidadCartas());
            assertTrue(pila.verUltima().estaBocaArriba());
            i++;
        }
    }

    @Test
    public void testMoverPilaAPila(){
        // Arrange
        Klondike klondike = new Klondike(Variante.KLONDIKE);

    }


    private void generarEntornoDePrueba(Klondike klondike){
        klondike.mazo = new Mazo();
    }
}