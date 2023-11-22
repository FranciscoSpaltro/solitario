package controlador;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import modelo.*;
import vista.VistaGanador;
import vista.VistaSolitario;

import java.util.ArrayList;

public abstract class ControladorSolitario {
    protected static VistaSolitario vistaSolitario;
    protected static ControladorMazo controladorMazo;
    protected static ArrayList<ControladorPila> controladoresPila = new ArrayList<>();
    protected static ArrayList<ControladorCimiento> controladoresCimiento = new ArrayList<>();
    protected static Solitario solitario;
    protected static DatosMovimiento datosMovimiento;

    public ControladorSolitario(VistaSolitario vistaSolitario, Solitario solitario){
        datosMovimiento = new DatosMovimiento();
        this.vistaSolitario = vistaSolitario;
        this.solitario = solitario;

        for(int i = 0; i < Constantes.obtenerCantidadCimientos(solitario.obtenerVariante()); i++)
            controladoresCimiento.add(new ControladorCimiento(vistaSolitario, solitario.obtenerCimiento(i), i, datosMovimiento, this));

        for(int i = 0; i < Constantes.obtenerCantidadPilasTableau(solitario.obtenerVariante()); i++)
            controladoresPila.add(new ControladorPila(vistaSolitario, solitario.obtenerPilaDelTableau(i), datosMovimiento, this));
    }

    public abstract void actualizar();
    protected static void evaluarGanador() {
        if (solitario.jugadorGano()) {
            var vistaGanador = new VistaGanador(solitario.obtenerPuntos());
            // Configurar la escena en el stage
            vistaSolitario.configurarNuevaStage(vistaGanador.obtenerScene());
        }
    }
}
