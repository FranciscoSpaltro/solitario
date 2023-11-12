package controlador;

import modelo.Cimiento;
import modelo.InvalidMovementException;
import modelo.PilaDelTableau;
import modelo.Spider;
import vista.VistaAlerta;
import vista.VistaPrincipal;

public class ControladorSpider extends ControladorSolitario {
    private Spider spider;
    public ControladorSpider(VistaPrincipal vistaPrincipal, Spider spider){
        super(vistaPrincipal, spider);
        this.spider = spider;
        controladorMazo = new ControladorMazoSpider(vistaPrincipal, spider, datosMovimiento, this);
    }

    @Override
    public void actualizar(){
        evaluarMovimiento();
        evaluarGanador();
        vistaPrincipal.actualizar();
        controladorVentana.iniciar();
        controladorMazo.iniciar(this);
        for(ControladorCimiento controladorCimiento : controladoresCimiento)
            controladorCimiento.actualizar(this);
        for(ControladorPila controladorPila : controladoresPila)
            controladorPila.actualizar(this);
    }

    public static void evaluarMovimiento() {
        if (!datosMovimiento.realizarMovimiento())
            return;
        if (datosMovimiento.esPila(datosMovimiento.obtenerListaOrigen())) {
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
                        System.out.println("Mover de Pila a Cimiento");
                        solitario.moverPilaACimiento((PilaDelTableau) datosMovimiento.obtenerListaOrigen(), (Cimiento) datosMovimiento.obtenerListaDestino());
                    } catch (InvalidMovementException e) {
                        VistaAlerta.mostrarAlerta(e);
                    }
                }
            }
        }

    }
}
