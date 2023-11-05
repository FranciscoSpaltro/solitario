package solitario;

import org.junit.Test;
import solitario.*;

import java.io.*;

import static org.junit.Assert.*;

public class PersistenciaTest {
    // PARA KLONDIKE

    private void guardarJuegoKlondike() throws IOException {
        // Arrange
        Klondike klondike = new Klondike(Variante.KLONDIKE, true);
        klondike.inicializarJuego();
        // Directorio en el que deseas guardar el archivo serializado
        File directorio = new File("prueba");
        if(!directorio.exists())
            directorio.mkdir();

        // Act
        var salida = new FileOutputStream("prueba/pruebaKlondike.txt");
        Persistencia.escribirObjeto(salida, klondike);

}

    @Test
    public void abrirJuegoKlondike() throws IOException, ClassNotFoundException {
        // Arrange
        Klondike klondike = null;
        guardarJuegoKlondike();
        var entrada = new FileInputStream("prueba/pruebaKlondike.txt");
        klondike = (Klondike) Persistencia.importarObjeto(entrada);

        // Assert
        assertNotNull(klondike);
        assertEquals(Variante.KLONDIKE, klondike.obtenerVariante());
        assertEquals(0, klondike.obtenerPuntos());
        assertEquals(0, klondike.obtenerBasura().cantidadCartas());
        assertEquals(24, klondike.obtenerMazo().cantidadCartas());
        assertEquals("AS de CORAZONES", klondike.obtenerMazo().obtenerCarta(0).verValor().toString() + " de " + klondike.obtenerMazo().obtenerCarta(0).verPalo().toString());
        assertEquals(0, klondike.obtenerCimiento(0).cantidadCartas());
        assertEquals(0, klondike.obtenerCimiento(1).cantidadCartas());
        assertEquals(0, klondike.obtenerCimiento(2).cantidadCartas());
        assertEquals(0, klondike.obtenerCimiento(3).cantidadCartas());

        File directorio = new File("prueba");
        directorio.delete();
    }

    // PARA SPIDER
    private void guardarJuegoSpider() throws IOException {
        // Arrange
        var spider = new Spider(Variante.SPIDER, Palo.PICAS, new MovimientoACimientoSpiderFacil(), new MovimientoAPilaSpiderFacil(), true);
        spider.inicializarJuego();
        // Directorio en el que deseas guardar el archivo serializado
        File directorio = new File("prueba");
        if(!directorio.exists())
            directorio.mkdir();

        // Act
        FileOutputStream salida;
        try {
            salida = new FileOutputStream("prueba/pruebaSpider.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        }

        try {
            Persistencia.escribirObjeto(salida, spider);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    @Test
    public void abrirJuegoSpider() throws IOException, ClassNotFoundException {
        // Arrange
        Spider spider = null;
        try {
            guardarJuegoSpider();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileInputStream entrada;

        try {
            entrada = new FileInputStream("prueba/pruebaSpider.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        }

        try {
            spider = (Spider) Persistencia.importarObjeto(entrada);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        // Assert
        assertNotNull(spider);
        assertEquals(Variante.SPIDER, spider.obtenerVariante());
        assertEquals(0, spider.obtenerPuntos());
        assertEquals(50, spider.obtenerMazo().cantidadCartas());
        assertEquals("AS de PICAS", spider.obtenerMazo().obtenerCarta(0).verValor().toString() + " de " + spider.obtenerMazo().obtenerCarta(0).verPalo().toString());
        assertEquals(0, spider.obtenerCimiento(0).cantidadCartas());
        assertEquals(0, spider.obtenerCimiento(5).cantidadCartas());
        assertEquals(0, spider.obtenerCimiento(7).cantidadCartas());

        File directorio = new File("prueba");
        directorio.delete();
    }
}