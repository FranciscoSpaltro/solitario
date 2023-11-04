package solitario;

import org.junit.Test;
import solitario.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SpiderDificilTest {

    @Test
    public void inicializarJuegoTest() {
        // Arrange
        ArrayList<Palo> palos = new ArrayList<Palo>();
        palos.add(Palo.CORAZONES);
        palos.add(Palo.TREBOLES);

        var spider = new Spider(Variante.SPIDER, palos, new MovimientoACimientoSpiderDificil(), new MovimientoAPilaSpiderDificil(), true);

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
    public void moverPilaAPilaTest() {
        // Arrange
        ArrayList<Palo> palos = new ArrayList<Palo>();
        palos.add(Palo.CORAZONES);
        palos.add(Palo.TREBOLES);

        var spider = new Spider(Variante.SPIDER, palos, new MovimientoACimientoSpiderDificil(), new MovimientoAPilaSpiderDificil(), true);
        spider.inicializarJuego();

        boolean resultado = true;
        try {
            spider.moverPilaAPila(spider.obtenerPilaDelTableau(6), spider.obtenerPilaDelTableau(5), 5);
        }
        catch (InvalidMovementException e) {
            resultado = false;
            assertEquals(ErrorAlMover.CARTA_A_MOVER_NO_BOCA_ARRIBA, e.obtenerMotivo());
        }
        assertFalse(resultado);

        resultado = true;

        try {
            spider.moverPilaAPila(spider.obtenerPilaDelTableau(1), spider.obtenerPilaDelTableau(3), 1);
        }
        catch (InvalidMovementException e) {
            resultado = false;
        }
        assertTrue(resultado);

        resultado = true;
        // Pruebo que se pueda mover de una pila a otra con distinto color pero descendente
        try {
            spider.moverPilaAPila(spider.obtenerPilaDelTableau(4), spider.obtenerPilaDelTableau(9), 1);
        } catch (InvalidMovementException e) {
            resultado = false;
        }
        assertTrue(resultado);

        resultado = true;
        // Pruebo que no se pueda mover si las cartas visibles son de distinto color
        var pilaDestino = spider.obtenerPilaDelTableau(4);
        pilaDestino.extraerUltima();

        try {
            spider.moverPilaAPila(spider.obtenerPilaDelTableau(9), pilaDestino, 2);
        } catch (InvalidMovementException e) {
            resultado = false;
            assertEquals(ErrorAlMover.CARTAS_A_MOVER_DISTINTO_PALO, e.obtenerMotivo());
        }
        assertFalse(resultado);
    }

    @Test
    public void movimientoACimiento(){
        // Arrange
        ArrayList<Palo> palos = new ArrayList<Palo>();
        palos.add(Palo.CORAZONES);
        palos.add(Palo.TREBOLES);

        var spider = new Spider(Variante.SPIDER, palos, new MovimientoACimientoSpiderDificil(), new MovimientoAPilaSpiderDificil(), true);

        spider.inicializarJuego();

        boolean resultado = true;

        try {
            spider.moverPilaACimiento(spider.obtenerPilaDelTableau(0), spider.obtenerCimiento(0));
        } catch (InvalidMovementException e) {
            resultado = false;
            assertEquals(ErrorAlMover.PILA_INCOMPLETA_NO_PUEDE_IR_A_CIMIENTO, e.obtenerMotivo());
        }
        assertFalse(resultado);

        resultado = true;
        for(int i = 0; i < 5; i++)
            spider.obtenerPilaDelTableau(0).darVueltaIndex(i);
        for(int i = 0; i < 5; i++)
            spider.obtenerPilaDelTableau(1).darVueltaIndex(i);
        try {
            spider.moverPilaAPila(spider.obtenerPilaDelTableau(1), spider.obtenerPilaDelTableau(0), 6);
        } catch (InvalidMovementException e) {
            resultado = false;
        }
        assertTrue(resultado);

        try {
            spider.moverPilaAPila(spider.obtenerPilaDelTableau(6), spider.obtenerPilaDelTableau(0), 1);
        } catch (InvalidMovementException e) {
            resultado = false;
        }
        assertTrue(resultado);

        try {
            spider.moverPilaACimiento(spider.obtenerPilaDelTableau(0), spider.obtenerCimiento(0));
        } catch (InvalidMovementException e) {
            resultado = false;
        }
        assertTrue(resultado);
    }
}