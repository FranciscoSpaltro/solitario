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
        assertEquals(4, klondike.cantidadCimientos());
        assertEquals(7, klondike.cantidadPilasDelTableau());
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
    public void testMoverPilaAPilaJuegoRecienIniciado(){
        // Arrange
        Klondike klondike = new Klondike(Variante.KLONDIKE);
        klondike.inicializarJuego();

        // Act
        PilaDelTableau  pila5 = klondike.obtenerPilaDelTableau(4);
        PilaDelTableau  pila6 = klondike.obtenerPilaDelTableau(5);
        Carta cartaPila5 = pila5.verUltima();
        Carta cartaPila6 = pila6.verUltima();
        boolean seMovio = klondike.moverPilaAPila(pila5, pila6, 1);
        boolean seMovio2 = klondike.moverPilaAPila(pila5, pila6, 3);

        boolean seTeniaQueMover = false;
        if (cartaPila5.estaBocaArriba() && cartaPila6.estaBocaArriba() && cartaPila5.verColor() != cartaPila6.verColor() && cartaPila6.verValor().ordinal() ==  cartaPila5.verValor().ordinal() -1)
            seTeniaQueMover = true;

        // Assert
        assertEquals(seTeniaQueMover,seMovio);
    }

    @Test
    public void testMoverUnaCartaMazoABasura(){
        // Arrange
        Klondike klondike = new Klondike(Variante.KLONDIKE);
        klondike.inicializarJuego();

        // Act
        int cantidadCartasMazoAntesDeSacarUna = klondike.mazo.cantidadCartas();
        Carta ultimaMazo = klondike.mazo.verUltima();
        boolean seMovio = klondike.moverMazoABasura();

        // Assert

        assertEquals(ultimaMazo.verValor(), klondike.basura.verUltima().verValor());
        assertEquals(ultimaMazo.verPalo(), klondike.basura.verUltima().verPalo());
        assertEquals(cantidadCartasMazoAntesDeSacarUna, klondike.mazo.cantidadCartas() + klondike.basura.cantidadCartas());
        assertEquals(1, klondike.basura.cantidadCartas());
        assertTrue(seMovio);
    }


    @Test
    public void testMoverMasDeTresCartasMazoABasura(){
        // Arrange
        int N = 5;
        Klondike klondike = new Klondike(Variante.KLONDIKE);
        klondike.inicializarJuego();

        // Act
        int cantidadCartasMazoAntesDeSacarUna = klondike.mazo.cantidadCartas();
        Carta ultimaMazo = klondike.mazo.verUltima();
        boolean seMovio = klondike.moverMazoABasura();
        Carta ultimaMazo1 = klondike.mazo.verUltima();
        boolean seMovio1 = klondike.moverMazoABasura();
        Carta ultimaMazo2 = klondike.mazo.verUltima();
        boolean seMovio2 = klondike.moverMazoABasura();
        Carta ultimaMazo3 = klondike.mazo.verUltima();
        boolean seMovio3 = klondike.moverMazoABasura();
        Carta ultimaMazo4 = klondike.mazo.verUltima();
        boolean seMovio4 = klondike.moverMazoABasura();

        // Assert
        assertFalse(ultimaMazo.estaBocaArriba());
        assertFalse(ultimaMazo1.estaBocaArriba());
        assertTrue(ultimaMazo2.estaBocaArriba());
        assertTrue(ultimaMazo3.estaBocaArriba());
        assertTrue(ultimaMazo4.estaBocaArriba());
        assertEquals(cantidadCartasMazoAntesDeSacarUna, klondike.mazo.cantidadCartas() + klondike.basura.cantidadCartas());
        assertEquals(N, klondike.basura.cantidadCartas());
    }

    @Test
    public void testMoverCartasDePilaACimiento() {
        // Arrange
        int N = 5;
        Klondike klondike = new Klondike(Variante.KLONDIKE, true);
        klondike.inicializarJuego();

        // Act
        PilaDelTableau pilaAMover = klondike.obtenerPilaDelTableau(0);
        boolean movioRey = klondike.moverPilaACimiento(pilaAMover, klondike.obtenerCimiento(0));
        pilaAMover = klondike.obtenerPilaDelTableau(4);
        pilaAMover.extraerUltima();
        pilaAMover.extraerUltima();
        boolean movioAs = klondike.moverPilaACimiento(pilaAMover, klondike.obtenerCimiento(0));
        boolean movioDos = klondike.moverPilaACimiento(pilaAMover, klondike.obtenerCimiento(0));
        pilaAMover = klondike.obtenerPilaDelTableau(0);
        boolean movioRey2 = klondike.moverPilaACimiento(pilaAMover, klondike.obtenerCimiento(0));

        // Assert
        assertFalse(movioRey);
        assertTrue(movioAs);
        assertTrue(movioDos);
        assertFalse(movioRey2);
    }

    @Test
    public void testMoverMazoABasura() {
        // Arrange
        Klondike klondike = new Klondike(Variante.KLONDIKE, true);

        // Act
        while (klondike.mazo.cantidadCartas() > 0) {
            klondike.moverMazoABasura();
        }

        // Assert
        assertEquals(0, klondike.mazo.cantidadCartas());
        assertFalse(klondike.moverMazoABasura());
    }

    @Test
    public void testMoverBasuraAMazo(){
        // Arrange
        Klondike klondike = new Klondike(Variante.KLONDIKE, true);

        // Act
        while (klondike.mazo.cantidadCartas() > 5) {
            klondike.moverMazoABasura();
        }

        // Assert
        assertEquals(5, klondike.mazo.cantidadCartas());
        assertEquals(47, klondike.basura.cantidadCartas());
        assertFalse(klondike.moverBasuraAMazo());

        // Act
        while (klondike.mazo.cantidadCartas() > 0) {
            klondike.moverMazoABasura();
        }

        klondike.moverBasuraAMazo();

        // Assert
        assertTrue(klondike.basura.estaVacia());
        assertEquals(52, klondike.mazo.cantidadCartas());

    }

    @Test
    public void testMoverPilaAPilaVacia() {
        // Arrange
        Klondike klondike = new Klondike(Variante.KLONDIKE, true);
        klondike.inicializarJuego();

        // Act
        // Sacar la carta de la primera pila para que quede vacía
        klondike.pilasTableau.get(0).extraerUltima();
        // La última carta de la segunda pila es J de corazones
        boolean seMovio = klondike.moverPilaAPila(klondike.pilasTableau.get(1), klondike.pilasTableau.get(0), 1);

        // Assert
        assertFalse(seMovio);
    }

    @Test
    public void testMoverPilaAPilaPorColor(){
        // Arrange
        Klondike klondike = new Klondike(Variante.KLONDIKE, true);
        klondike.inicializarJuego();

        // Act
        // La primera carta de la primera pila es K corazones (rojo) y la última carta de la última pila es Q treboles (negro)
        boolean seMovio = klondike.moverPilaAPila(klondike.pilasTableau.get(6), klondike.pilasTableau.get(0), 1);

        // Assert
        assertTrue(seMovio);

        // Act
        // Ahora la última carta de la última pila es K tréboles (negro) y la de la primera es Q tréboles (negro)
        seMovio = klondike.moverPilaAPila(klondike.pilasTableau.get(0), klondike.pilasTableau.get(6), 1);

        // Assert
        assertFalse(seMovio);
    }
}