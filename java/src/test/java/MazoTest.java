import org.junit.Test;
import static org.junit.Assert.*;

public class MazoTest {
    @Test
    public void testMazoCantidad() {
        // Arrange
        Mazo mazo = new Mazo();

        // Act

        // Assert
        assertEquals(52, mazo.cantidadCartas());
    }

    @Test
    public void testMazoCartas() {
        // Arrange
        Mazo mazo = new Mazo();

        // Act
        Carta carta1 =  mazo.verUltima();

        // Assert
        assertEquals(13, carta1.verValor());
        assertEquals("Picas", carta1.verPalo());
        assertFalse(carta1.estaBocaArriba());
        assertEquals(52, mazo.cantidadCartas());
    }

    // ¿Cómo se podría validar mezclar?
}