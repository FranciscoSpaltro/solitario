package controlador;

import modelo.*;
import vista.VistaAlerta;
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

    public void actualizar(){
        evaluarMovimiento();
        evaluarGanador();
        vistaSolitario.obtenerVistaCarta().resetearControladores();
        vistaSolitario.actualizar();
        controladorMazo.iniciar(this);
        for(ControladorCimiento controladorCimiento : controladoresCimiento)
            controladorCimiento.actualizar(this);
        for(ControladorPila controladorPila : controladoresPila)
            controladorPila.actualizar(this);
    }

    public void evaluarMovimiento() {
        if (!datosMovimiento.realizarMovimiento())
            return;
        else if (datosMovimiento.esPila(datosMovimiento.obtenerListaOrigen())) {
            if (datosMovimiento.esPila(datosMovimiento.obtenerListaDestino())) {
                // Lógica para "Mover de Pila a Pila"
                try {
                    solitario.moverPilaAPila((PilaDelTableau) datosMovimiento.obtenerListaOrigen(), (PilaDelTableau) datosMovimiento.obtenerListaDestino(), datosMovimiento.obtenerListaOrigen().cantidadCartas() - datosMovimiento.obtenerIndiceOrigen() + 1);
                    datosMovimiento.resetear();
                } catch (InvalidMovementException e) {
                    VistaAlerta.mostrarAlerta(e);
                }
            } else if (datosMovimiento.esCimiento(datosMovimiento.obtenerListaDestino())) {
                // Lógica para "Mover de Pila a Cimiento"
                if (datosMovimiento.obtenerIndiceOrigen() == datosMovimiento.obtenerListaOrigen().cantidadCartas()) {
                    try {
                        solitario.moverPilaACimiento((PilaDelTableau) datosMovimiento.obtenerListaOrigen(), (Cimiento) datosMovimiento.obtenerListaDestino());
                    } catch (InvalidMovementException e) {
                        VistaAlerta.mostrarAlerta(e);
                    }
                }
            }
        } else {
            datosMovimiento.resetear();
        }
        vistaSolitario.obtenerVistaCarta().eliminarEfectos();
    }

    protected static void evaluarGanador() {
        if (solitario.jugadorGano()) {
            var vistaGanador = new VistaGanador(solitario.obtenerPuntos());
            // Configurar la escena en el stage
            vistaSolitario.configurarNuevaStage(vistaGanador.obtenerScene());
        }
    }
}
