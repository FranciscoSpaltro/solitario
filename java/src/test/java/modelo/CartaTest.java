package modelo;

import org.junit.Test;

import static org.junit.Assert.*;

public class CartaTest {
    @Test
    public void testCartasValidas() {
        // Arrange
        Carta c1 = new Carta(Valor.AS, Palo.CORAZONES, true);
        Carta c2 = new Carta(Valor.REY, Palo.PICAS, false);
        Carta c3 = new Carta(Valor.SIETE, Palo.DIAMANTES, true);
        Carta c4 = new Carta(Valor.CUATRO, Palo.TREBOLES, false);

        // Act

        // Assert
        assertEquals(Valor.AS, c1.verValor());
        assertEquals(Palo.CORAZONES, c1.verPalo());
        assertTrue(c1.estaBocaArriba());
        assertEquals(Valor.REY, c2.verValor());
        assertEquals(Palo.PICAS, c2.verPalo());
        assertFalse(c2.estaBocaArriba());
        assertEquals(Valor.SIETE, c3.verValor());
        assertEquals(Palo.DIAMANTES, c3.verPalo());
        assertTrue(c3.estaBocaArriba());
        assertEquals(Valor.CUATRO, c4.verValor());
        assertEquals(Palo.TREBOLES, c4.verPalo());
        assertFalse(c4.estaBocaArriba());
    }

    @Test
    public void testDarVuelta() {
        // Arrange
        Carta c1 = new Carta(Valor.AS, Palo.CORAZONES, true);
        Carta c2 = new Carta(Valor.REY, Palo.PICAS, false);
        Carta c3 = new Carta(Valor.SIETE, Palo.DIAMANTES, true);
        Carta c4 = new Carta(Valor.CUATRO, Palo.TREBOLES, false);

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