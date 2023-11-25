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
        try {
            evaluarMovimiento();
        } catch (InvalidMovementException e) {
            VistaAlerta.mostrarAlerta(e);
        }
        evaluarGanador();
        vistaSolitario.obtenerVistaCarta().resetearControladores();
        vistaSolitario.actualizar();
        controladorMazo.iniciar(this);
        for(ControladorCimiento controladorCimiento : controladoresCimiento)
            controladorCimiento.actualizar(this);
        for(ControladorPila controladorPila : controladoresPila)
            controladorPila.actualizar(this);
    }

    public void evaluarMovimiento() throws InvalidMovementException {
        if (!datosMovimiento.realizarMovimiento())
            return;
        ListaDeCartas listaOrigen = datosMovimiento.obtenerListaOrigen();
        ListaDeCartas listaDestino = datosMovimiento.obtenerListaDestino();
        if (listaOrigen.esPilaDelTableau()) {
            if (listaDestino.esPilaDelTableau()) {
                // Lógica para "Mover de Pila a Pila"
                solitario.moverPilaAPila((PilaDelTableau) listaOrigen, (PilaDelTableau) listaDestino, listaOrigen.cantidadCartas() - datosMovimiento.obtenerIndiceOrigen() + 1);
            } else if (listaDestino.esCimiento()) {
                // Lógica para "Mover de Pila a Cimiento"
                if (datosMovimiento.obtenerIndiceOrigen() == listaOrigen.cantidadCartas()) {
                    solitario.moverPilaACimiento((PilaDelTableau) listaOrigen, (Cimiento) listaDestino);
                }
            }
        }
        datosMovimiento.resetear();
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
