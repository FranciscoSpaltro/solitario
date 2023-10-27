import org.junit.Test;

import java.io.File;
import java.io.IOException;

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
        Persistencia.escribirObjeto("prueba/pruebaKlondike.txt", klondike);

}

    @Test
    public void abrirJuegoKlondike() throws IOException, ClassNotFoundException {
        // Arrange
        Klondike klondike = null;
        guardarJuegoKlondike();
        klondike = (Klondike) Persistencia.importarObjeto("prueba/pruebaKlondike.txt");

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
    }

    // PARA SPIDER
    private void guardarJuegoSpider() throws IOException {
        // Arrange
        SpiderFacil spider = new SpiderFacil(Variante.SPIDER, Palo.PICAS, true);
        spider.inicializarJuego();
        // Directorio en el que deseas guardar el archivo serializado
        File directorio = new File("prueba");
        if(!directorio.exists())
            directorio.mkdir();

        // Act
        Persistencia.escribirObjeto("prueba/pruebaSpider.txt", spider);

    }

    @Test
    public void abrirJuegoSpider() throws IOException, ClassNotFoundException {
        // Arrange
        SpiderFacil spider = null;
        guardarJuegoSpider();
        spider = (SpiderFacil) Persistencia.importarObjeto("prueba/pruebaSpider.txt");

        // Assert
        assertNotNull(spider);
        assertEquals(Variante.SPIDER, spider.obtenerVariante());
        assertEquals(0, spider.obtenerPuntos());
        assertEquals(50, spider.obtenerMazo().cantidadCartas());
        assertEquals("AS de PICAS", spider.obtenerMazo().obtenerCarta(0).verValor().toString() + " de " + spider.obtenerMazo().obtenerCarta(0).verPalo().toString());
        assertEquals(0, spider.obtenerCimiento(0).cantidadCartas());
        assertEquals(0, spider.obtenerCimiento(5).cantidadCartas());
        assertEquals(0, spider.obtenerCimiento(7).cantidadCartas());
    }
}