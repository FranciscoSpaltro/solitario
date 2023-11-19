package controlador;

import modelo.*;
import vista.VistaCarta;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ControladorArchivos {
    private Solitario solitario;


    public boolean hayJuegoGuardado() {
        return Persistencia.existeArchivo(Constantes.RUTA_POR_DEFECTO);
    }

    public void configurarSolitario(Solitario solitario) {
        this.solitario = solitario;
    }


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

    public VistaCarta crearVistaCarta(Solitario solitario){
        Mazo mazoAuxiliar = solitario.obtenerMazo();
        for (int i = 0; i < Constantes.obtenerCantidadPilasTableau(solitario.obtenerVariante()); i++) {
            for (Carta carta : solitario.obtenerPilaDelTableau(i)) {
                mazoAuxiliar.agregarCarta(carta);
            }
        }
        for (int i = 0; i < Constantes.obtenerCantidadCimientos(solitario.obtenerVariante()); i++) {
            for (Carta carta : solitario.obtenerCimiento(i)) {
                mazoAuxiliar.agregarCarta(carta);
            }
        }
        if (Constantes.tieneBasura(solitario.obtenerVariante())) {
            for (Carta carta : solitario.obtenerBasura()) {
                mazoAuxiliar.agregarCarta(carta);
            }
        }
        return new VistaCarta(mazoAuxiliar, solitario.obtenerVariante());
    }

    public void guardarJuego() {
        try {
            Persistencia.escribirObjeto(new FileOutputStream(Constantes.RUTA_POR_DEFECTO), solitario);
            //Persistencia.escribirObjeto(new FileOutputStream(Constantes.RUTA_VISTA_CARTA), vistaCarta);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean hayJuegoIniciado() {
        return solitario != null;
    }
    public void borrarJuegoGuardado() {
        Persistencia.borrarArchivo(Constantes.RUTA_POR_DEFECTO);
    }

}
