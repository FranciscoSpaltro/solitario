package controlador;

import modelo.Constantes;
import modelo.Klondike;
import vista.VistaPrincipal;

import java.util.ArrayList;

public class ControladorPrincipal {
    private static VistaPrincipal vistaPrincipal;
    private static ControladorVentana controladorVentana;
    private static ControladorMazo controladorMazo;
    private static ControladorBasura controladorBasura;
    private static ArrayList<ControladorPila> controladoresPila = new ArrayList<>();
    private static ArrayList<ControladorCimiento> controladoresCimiento = new ArrayList<>();

    public ControladorPrincipal(VistaPrincipal vistaPrincipal, Klondike klondike){
        this.vistaPrincipal = vistaPrincipal;
        controladorVentana = new ControladorVentana(vistaPrincipal);
        controladorMazo = new ControladorMazo(vistaPrincipal, klondike);
        controladorBasura = new ControladorBasura(vistaPrincipal, klondike.obtenerBasura());
        for(int i = 0; i < Constantes.CANTIDAD_CIMIENTOS_KLONDIKE; i++)
            controladoresCimiento.add(new ControladorCimiento(vistaPrincipal, klondike.obtenerCimiento(i), i));

        for(int i = 0; i < Constantes.CANTIDAD_PILAS_TABLEAU_KLONDIKE; i++)
            controladoresPila.add(new ControladorPila(vistaPrincipal, klondike.obtenerPilaDelTableau(i), i));
    }

    public static void actualizar(){
        vistaPrincipal.actualizar();
        controladorVentana.iniciar();
        controladorMazo.iniciar();
        controladorBasura.actualizar();
        for(ControladorCimiento controladorCimiento : controladoresCimiento)
            controladorCimiento.actualizar();
        for(ControladorPila controladorPila : controladoresPila)
            controladorPila.actualizar();
    }
}
