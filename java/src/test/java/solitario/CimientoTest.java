package solitario;

import org.junit.Test;
import solitario.InvalidMovementException;
import solitario.Klondike;
import solitario.PilaDelTableau;
import solitario.Variante;

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

        boolean movioAs = true;
        try {
            klondike.moverPilaACimiento(pilaAMover, klondike.obtenerCimiento(0));
        } catch (InvalidMovementException e) {
            movioAs = false;
        }

        boolean movioDos = true;
        try {
            klondike.moverPilaACimiento(pilaAMover, klondike.obtenerCimiento(0));
        } catch (InvalidMovementException e) {
            movioDos = false;
        }

        boolean movioTres = true;
        try {
            klondike.moverPilaACimiento(pilaAMover, klondike.obtenerCimiento(0));
        } catch (InvalidMovementException e) {
            movioTres = false;
        }

        pilaAMover = klondike.obtenerPilaDelTableau(3);
        boolean movioCuatro = true;
        try {
            klondike.moverPilaACimiento(pilaAMover, klondike.obtenerCimiento(0));
        } catch (InvalidMovementException e) {
            movioCuatro = false;
        }

        boolean movioCinco = true;
        try {
            klondike.moverPilaACimiento(pilaAMover, klondike.obtenerCimiento(0));
        } catch (InvalidMovementException e) {
            movioCinco = false;
        }

        boolean movioSeis = true;
        try {
            klondike.moverPilaACimiento(pilaAMover, klondike.obtenerCimiento(0));
        } catch (InvalidMovementException e) {
            movioSeis = false;
        }

        boolean movioSiete = true;
        try {
            klondike.moverPilaACimiento(pilaAMover, klondike.obtenerCimiento(0));
        } catch (InvalidMovementException e) {
            movioSiete = false;
        }

        boolean estaCompleto = klondike.obtenerCimiento(0).estaCompleto();

        pilaAMover = klondike.obtenerPilaDelTableau(2);
        boolean movioOcho = true;
        try {
            klondike.moverPilaACimiento(pilaAMover, klondike.obtenerCimiento(0));
        } catch (InvalidMovementException e) {
            movioOcho = false;
        }

        boolean movioNueve = true;
        try{
            klondike.moverPilaACimiento(pilaAMover, klondike.obtenerCimiento(0));
        } catch (InvalidMovementException e){
            movioNueve = false;
        }

        boolean movioDiez = true;
        try{
            klondike.moverPilaACimiento(pilaAMover, klondike.obtenerCimiento(0));
        } catch (InvalidMovementException e){
            movioDiez = false;
        }

        pilaAMover = klondike.obtenerPilaDelTableau(1);
        boolean movioOnce = true;
        try{
            klondike.moverPilaACimiento(pilaAMover, klondike.obtenerCimiento(0));
        } catch (InvalidMovementException e){
            movioOnce = false;
        }

        boolean movioDoce = true;
        try{
            klondike.moverPilaACimiento(pilaAMover, klondike.obtenerCimiento(0));
        } catch (InvalidMovementException e){
            movioDoce = false;
        }

        pilaAMover = klondike.obtenerPilaDelTableau(0);
        boolean movioTrece = true;
        try{
            klondike.moverPilaACimiento(pilaAMover, klondike.obtenerCimiento(0));
        } catch (InvalidMovementException e){
            movioTrece = false;
        }

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
