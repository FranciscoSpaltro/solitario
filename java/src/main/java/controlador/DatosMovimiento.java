package controlador;

import modelo.*;

public class DatosMovimiento {
    private ListaDeCartas origen = null;
    private ListaDeCartas destino = null;
    private int cartasAMover = 0;
    boolean yaHuboClic;
    boolean realizarMovimiento;

    public DatosMovimiento(){
        yaHuboClic = false;
        realizarMovimiento = false;
    }

    public void clic(ListaDeCartas listaDeCartas, int n){
        if(!yaHuboClic){
            origen = listaDeCartas;
            cartasAMover = n;
            realizarMovimiento = false;
            yaHuboClic = true;
        } else {
            if (origen == listaDeCartas) {
                resetear();
                return;
            }
            destino = listaDeCartas;
            yaHuboClic = false;
            realizarMovimiento = true;
        }
    }

    public boolean realizarMovimiento(){
        return realizarMovimiento;
    }
    public void resetear() {
        origen = null;
        destino = null;
        yaHuboClic = false;
        realizarMovimiento = false;
    }

    public ListaDeCartas obtenerListaOrigen(){
        return origen;
    }

    public ListaDeCartas obtenerListaDestino(){
        return destino;
    }

    public int obtenerIndiceOrigen(){
        return cartasAMover;
    }

    public boolean esPila(ListaDeCartas lista) {
        return lista instanceof PilaDelTableau;
    }

    public boolean esCimiento(ListaDeCartas lista) {
        return lista instanceof Cimiento;
    }

    public boolean esBasura(ListaDeCartas lista) {
        return lista instanceof Basura;
    }

}
