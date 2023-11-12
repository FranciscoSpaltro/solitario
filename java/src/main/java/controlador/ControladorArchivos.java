package controlador;

import modelo.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ControladorArchivos {
    static Solitario solitario;

    public Solitario abrirJuegoGuardado() {
        try {
            solitario = (Solitario) Persistencia.importarObjeto(new FileInputStream(Constantes.RUTA_POR_DEFECTO));
        } catch (FileNotFoundException | ClassNotFoundException e) {
            solitario = new Klondike(new MovimientoAPilaKlondike(),false);
            solitario.inicializarJuego();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return solitario;
    }

    public static void guardarJuego() {
        try {
            Persistencia.escribirObjeto(new FileOutputStream(Constantes.RUTA_POR_DEFECTO), solitario);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
