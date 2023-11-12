package vista;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import modelo.*;

import java.util.ArrayList;

public class VistaSolitario {
    private static Stage stage;
    private Scene scene;
    private Pane menuPane;
    private Solitario solitario;

    private ArrayList<VistaPila> vistaPilas;
    private ArrayList<VistaCimiento> vistaCimientos;
    private VistaMazo vistaMazo;
    private VistaBasura vistaBasura;
    private Pane pane;


    public VistaSolitario(Stage stage , Solitario solitario){
        this.stage = stage;
        this.vistaPilas = new ArrayList<>();
        for (int i = 0; i < Constantes.obtenerCantidadPilasTableau(solitario.obtenerVariante()); i++) {
            this.vistaPilas.add(new VistaPila(solitario.obtenerPilaDelTableau(i), i, solitario.obtenerVariante()));
        }
        this.vistaCimientos = new ArrayList<>();
        for (int i = 0; i < Constantes.obtenerCantidadCimientos(solitario.obtenerVariante()); i++) {
            var vistaCimiento = new VistaCimiento(solitario.obtenerCimiento(i), i, solitario.obtenerVariante());
            this.vistaCimientos.add(vistaCimiento);
        }

        this.vistaMazo = new VistaMazo(solitario.obtenerMazo(), solitario.obtenerVariante());

        if (solitario.obtenerVariante() == Variante.KLONDIKE)
            this.vistaBasura = new VistaBasura(solitario.obtenerBasura(), solitario.obtenerVariante());

        this.solitario = solitario;
    }

    public void iniciar(){
        this.armarMenu();
        this.armarVentana();
        stage.setResizable(false);
        stage.show();
    }

    private void armarVentana() {
        stage.setTitle("Solitario");
        // Crear diseño principal
        pane = new Pane();
        pane.setPrefSize(Constantes.obtenerAncho(solitario.obtenerVariante()), Constantes.obtenerAlto(solitario.obtenerVariante()));
        pane.setStyle("-fx-background-color: green;");

        // Crear la escena
        scene = new Scene(pane, Constantes.obtenerAncho(solitario.obtenerVariante()), Constantes.obtenerAlto(solitario.obtenerVariante()));

        Label puntajeLabel = new Label("PUNTAJE: " + solitario.obtenerPuntos());
        puntajeLabel.setLayoutX(pane.getWidth() - 120);
        puntajeLabel.setLayoutY(5);
        puntajeLabel.setFont(new Font("Arial", 15));
        menuPane.setLayoutY(0);
        pane.getChildren().addAll(menuPane, puntajeLabel);


        pane.getChildren().add(vistaMazo);

        if(solitario.obtenerVariante() == Variante.KLONDIKE)
            pane.getChildren().add(vistaBasura);

        pane.getChildren().addAll(vistaPilas);

        pane.getChildren().addAll(vistaCimientos);

        // Configurar la escena en la etapa
        stage.setScene(scene);
    }

    public void actualizar(){
        pane.getChildren().clear();
        vistaMazo.actualizar();
        if (solitario.obtenerVariante() == Variante.KLONDIKE)
            vistaBasura.actualizar();

        for (VistaCimiento vistaCimiento : vistaCimientos)
            vistaCimiento.actualizar();
        for (VistaPila vistaPila : vistaPilas)
            vistaPila.actualizar();
        Label puntajeLabel = new Label("PUNTAJE: " + solitario.obtenerPuntos());
        puntajeLabel.setLayoutX(pane.getWidth() - 120);
        puntajeLabel.setLayoutY(5);
        puntajeLabel.setFont(new Font("Arial", 15));
        pane.getChildren().addAll(menuPane, puntajeLabel);
        pane.getChildren().add(vistaMazo);
        if (solitario.obtenerVariante() == Variante.KLONDIKE)
            pane.getChildren().add(vistaBasura);
        pane.getChildren().addAll(vistaPilas);
        pane.getChildren().addAll(vistaCimientos);
        stage.setScene(scene);
        stage.show();
    }

    private void armarMenu() {
        menuPane = new Pane();
        menuPane.setStyle("-fx-background-color: #DDDDDD;");
        menuPane.setPrefSize(Constantes.obtenerAncho(solitario.obtenerVariante()), 30);
        Button nuevoJuegoButton = new Button("Nuevo Juego");
        nuevoJuegoButton.setLayoutX(10);
        nuevoJuegoButton.setLayoutY(2.5);
        Button contactanosButton = new Button("Contactanos");
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
