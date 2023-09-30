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
        Klondike klondike = new Klondike(Variante.KLONDIKE, true);
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
        assertTrue(seMovio && seMovio1 && seMovio2 && seMovio3 && seMovio4);
        assertFalse(ultimaMazo.estaBocaArriba());
        assertFalse(ultimaMazo1.estaBocaArriba());
        assertTrue(ultimaMazo2.estaBocaArriba());
        assertTrue(ultimaMazo3.estaBocaArriba());
        assertTrue(ultimaMazo4.estaBocaArriba());
        assertEquals(cantidadCartasMazoAntesDeSacarUna, klondike.mazo.cantidadCartas() + klondike.basura.cantidadCartas());
        assertEquals(N, klondike.basura.cantidadCartas());
    }

    @Test
    public void testMoverCartasEntreCimientoYPila() {
        // Arrange
        Klondike klondike = new Klondike(Variante.KLONDIKE, true);
        klondike.inicializarJuego();

        // Act
        PilaDelTableau pilaAMover = klondike.obtenerPilaDelTableau(4);
        Cimiento cimientoDestino = klondike.obtenerCimiento(0);

        pilaAMover.extraerUltima();
        pilaAMover.extraerUltima();
        // En la pila 4 queda el 1 de picas

        // Completo el cimiento 0 hasta la J de picas
        for (int i = 0; i < 3; i++)
            klondike.moverPilaACimiento(pilaAMover, cimientoDestino);
        pilaAMover = klondike.obtenerPilaDelTableau(3);
        boolean juegoTerminado = klondike.jugadorGano();
        for (int i = 0; i < 4; i++)
            klondike.moverPilaACimiento(pilaAMover, cimientoDestino);
        pilaAMover = klondike.obtenerPilaDelTableau(2);
        for (int i = 0; i < 3; i++)
            klondike.moverPilaACimiento(pilaAMover, cimientoDestino);
        pilaAMover = klondike.obtenerPilaDelTableau(1);
        klondike.moverPilaACimiento(pilaAMover, cimientoDestino);
        // Quedó el cimiento con as, 2 ... J de picas

        // Muevo J de picas al Q de tréboles
        PilaDelTableau pilaDestino = klondike.obtenerPilaDelTableau(6);
        Cimiento cimientoOrigen = cimientoDestino;
        boolean movioJ = klondike.moverCimientoAPila(cimientoOrigen, pilaDestino);

        // Vuelvo a mover J de picas al cimiento 0
        klondike.moverPilaACimiento(pilaDestino, cimientoDestino);

        // Libero Q de diamantes
        PilaDelTableau pilaConQDeDiamantes = klondike.obtenerPilaDelTableau(6);
        pilaConQDeDiamantes.extraerUltima();
        // Intento mover Q de diamantes al cimiento con J de picas (!= palo)
        boolean movioQ = klondike.moverPilaACimiento(pilaConQDeDiamantes, cimientoDestino);

        // Agarro un nuevo cimiento vacío
        Cimiento cimientoNuevo = klondike.obtenerCimiento(1);
        // En la pila 1 está la reina de picas
        boolean moverReinaCimientoVacio = klondike.moverPilaACimiento(klondike.obtenerPilaDelTableau(1), cimientoNuevo);

        // Assert
        assertTrue(movioJ);
        assertFalse(moverReinaCimientoVacio);
        assertFalse(juegoTerminado);
        assertFalse(movioQ);
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
    public void testMoverBasuraAPila(){
        // Arrange
        Klondike klondike = new Klondike(Variante.KLONDIKE, true);
        klondike.inicializarJuego();

        // Act
        klondike.moverMazoABasura();
        // Queda un J diamantes (rojo) en la basura y Q treboles (negro) en la pila id 4

        PilaDelTableau pilaDestino = klondike.obtenerPilaDelTableau(4);
        boolean seMovio = klondike.moverBasuraAPila(pilaDestino);
        Carta cartaMovida = pilaDestino.verUltima();

        // Assert
        assertTrue(seMovio);
        assertEquals(5, klondike.obtenerPuntos());
        assertEquals(Palo.DIAMANTES, cartaMovida.verPalo());
        assertEquals(Valor.JOTA, cartaMovida.verValor());
        assertTrue(klondike.basura.estaVacia());
    }

    @Test
    public void testMoverBasuraACimiento(){
        // Arrange
        Klondike klondike = new Klondike(Variante.KLONDIKE, true);
        klondike.inicializarJuego();

        // Act
        for (int i = 0; i < 11; i++)
            klondike.moverMazoABasura();
        // Queda un as diamantes (rojo) en la basura y los cimientos vacíos

        Cimiento cimientoDestino = klondike.obtenerCimiento(0);
        boolean seMovio = klondike.moverBasuraACimiento(cimientoDestino);
        Carta cartaMovida = cimientoDestino.verUltima();

        // Assert
        assertTrue(seMovio);
        assertEquals(10, klondike.obtenerPuntos());
        assertEquals(Palo.DIAMANTES, cartaMovida.verPalo());
        assertEquals(Valor.AS, cartaMovida.verValor());
        assertFalse(klondike.basura.estaVacia());
        assertFalse(cimientoDestino.estaVacia());
        assertTrue(cimientoDestino.verUltima().estaBocaArriba());
    }

    @Test
    public void testMoverBasuraAMazo(){
        // Arrange
        Klondike klondike = new Klondike(Variante.KLONDIKE, true);

        // Act
        while (klondike.mazo.cantidadCartas() > 5) {
            klondike.moverMazoABasura();
        }

        klondike.puntos = 50;

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
        assertEquals(0, klondike.obtenerPuntos());
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
        // La última carta de la segunda pila es J de picas
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
        boolean seMovio = klondike.moverPilaAPila(klondike.pilasTableau.get(6), klondike.pilasTableau.get(0), 1);
        // La primera carta de la primera pila es K picas (negro) y la última carta de la última pila es Q diamantes (rojo)

        // Assert
        assertTrue(seMovio);
        assertEquals(5, klondike.obtenerPuntos());

        // Act
        seMovio = klondike.moverPilaAPila(klondike.pilasTableau.get(0), klondike.pilasTableau.get(6), 1);
        // Ahora la última carta de la última pila es K diamantes (rojo) y la de la primera es Q diamantes (rojo)

        // Assert
        assertFalse(seMovio);
        assertEquals(5, klondike.obtenerPuntos());
    }
}