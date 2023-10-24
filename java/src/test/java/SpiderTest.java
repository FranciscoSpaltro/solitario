import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
public class SpiderTest {

    @Test
    public void testInicializarJuego() {
        // Arrange
        Spider spider = new Spider(Variante.SPIDER, Palo.CORAZONES);

        // Assert
        assertEquals(104, spider.obtenerMazo().cantidadCartas());

        // Act
        spider.inicializarJuego();

        // Assert
        assertEquals(50, spider.obtenerMazo().cantidadCartas());
        assertEquals(8, spider.cantidadCimientos());
        assertEquals(10, spider.cantidadPilasDelTableau());
        assertEquals(0, spider.obtenerPuntos());

        for (int i = 0; i < 4; i++) {
            assertEquals(6, spider.obtenerPilaDelTableau(i).cantidadCartas());
            assertTrue(spider.obtenerPilaDelTableau(i).obtenerCarta(5).estaBocaArriba());
        }
        for (int i = 4; i < 10; i++) {
            assertEquals(5, spider.obtenerPilaDelTableau(i).cantidadCartas());
            assertTrue(spider.obtenerPilaDelTableau(i).obtenerCarta(4).estaBocaArriba());
        }

    }

    @Test
    public void testSacarCartasMazo() {
        // Arrange
        Spider spider = new Spider(Variante.SPIDER, Palo.CORAZONES);
        spider.inicializarJuego();

        // Act
        spider.sacarCartasMazo();

        // Assert
        assertEquals(40, spider.obtenerMazo().cantidadCartas());
        for (int i = 0; i < 4; i++) {
            assertEquals(7, spider.obtenerPilaDelTableau(i).cantidadCartas());
            assertTrue(spider.obtenerPilaDelTableau(i).obtenerCarta(5).estaBocaArriba());
            assertTrue(spider.obtenerPilaDelTableau(i).obtenerCarta(6).estaBocaArriba());
        }
        for (int i = 4; i < 10; i++) {
            assertEquals(6, spider.obtenerPilaDelTableau(i).cantidadCartas());
            assertTrue(spider.obtenerPilaDelTableau(i).obtenerCarta(4).estaBocaArriba());
            assertTrue(spider.obtenerPilaDelTableau(i).obtenerCarta(5).estaBocaArriba());
        }
    }

    @Test
    public void testMoverPilaAPila() {
        // Arrange
        Spider spider = new Spider(Variante.SPIDER, Palo.CORAZONES, true);
        spider.inicializarJuego();

        // Act
        PilaDelTableau pilaNueve = spider.obtenerPilaDelTableau(9);
        pilaNueve.extraerUltima();
        pilaNueve.extraerUltima();
        pilaNueve.darVueltaIndex(1);
        pilaNueve.darVueltaIndex(0);
        PilaDelTableau pilaOcho = spider.obtenerPilaDelTableau(8);

        //Muevo creando la pilaOcho del 8 al As
        spider.moverPilaAPila(pilaNueve, pilaOcho, 3);

        //Assert
        assertEquals(8, pilaOcho.cantidadCartas());
        assertEquals(0, pilaNueve.cantidadCartas());
    }

    @Test
    public void testMoverPilaACimiento(){
        // Arrange
        Spider spider = new Spider(Variante.SPIDER, Palo.CORAZONES, true);
        spider.inicializarJuego();

        // Act
        PilaDelTableau pilaNueve = spider.obtenerPilaDelTableau(9);
        pilaNueve.extraerUltima();
        pilaNueve.extraerUltima();
        pilaNueve.darVueltaIndex(1);
        pilaNueve.darVueltaIndex(0);
        PilaDelTableau pilaOcho = spider.obtenerPilaDelTableau(8);

        //Muevo creando la pilaOcho del 8 al As
        spider.moverPilaAPila(pilaNueve, pilaOcho, 3);
        assertEquals(8, pilaOcho.cantidadCartas());

        //Muevo de la pilaOcho a la pila 7
        pilaOcho.darVueltaIndex(0);
        pilaOcho.darVueltaIndex(1);
        pilaOcho.darVueltaIndex(2);
        pilaOcho.darVueltaIndex(3);
        PilaDelTableau pilaSiete = spider.obtenerPilaDelTableau(7);
        spider.moverPilaAPila(pilaOcho, pilaSiete, 8);

        assertEquals(13, pilaSiete.cantidadCartas());

        // Ya tengo la pilaSiete con las cartas del Rey al As, muevo al Cimiento
        pilaSiete.darVueltaIndex(0);
        pilaSiete.darVueltaIndex(1);
        pilaSiete.darVueltaIndex(2);
        pilaSiete.darVueltaIndex(3);

        Cimiento cimientoCero = spider.obtenerCimiento(0);
        spider.moverPilaACimiento(pilaSiete, cimientoCero);
        assertEquals(0, pilaSiete.cantidadCartas());
        assertEquals(13, cimientoCero.cantidadCartas());
    }

    @Test
    public void testReiniciarJuego(){
        // Arrange
        Spider spider = new Spider(Variante.SPIDER, Palo.CORAZONES, true);
        spider.inicializarJuego();

        // Act
        PilaDelTableau pilaNueve = spider.obtenerPilaDelTableau(9);
        pilaNueve.extraerUltima();
        pilaNueve.extraerUltima();

        PilaDelTableau pilaOcho = spider.obtenerPilaDelTableau(8);
        pilaOcho.extraerUltima();
        pilaOcho.extraerUltima();

        int numeroCartasAntesNueve = pilaNueve.cantidadCartas();
        int numeroCartasAntesOcho = pilaOcho.cantidadCartas();

        spider.reiniciar(Palo.CORAZONES);
        PilaDelTableau pilaNueveNueva = spider.obtenerPilaDelTableau(9);
        PilaDelTableau pilaOchoNueva = spider.obtenerPilaDelTableau(8);

        int numeroCartasNuevoNueve = pilaNueveNueva.cantidadCartas();
        int numeroCartasNuevoOcho = pilaOchoNueva.cantidadCartas();

        // Assert
        assertEquals(3, numeroCartasAntesOcho);
        assertEquals(3, numeroCartasAntesNueve);
        assertEquals(5, numeroCartasNuevoOcho);
        assertEquals(5, numeroCartasNuevoNueve);
    }

    @Test
    public void testMoverCartaAPilaQueNoCorresponde(){
        // Arrange
        Spider spider = new Spider(Variante.SPIDER, Palo.CORAZONES, true);
        spider.inicializarJuego();

        // Act
        PilaDelTableau pilaTres = spider.obtenerPilaDelTableau(3);
        PilaDelTableau pilaDos = spider.obtenerPilaDelTableau(2);

        boolean seMovio = true;
        try {
            spider.moverPilaAPila(pilaTres, pilaDos, 1);
        } catch (InvalidMovementException e){
            seMovio = false;
        }

        // Assert
        assertFalse(seMovio);
    }
}
