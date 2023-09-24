import org.junit.Test;

import java.util.ArrayList;

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
        assertEquals(Valor.REY, carta1.verValor());
        assertEquals(Palo.PICAS, carta1.verPalo());
        assertFalse(carta1.estaBocaArriba());
        assertEquals(52, mazo.cantidadCartas());
    }


    @Test
    public void testMazoMezclado(){
        // Arrange
        int N = 10;
        Mazo mazo = new Mazo();
        Mazo mazo2 = new Mazo();

        // Act
        mazo.mezclar();
        mazo2.mezclar();

        ArrayList<Carta> mano =  mazo.extraerUltimasN(N);
        ArrayList<Carta> mano2 =  mazo2.extraerUltimasN(N);

        // Assert
        int counter = 0;
        for (int i = 0; i < N; i++) {
            if (mano.get(i).verValor() == mano2.get(i).verValor() && mano.get(i).verPalo() == mano2.get(i).verPalo())
                counter++;
        }
        assertNotEquals(N, counter);
    }

    @Test
    public void testExtraerCartas() {
        // Arrange
        Mazo mazo = new Mazo();

        // Act
        ArrayList<Carta> extracto  = mazo.extraerUltimasN(10);

        // Assert
        assertEquals(10, extracto.size());
    }


}