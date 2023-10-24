import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class PersistenciaTest {
    @Test
    public void guardarJuego(){
        // Arrange
        Klondike klondike = new Klondike(Variante.KLONDIKE, true);
        klondike.inicializarJuego();
        // Directorio en el que deseas guardar el archivo serializado
        File directorio = new File("prueba");
        directorio.mkdir();

        // Act
        try {
            Persistencia.escribirObjeto("prueba/prueba.txt", klondike);
        } catch (IOException e) {
            System.out.println("ups");
        }
    }

    @Test
    public void abrirJuego(){
        // Arrange
        try {
            Klondike klondike = (Klondike) Persistencia.importarObjeto("prueba/prueba.txt");
        } catch (IOException e){
            System.out.println("ups1");
        } catch (ClassNotFoundException e) {
            System.out.println("ups2");
        }

        System.out.print("final");
    }
}