package controlador;
import modelo.Solitario;
import vista.VistaSolitario;

public abstract class ControladorMazo {
    protected VistaSolitario vistaSolitario;
    protected Solitario solitario;
    protected DatosMovimiento datosMovimiento;

    public ControladorMazo(VistaSolitario vistaSolitario, Solitario solitario, DatosMovimiento datosMovimiento, ControladorSolitario controladorSolitario) {
        this.vistaSolitario = vistaSolitario;
        this.solitario = solitario;
        this.datosMovimiento = datosMovimiento;
        iniciar(controladorSolitario);
    }

    public abstract void iniciar(ControladorSolitario controladorSolitario);
}
