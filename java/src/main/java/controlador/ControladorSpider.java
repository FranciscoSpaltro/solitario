package controlador;

import modelo.Cimiento;
import modelo.InvalidMovementException;
import modelo.PilaDelTableau;
import modelo.Spider;
import vista.VistaAlerta;
import vista.VistaSolitario;

public class ControladorSpider extends ControladorSolitario {
    public ControladorSpider(VistaSolitario vistaSolitario, Spider spider){
        super(vistaSolitario, spider);
        controladorMazo = new ControladorMazoSpider(vistaSolitario, spider, datosMovimiento, this);
    }
}
