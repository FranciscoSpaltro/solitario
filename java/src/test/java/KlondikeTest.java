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
}



    //private void generarEntornoDePrueba(){
    //    Mazo mazo = new Mazo();
    //}
