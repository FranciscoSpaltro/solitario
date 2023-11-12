package controlador;
import modelo.Spider;
import vista.VistaPrincipal;

public class ControladorMazoSpider extends ControladorMazo {
    private Spider spider;
    public ControladorMazoSpider(VistaPrincipal vistaPrincipal, Spider spider, DatosMovimiento datosMovimiento, ControladorSolitario controladorSolitario) {
        super(vistaPrincipal, spider, datosMovimiento, controladorSolitario);
        this.spider = spider;
    }

    public void iniciar(ControladorSolitario controladorSolitario) {
        vistaPrincipal.obtenerVistaMazo().setOnMouseClicked(event -> {
            // Lógica para "Apretar Mazo"
            if(spider.obtenerMazo().estaVacia()){
                return;
            } else {
                spider.sacarCartasMazo();
            }
            controladorSolitario.actualizar();
        });
    }
}