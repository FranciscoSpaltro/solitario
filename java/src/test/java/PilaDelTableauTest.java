import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PilaDelTableauTest {
    @Test
    public void testRepartirPila(){
        // Arrange
        Mazo mazo = new Mazo();
        PilaDelTableau pila = new PilaDelTableau(0);

        // Act
        mazo.mezclar();
        pila.anexarCartas(mazo.extraerUltimasN(7));
        boolean aux = pila.darVueltaIndex(6);

        // Assert
        assertEquals(7, pila.cantidadCartas());
        assertTrue(aux);

    }
}
