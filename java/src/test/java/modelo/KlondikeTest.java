package modelo;// Para este archivo se agrega un anexo explicativo en el informe

import org.junit.Test;

import static org.junit.Assert.*;

public class KlondikeTest {

    @Test
    public void testInicializarJuego() {
        // Arrange
        Klondike klondike = new Klondike(Variante.KLONDIKE, false);

        // Assert
        assertEquals(52, klondike.mazo.cantidadCartas());

        // Act
        // Se reparten las cartas del mazo en las PilasDelTableau
        // Quedan 24 cartas en el mazo, se generan 4 cimientos, 7 PilasDelTableau y se inicializa la basura en 0
        klondike.inicializarJuego();

        // Assert
        assertEquals(24, klondike.mazo.cantidadCartas());
        assertEquals(4, klondike.cantidadCimientos());
        assertEquals(7, klondike.cantidadPilasDelTableau());
        assertEquals(0, klondike.obtenerBasura().cantidadCartas());
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
        Klondike klondike = new Klondike(Variante.KLONDIKE, false);
        klondike.inicializarJuego();

        // Act
        // Pruebo si puedo mover la última carta de la pila 4 a la pila 5, dependiendo de
        // como se reparta el mazo, se podrá mover o no la carta
        PilaDelTableau pila4 = klondike.obtenerPilaDelTableau(4);
        PilaDelTableau pila5 = klondike.obtenerPilaDelTableau(5);
        Carta cartaPila4 = pila4.verUltima();
        Carta cartaPila5 = pila5.verUltima();

        boolean seMovio = true;
        try {
            klondike.moverPilaAPila(pila4, pila5, 1);
        } catch (InvalidMovementException e) {
            seMovio = false;
        }

        boolean seTeniaQueMover = false;
        if (cartaPila4.estaBocaArriba()
                && cartaPila5.estaBocaArriba()
                && !cartaPila4.verPalo().mismoColor(cartaPila5.verPalo())
                && cartaPila4.verValor().ordinal() ==  cartaPila5.verValor().ordinal() + 1)
            seTeniaQueMover = true;

        // Assert
        assertEquals(seTeniaQueMover,seMovio);
    }

    @Test
    public void testMoverUnaCartaMazoABasura(){
        // Arrange
        Klondike klondike = new Klondike(Variante.KLONDIKE, false);
        klondike.inicializarJuego();

        // Act
        // Muevo una carta del mazo a la basura y chequeo que sea la misma
        int cantidadCartasMazoAntesDeSacarUna = klondike.obtenerMazo().cantidadCartas();
        Carta ultimaMazo = klondike.obtenerMazo().verUltima();

        boolean seMovio = true;

        try {
            klondike.moverMazoABasura();
        } catch (InvalidMovementException e) {
            seMovio = false;
        }

        // Assert

        assertEquals(ultimaMazo.verValor(), klondike.obtenerBasura().verUltima().verValor());
        assertEquals(ultimaMazo.verPalo(), klondike.obtenerBasura().verUltima().verPalo());
        assertEquals(cantidadCartasMazoAntesDeSacarUna, klondike.obtenerMazo().cantidadCartas() + klondike.obtenerBasura().cantidadCartas());
        assertEquals(1, klondike.obtenerBasura().cantidadCartas());
        assertTrue(seMovio);
    }


    /*@Test
    public void testMoverMasDeTresCartasMazoABasura(){
        // Arrange
        int N = 5;
        Klondike klondike = new Klondike(Variante.KLONDIKE, false);
        klondike.inicializarJuego();
        boolean seMovio = true;
        boolean seMovio1 = true;
        boolean seMovio2 = true;
        boolean seMovio3 = true;
        boolean seMovio4 = true;

        // Act
        int cantidadCartasMazoAntesDeSacarUna = klondike.obtenerMazo().cantidadCartas();

        Carta ultimaMazo = klondike.obtenerMazo().verUltima();
        try {
            klondike.moverMazoABasura();
        } catch (InvalidMovementException e) {
            seMovio = false;
        }

        Carta ultimaMazo1 = klondike.obtenerMazo().verUltima();
        try {
            klondike.moverMazoABasura();
        } catch (InvalidMovementException e) {
            seMovio1 = false;
        }

        Carta ultimaMazo2 = klondike.obtenerMazo().verUltima();
        try {
            klondike.moverMazoABasura();
        } catch (InvalidMovementException e) {
            seMovio2 = false;
        }

        Carta ultimaMazo3 = klondike.obtenerMazo().verUltima();
        try {
            klondike.moverMazoABasura();
        } catch (InvalidMovementException e) {
            seMovio3 = false;
        }

        Carta ultimaMazo4 = klondike.obtenerMazo().verUltima();
        try {
            klondike.moverMazoABasura();
        } catch (InvalidMovementException e) {
            seMovio4 = false;
        }

        // Se busca probar que al sacar 5 cartas en el mazo solo se puedan visualizar las últimas 3
        // por eso las dos primeras que saco del mazo, deben tener estaBocaAbajo = false

        // Assert
        assertTrue(seMovio && seMovio1 && seMovio2 && seMovio3 && seMovio4);
        assertFalse(ultimaMazo.estaBocaArriba());
        assertFalse(ultimaMazo1.estaBocaArriba());
        assertTrue(ultimaMazo2.estaBocaArriba());
        assertTrue(ultimaMazo3.estaBocaArriba());
        assertTrue(ultimaMazo4.estaBocaArriba());
        assertEquals(cantidadCartasMazoAntesDeSacarUna, klondike.obtenerMazo().cantidadCartas() + klondike.obtenerBasura().cantidadCartas());
        assertEquals(N, klondike.obtenerBasura().cantidadCartas());
    }*/

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
        boolean movioJ = true;
        try {
            klondike.moverCimientoAPila(cimientoOrigen, pilaDestino);
        } catch (InvalidMovementException e) {
            movioJ = false;
        }

        // Vuelvo a mover J de picas al cimiento 0
        klondike.moverPilaACimiento(pilaDestino, cimientoDestino);

        PilaDelTableau pilaConQDeDiamantes = klondike.obtenerPilaDelTableau(6);
        // Intento mover Q de diamantes al cimiento con J de picas (!= palo)
        boolean movioQ = true;
        try {
            klondike.moverPilaACimiento(pilaConQDeDiamantes, cimientoDestino);
        } catch (InvalidMovementException e) {
            movioQ = false;
        }

        // Agarro un nuevo cimiento vacío
        Cimiento cimientoNuevo = klondike.obtenerCimiento(1);
        // En la pila 1 está la reina de picas
        boolean moverReinaCimientoVacio = true;
        try {
            klondike.moverPilaACimiento(klondike.obtenerPilaDelTableau(1), cimientoNuevo);
        } catch (InvalidMovementException e) {
            moverReinaCimientoVacio = false;
        }

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

        boolean seMovio = true;
        try {
            klondike.moverMazoABasura();
        } catch (InvalidMovementException e) {
            seMovio = false;
        }

        // Assert
        assertEquals(0, klondike.mazo.cantidadCartas());
        assertFalse(seMovio);
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
        boolean seMovio = true;
        try {
            klondike.moverBasuraAPila(pilaDestino);
        } catch (InvalidMovementException e) {
            seMovio = false;
        }

        Carta cartaMovida = pilaDestino.verUltima();

        // Assert
        assertTrue(seMovio);
        assertEquals(5, klondike.obtenerPuntos());
        assertEquals(Palo.DIAMANTES, cartaMovida.verPalo());
        assertEquals(Valor.JOTA, cartaMovida.verValor());
        assertTrue(klondike.obtenerBasura().estaVacia());
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
        boolean seMovio = true;
        try {
            klondike.moverBasuraACimiento(cimientoDestino);
        } catch (InvalidMovementException e) {
            seMovio = false;
        }
        Carta cartaMovida = cimientoDestino.verUltima();

        // Assert
        assertTrue(seMovio);
        assertEquals(10, klondike.obtenerPuntos());
        assertEquals(Palo.DIAMANTES, cartaMovida.verPalo());
        assertEquals(Valor.AS, cartaMovida.verValor());
        assertFalse(klondike.obtenerBasura().estaVacia());
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
        boolean seMovio = true;

        // Assert
        assertEquals(5, klondike.mazo.cantidadCartas());
        assertEquals(47, klondike.obtenerBasura().cantidadCartas());

        try {
            klondike.moverBasuraAMazo();
        } catch (InvalidMovementException e) {
            seMovio = false;
        }
        assertFalse(seMovio);

        // Act
        while (klondike.mazo.cantidadCartas() > 0) {
            klondike.moverMazoABasura();
        }

        klondike.moverBasuraAMazo();

        // Assert
        assertEquals(0, klondike.obtenerPuntos());
        assertTrue(klondike.obtenerBasura().estaVacia());
        assertEquals(52, klondike.mazo.cantidadCartas());

    }

    @Test
    public void testMoverPilaAPilaVacia() {
        // Arrange
        Klondike klondike = new Klondike(Variante.KLONDIKE, true);
        klondike.inicializarJuego();

        // Act
        // Sacar la carta de la primera pila para que quede vacía
        klondike.obtenerPilaDelTableau(0).extraerUltima();
        // La última carta de la segunda pila es J de picas
        boolean seMovio = true;
        try {
            klondike.moverPilaAPila(klondike.obtenerPilaDelTableau(1), klondike.obtenerPilaDelTableau(0), 1);
        } catch(InvalidMovementException e) {
            seMovio = false;
        }

        // Assert
        assertFalse(seMovio);
    }

    @Test
    public void testMoverPilaAPilaPorColor(){
        // Arrange
        Klondike klondike = new Klondike(Variante.KLONDIKE, true);
        klondike.inicializarJuego();

        // Act
        boolean seMovio = true;
        try {
            klondike.moverPilaAPila(klondike.obtenerPilaDelTableau(6), klondike.obtenerPilaDelTableau(0), 1);
        } catch (InvalidMovementException e) {
            seMovio = false;
        }

        // La primera carta de la primera pila es K picas (negro) y la última carta de la última pila es Q diamantes (rojo)

        // Assert
        assertTrue(seMovio);
        assertEquals(5, klondike.obtenerPuntos());

        // Act
        try {
            klondike.moverPilaAPila(klondike.obtenerPilaDelTableau(0), klondike.obtenerPilaDelTableau(6), 1);
        } catch(InvalidMovementException e) {
            seMovio = false;
        }

        // Ahora la última carta de la última pila es K diamantes (rojo) y la de la primera es Q diamantes (rojo)

        // Assert
        assertFalse(seMovio);
        assertEquals(5, klondike.obtenerPuntos());
    }
}