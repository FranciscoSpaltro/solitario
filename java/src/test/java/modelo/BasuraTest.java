package modelo;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BasuraTest {
    @Test
    public void testMostrarUltimasTres() {
        // Arrange
        Klondike klondike = new Klondike(Variante.KLONDIKE,new MovimientoAPilaKlondike(), true);
        klondike.inicializarJuego();

        // Act
        boolean movidoABasura = true;
        try {
            klondike.moverMazoABasura();
        } catch (InvalidMovementException e) {
            movidoABasura = false;
        }

        klondike.moverMazoABasura();
        klondike.moverMazoABasura();
        klondike.moverMazoABasura();
        ArrayList<Carta> ultimasTres = klondike.obtenerBasura().mostrarCartasBasura();
        Carta carta1 = ultimasTres.remove(0);
        Carta carta2 = ultimasTres.remove(0);
        Carta carta3 = ultimasTres.remove(0);
        Carta carta4 = klondike.obtenerBasura().obtenerCarta(0);

        // Assert
        assertEquals(Valor.DIEZ, carta1.verValor());
        assertEquals(Palo.DIAMANTES, carta1.verPalo());
        assertTrue(carta1.estaBocaArriba());
        assertEquals(Valor.NUEVE, carta2.verValor());
        assertEquals(Palo.DIAMANTES, carta2.verPalo());
        assertTrue(carta2.estaBocaArriba());
        assertEquals(Valor.OCHO, carta3.verValor());
        assertEquals(Palo.DIAMANTES, carta3.verPalo());
        assertTrue(carta3.estaBocaArriba());
        assertEquals(Valor.JOTA, carta4.verValor());
        assertEquals(Palo.DIAMANTES, carta4.verPalo());
        assertTrue(carta4.estaBocaArriba());
        assertTrue(movidoABasura);
    }
}