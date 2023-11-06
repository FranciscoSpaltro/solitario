import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import solitario.*;
import ui.ClicCartaEvento;
import ui.VistaCarta;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Klondike klondike = new Klondike(Variante.KLONDIKE, true);
        VistaCarta vistaCarta = new VistaCarta(klondike.obtenerMazo());
        klondike.inicializarJuego();

        var loader = new FXMLLoader(getClass().getResource("/ventana.fxml"));
        Parent ventana = loader.load();
        var scene = new Scene(ventana, 640, 480);

        StackPane mazo = (StackPane) ventana.lookup("#mazo");
        var basura = (StackPane) ventana.lookup("#basura");
        var cimiento1 = (StackPane) ventana.lookup("#cimiento1");
        var cimiento2 = (StackPane) ventana.lookup("#cimiento2");
        var cimiento3 = (StackPane) ventana.lookup("#cimiento3");
        var cimiento4 = (StackPane) ventana.lookup("#cimiento4");
        var pila1 = (StackPane) ventana.lookup("#pila1");
        var pila2 = (StackPane) ventana.lookup("#pila2");
        var pila3 = (StackPane) ventana.lookup("#pila3");
        var pila4 = (StackPane) ventana.lookup("#pila4");
        var pila5 = (StackPane) ventana.lookup("#pila5");
        var pila6 = (StackPane) ventana.lookup("#pila6");
        var pila7 = (StackPane) ventana.lookup("#pila7");

        ClicCartaEvento clicCartaEvento = new ClicCartaEvento();

        actualizarStackPane(klondike.obtenerMazo(), mazo, vistaCarta, Variante.KLONDIKE);
        actualizarStackPane(klondike.obtenerCimiento(0), cimiento1, vistaCarta, Variante.KLONDIKE);
        actualizarStackPane(klondike.obtenerCimiento(1), cimiento2, vistaCarta, Variante.KLONDIKE);
        actualizarStackPane(klondike.obtenerCimiento(2), cimiento3, vistaCarta, Variante.KLONDIKE);
        actualizarStackPane(klondike.obtenerCimiento(3), cimiento4, vistaCarta, Variante.KLONDIKE);

        actualizarBasura(klondike.obtenerBasura(), basura, vistaCarta, Variante.KLONDIKE);

        actualizarVistaPila(klondike.obtenerPilaDelTableau(0), pila1, vistaCarta, Variante.KLONDIKE);
        actualizarVistaPila(klondike.obtenerPilaDelTableau(1), pila2, vistaCarta, Variante.KLONDIKE);
        actualizarVistaPila(klondike.obtenerPilaDelTableau(2), pila3, vistaCarta, Variante.KLONDIKE);
        actualizarVistaPila(klondike.obtenerPilaDelTableau(3), pila4, vistaCarta, Variante.KLONDIKE);
        actualizarVistaPila(klondike.obtenerPilaDelTableau(4), pila5, vistaCarta, Variante.KLONDIKE);
        actualizarVistaPila(klondike.obtenerPilaDelTableau(5), pila6, vistaCarta, Variante.KLONDIKE);
        actualizarVistaPila(klondike.obtenerPilaDelTableau(6), pila7, vistaCarta, Variante.KLONDIKE);

        stage.setScene(scene);
        stage.setTitle("Solitario");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void actualizarStackPane(ListaDeCartas lista, StackPane stackpane, VistaCarta vistaCarta, Variante variante){
        if(lista.estaVacia()) {
            stackpane.getChildren().add(vistaCarta.obtenerImagen(null, variante));
        }
        for(Carta carta : lista) {
            stackpane.getChildren().add(vistaCarta.obtenerImagen(carta, variante));
        }
    }

    private void actualizarBasura(ListaDeCartas lista, StackPane stackpane, VistaCarta vistaCarta, Variante variante){
        if(lista.estaVacia()) {
            ImageView aux1 = vistaCarta.obtenerImagen(lista.obtenerCarta(lista.cantidadCartas() - 1), variante);
            ImageView aux2 = vistaCarta.obtenerImagen(lista.obtenerCarta(lista.cantidadCartas() - 2), variante);;
            ImageView aux3 = vistaCarta.obtenerImagen(lista.obtenerCarta(lista.cantidadCartas() - 3), variante);

            StackPane.setAlignment(aux1, javafx.geometry.Pos.TOP_LEFT);
            StackPane.setAlignment(aux2, javafx.geometry.Pos.CENTER);
            StackPane.setAlignment(aux3, javafx.geometry.Pos.TOP_RIGHT);

            stackpane.getChildren().addAll(aux1, aux2, aux3);
        }
    }

    private List<ImageView> generarEventosPila(Klondike klondike, PilaDelTableau pila, StackPane stackpane, VistaCarta vistaCarta, ClicCartaEvento clicCartaEvento){
        if(pila.estaVacia()) {
            ImageView aux = vistaCarta.obtenerImagen(null, klondike.obtenerVariante());
            aux.setOnMouseClicked(event -> {
                clicCartaEvento.hacerClic(klondike, pila, 0);
            });
            return List.of(aux);
        }
        int posicionY = 0;
        List<ImageView> lista = new ArrayList<>();
        for(Carta carta : pila) {
            ImageView aux = vistaCarta.obtenerImagen(carta, klondike.obtenerVariante());
            StackPane.setAlignment(aux, javafx.geometry.Pos.TOP_CENTER);
            aux.setTranslateY(posicionY);
            posicionY += 20;
            lista.add(aux);
        }
        return lista;
    }
    private void actualizarVistaPila(PilaDelTableau pila, StackPane stackpane, VistaCarta vistaCarta, Variante variante){
        if(pila.estaVacia()) {
            ImageView aux = vistaCarta.obtenerImagen(null, variante);
            stackpane.getChildren().add(aux);
        }
        int posicionY = 0;
        for(Carta carta : pila) {
            ImageView aux = vistaCarta.obtenerImagen(carta, variante);
            StackPane.setAlignment(aux, javafx.geometry.Pos.TOP_CENTER);
            aux.setTranslateY(posicionY);
            posicionY += 20;
            stackpane.getChildren().add(aux);
        }
    }
}
