package vista;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import modelo.*;

import java.util.ArrayList;

public class VistaPrincipal {
    private static Stage stage;
    private Scene scene;
    private Pane menuPane;
    private Klondike klondnike;
    private ArrayList<VistaPila> vistaPilas;
    private ArrayList<VistaCimiento> vistaCimientos;
    private VistaMazo vistaMazo;
    private VistaBasura vistaBasura;
    private Pane pane;


    public VistaPrincipal(Stage stage , Klondike klondike){
        this.stage = stage;
        this.vistaPilas = new ArrayList<>();
        for (int i = 0; i < Constantes.CANTIDAD_PILAS_TABLEAU_KLONDIKE; i++) {
            this.vistaPilas.add(new VistaPila(klondike.obtenerPilaDelTableau(i), i));
        }
        this.vistaCimientos = new ArrayList<>();
        for (int i = 0; i < Constantes.CANTIDAD_CIMIENTOS_KLONDIKE; i++) {
            var vistaCimiento = new VistaCimiento(klondike.obtenerCimiento(i), i);
            this.vistaCimientos.add(vistaCimiento);
        }

        this.vistaMazo = new VistaMazo(klondike.obtenerMazo());

        this.vistaBasura = new VistaBasura(klondike.obtenerBasura());
        this.klondnike = klondike;
    }

    public void iniciar(){
        this.armarMenu();
        this.armarVentana();
        stage.setResizable(false);
    }

    public void mostrar(){
        this.armarVentana();
        stage.show();
    }

    private void armarVentana() {
        stage.setTitle("Solitario");
        // Crear diseño principal
        pane = new Pane();
        pane.setPrefSize(640, 480);
        pane.setStyle("-fx-background-color: green;");

        // Crear la escena
        scene = new Scene(pane, 640, 480);

        Label puntajeLabel = new Label("PUNTAJE: " + klondnike.obtenerPuntos());
        puntajeLabel.setLayoutX(pane.getWidth() - 120);
        puntajeLabel.setLayoutY(5);
        puntajeLabel.setFont(new Font("Arial", 15));
        menuPane.setLayoutY(0);
        pane.getChildren().addAll(menuPane, puntajeLabel);


        pane.getChildren().add(vistaMazo);

        pane.getChildren().add(vistaBasura);
        pane.getChildren().addAll(vistaPilas);

        pane.getChildren().addAll(vistaCimientos);

        // Configurar la escena en la etapa
        stage.setScene(scene);
    }

    public void actualizar(){
        pane.getChildren().clear();
        vistaMazo.actualizar();
        vistaBasura.actualizar();
        for (VistaCimiento vistaCimiento : vistaCimientos)
            vistaCimiento.actualizar();
        for (VistaPila vistaPila : vistaPilas)
            vistaPila.actualizar();
        Label puntajeLabel = new Label("PUNTAJE: " + klondnike.obtenerPuntos());
        puntajeLabel.setLayoutX(pane.getWidth() - 120);
        puntajeLabel.setLayoutY(5);
        puntajeLabel.setFont(new Font("Arial", 15));
        pane.getChildren().addAll(menuPane, puntajeLabel);
        pane.getChildren().add(vistaMazo);
        pane.getChildren().add(vistaBasura);
        pane.getChildren().addAll(vistaPilas);
        pane.getChildren().addAll(vistaCimientos);
        stage.setScene(scene);
        stage.show();
    }

    private void armarMenu() {
        menuPane = new Pane();
        menuPane.setStyle("-fx-background-color: #DDDDDD;");
        menuPane.setPrefSize(640, 30);
        Button nuevoJuegoButton = new Button("Nuevo Juego");
        nuevoJuegoButton.setLayoutX(10);
        nuevoJuegoButton.setLayoutY(2.5);
        Button contactanosButton = new Button("Contáctanos");
        contactanosButton.setLayoutX(100);
        contactanosButton.setLayoutY(2.5);
        menuPane.getChildren().addAll(nuevoJuegoButton, contactanosButton);
    }


    public Button obtenerNuevoJuegoItem() {
        return (Button) menuPane.getChildren().get(0);
    }

    public Button obtenerContactanosItem() {
        return (Button) menuPane.getChildren().get(1);
    }

    public void configurarNuevaStage(Scene nuevaScene){
        scene = nuevaScene;
        stage.setScene(nuevaScene);
        stage.show();
    }

    public VistaMazo obtenerVistaMazo() {
        return this.vistaMazo;
    }

    public VistaBasura obtenerVistaBasura() {
        return this.vistaBasura;
    }
   public VistaCimiento obtenerVistaCimiento(int i) {
        return this.vistaCimientos.get(i);
    }
    public VistaPila obtenerVistaPila(int i) {
        return this.vistaPilas.get(i);
    }

    public Stage obtenerStage() {
        return stage;
    }
}
