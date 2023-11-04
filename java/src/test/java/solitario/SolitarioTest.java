package solitario;

import org.junit.Test;
import solitario.Cimiento;
import solitario.Klondike;
import solitario.PilaDelTableau;
import solitario.Variante;

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

        // Después de modificar el tamaño de la pila 5, el tamaño del mazo y de basura y los puntos,
        // se reinicia el juego, y se chequean que los valores de los atributos sean los iniciales

        // Assert
        assertEquals(24, klondike.obtenerMazo().cantidadCartas());
        assertEquals(0, klondike.obtenerBasura().cantidadCartas());
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

    @Test
    public void testJugadorGano(){
        // Arrange
        Klondike klondike = new Klondike(Variante.KLONDIKE, true);
        klondike.inicializarJuego();

        // Act
        for (int i = 0; i < 11; i++)
            klondike.moverMazoABasura();
        Cimiento cimientoDestino = klondike.obtenerCimiento(0);
        for (int i = 0; i < 11; i++)
            klondike.moverBasuraACimiento(cimientoDestino);
        PilaDelTableau pilaAMover = klondike.obtenerPilaDelTableau(6);
        for (int i = 0; i < 2; i++)
            klondike.moverPilaACimiento(pilaAMover, cimientoDestino);
        // Quedan en el cimiento 0 todas las cartas de diamante

        cimientoDestino = klondike.obtenerCimiento(1);
        for (int i = 0; i < 5; i++)
            klondike.moverPilaACimiento(pilaAMover, cimientoDestino);
        pilaAMover = klondike.obtenerPilaDelTableau(5);
        for (int i = 0; i < 6; i++)
            klondike.moverPilaACimiento(pilaAMover, cimientoDestino);
        pilaAMover = klondike.obtenerPilaDelTableau(4);
        for (int i = 0; i < 2; i++)
            klondike.moverPilaACimiento(pilaAMover, cimientoDestino);
        // Quedan en el cimiento 1 todas las cartas de tréboles

        cimientoDestino = klondike.obtenerCimiento(2);
        for (int i = 0; i < 3; i++)
            klondike.moverPilaACimiento(pilaAMover, cimientoDestino);
        pilaAMover = klondike.obtenerPilaDelTableau(3);
        for (int i = 0; i < 4; i++)
            klondike.moverPilaACimiento(pilaAMover, cimientoDestino);
        pilaAMover = klondike.obtenerPilaDelTableau(2);
        for (int i = 0; i < 3; i++)
            klondike.moverPilaACimiento(pilaAMover, cimientoDestino);
        pilaAMover = klondike.obtenerPilaDelTableau(1);
        for (int i = 0; i < 2; i++)
            klondike.moverPilaACimiento(pilaAMover, cimientoDestino);
        pilaAMover = klondike.obtenerPilaDelTableau(0);
        klondike.moverPilaACimiento(pilaAMover, cimientoDestino);
        // Quedan en el cimiento 2 todas las cartas de picas

        cimientoDestino = klondike.obtenerCimiento(3);
        for (int i = 0; i < 13; i++)
            klondike.moverMazoABasura();
        for (int i = 0; i < 13; i++)
            klondike.moverBasuraACimiento(cimientoDestino);
        // Quedan en el cimiento 3 todas las cartas de corazones

        // Assert
        assertTrue(klondike.jugadorGano());
    }
}