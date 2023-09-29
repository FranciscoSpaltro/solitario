import org.junit.Test;

import static org.junit.Assert.*;

public class SolitarioTest {
    @Test
    public void testReiniciarJuego(){
        // Arrange
        Klondike klondike = new Klondike(Variante.KLONDIKE);
        klondike.inicializarJuego();

        // Act
        PilaDelTableau  pila = klondike.obtenerPilaDelTableau(4);
        pila.extraerUltima();
        klondike.moverMazoABasura();
        klondike.puntos = 25;
        klondike.reiniciar();

        // Assert
        assertEquals(24, klondike.mazo.cantidadCartas());
        assertEquals(0, klondike.basura.cantidadCartas());
        assertEquals(0, klondike.obtenerPuntos());
        assertEquals(4, klondike.cantidadCimientos());
        assertEquals(7, klondike.cantidadPilasDelTableau());
        assertEquals(1, klondike.obtenerPilaDelTableau(0).cantidadCartas());
        assertEquals(2, klondike.obtenerPilaDelTableau(1).cantidadCartas());
        assertEquals(3, klondike.obtenerPilaDelTableau(2).cantidadCartas());
        assertEquals(4, klondike.obtenerPilaDelTableau(3).cantidadCartas());
        assertEquals(5, klondike.obtenerPilaDelTableau(4).cantidadCartas());
        assertEquals(6, klondike.obtenerPilaDelTableau(5).cantidadCartas());
        assertEquals(7, klondike.obtenerPilaDelTableau(6).cantidadCartas());
    }
}