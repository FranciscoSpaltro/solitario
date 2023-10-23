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


}
