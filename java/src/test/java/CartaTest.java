import org.junit.Test;

import static org.junit.Assert.*;

public class CartaTest {
    @Test
    public void testCartasValidas() {
        // Arrange
        Carta c1 = new Carta(1, "Corazones", true);
        Carta c2 = new Carta(13, "Picas", false);
        Carta c3 = new Carta(7, "Diamantes", true);
        Carta c4 = new Carta(4, "Tréboles", false);

        // Act

        // Assert
        assertEquals(1, c1.verValor());
        assertEquals("Corazones", c1.verPalo());
        assertTrue(c1.estaBocaArriba());
        assertEquals(13, c2.verValor());
        assertEquals("Picas", c2.verPalo());
        assertFalse(c2.estaBocaArriba());
        assertEquals(7, c3.verValor());
        assertEquals("Diamantes", c3.verPalo());
        assertTrue(c3.estaBocaArriba());
        assertEquals(4, c4.verValor());
        assertEquals("Tréboles", c4.verPalo());
        assertFalse(c4.estaBocaArriba());
    }

    @Test
    public void testDarVuelta() {
        // Arrange
        Carta c1 = new Carta(1, "Corazones", true);
        Carta c2 = new Carta(13, "Picas", false);
        Carta c3 = new Carta(7, "Diamantes", true);
        Carta c4 = new Carta(4, "Tréboles", false);

        // Act
        c1.darVuelta();
        c2.darVuelta();
        c3.darVuelta();
        c4.darVuelta();

        // Assert
        assertFalse(c1.estaBocaArriba());
        assertTrue(c2.estaBocaArriba());
        assertFalse(c3.estaBocaArriba());
        assertTrue(c4.estaBocaArriba());
    }
}