package controlador;
import modelo.Klondike;
import modelo.Solitario;
import vista.VistaPrincipal;

public abstract class ControladorMazo {
    protected VistaPrincipal vistaPrincipal;
    protected Solitario solitario;
    protected DatosMovimiento datosMovimiento;

    public ControladorMazo(VistaPrincipal vistaPrincipal, Solitario solitario, DatosMovimiento datosMovimiento, ControladorSolitario controladorSolitario) {
        this.vistaPrincipal = vistaPrincipal;
        this.solitario = solitario;
        this.datosMovimiento = datosMovimiento;
        iniciar(controladorSolitario);
    }

    public abstract void iniciar(ControladorSolitario controladorSolitario);
}
