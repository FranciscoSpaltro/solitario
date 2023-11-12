package controlador;
import modelo.Spider;
import vista.VistaSolitario;

public class ControladorMazoSpider extends ControladorMazo {
    private Spider spider;
    public ControladorMazoSpider(VistaSolitario vistaSolitario, Spider spider, DatosMovimiento datosMovimiento, ControladorSolitario controladorSolitario) {
        super(vistaSolitario, spider, datosMovimiento, controladorSolitario);
        this.spider = spider;
    }

    public void iniciar(ControladorSolitario controladorSolitario) {
        vistaSolitario.obtenerVistaMazo().setOnMouseClicked(event -> {
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