import org.junit.Test;
import static org.junit.Assert.*;
public class CimientoTest {

    @Test

    public void testChequearId(){
        // Arrange
        Klondike klondike = new Klondike(Variante.KLONDIKE);
        klondike.inicializarJuego();

        // Act
        // Assert
        for (int i = 0; i < klondike.cantidadCimientos(); i++) {
            assertEquals(i, klondike.obtenerCimiento(i).verId());
        }
    }
    @Test
    public void testCimientoEstaCompleto(){
        // Arrange
        Klondike klondike = new Klondike(Variante.KLONDIKE, true);
        klondike.inicializarJuego();

        // Act
        PilaDelTableau pilaAMover = klondike.obtenerPilaDelTableau(4);
        pilaAMover.extraerUltima();
        pilaAMover.extraerUltima();
        boolean movioAs = klondike.moverPilaACimiento(pilaAMover, klondike.obtenerCimiento(0));
        boolean movioDos = klondike.moverPilaACimiento(pilaAMover, klondike.obtenerCimiento(0));
        boolean movioTres = klondike.moverPilaACimiento(pilaAMover, klondike.obtenerCimiento(0));
        pilaAMover = klondike.obtenerPilaDelTableau(3);
        boolean movioCuatro = klondike.moverPilaACimiento(pilaAMover, klondike.obtenerCimiento(0));
        boolean movioCinco = klondike.moverPilaACimiento(pilaAMover, klondike.obtenerCimiento(0));
        boolean movioSeis = klondike.moverPilaACimiento(pilaAMover, klondike.obtenerCimiento(0));
        boolean movioSiete = klondike.moverPilaACimiento(pilaAMover, klondike.obtenerCimiento(0));
        boolean estaCompleto = klondike.obtenerCimiento(0).estaCompleto();
        pilaAMover = klondike.obtenerPilaDelTableau(2);
        boolean movioOcho = klondike.moverPilaACimiento(pilaAMover, klondike.obtenerCimiento(0));
        boolean movioNueve = klondike.moverPilaACimiento(pilaAMover, klondike.obtenerCimiento(0));
        boolean movioDiez = klondike.moverPilaACimiento(pilaAMover, klondike.obtenerCimiento(0));
        pilaAMover = klondike.obtenerPilaDelTableau(1);
        boolean movioOnce = klondike.moverPilaACimiento(pilaAMover, klondike.obtenerCimiento(0));
        boolean movioDoce = klondike.moverPilaACimiento(pilaAMover, klondike.obtenerCimiento(0));
        pilaAMover = klondike.obtenerPilaDelTableau(0);
        boolean movioTrece = klondike.moverPilaACimiento(pilaAMover, klondike.obtenerCimiento(0));

        // Assert
        assertTrue(movioAs);
        assertTrue(movioDos);
        assertTrue(movioTres);
        assertTrue(movioCuatro);
        assertTrue(movioCinco);
        assertTrue(movioSeis);
        assertFalse(estaCompleto);
        assertTrue(movioSiete);
        assertTrue(movioOcho);
        assertTrue(movioNueve);
        assertTrue(movioDiez);
        assertTrue(movioOnce);
        assertTrue(movioDoce);
        assertTrue(movioTrece);

        assertTrue(klondike.obtenerCimiento(0).estaCompleto());
    }
}
