package vista;

import controlador.ControladorArchivos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import modelo.*;

import java.io.Serializable;
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
    private ControladorArchivos controladorArchivos;
    VistaCarta vistaCarta;
    public VistaSolitario(Stage stage , Solitario solitario, ControladorArchivos controladorArchivos, VistaCarta vistaCarta) {
        this.stage = stage;
        this.vistaPilas = new ArrayList<>();
        for (int i = 0; i < Constantes.obtenerCantidadPilasTableau(solitario.obtenerVariante()); i++) {
            this.vistaPilas.add(new VistaPila(solitario.obtenerPilaDelTableau(i), i, vistaCarta));
        }
        this.vistaCimientos = new ArrayList<>();
        for (int i = 0; i < Constantes.obtenerCantidadCimientos(solitario.obtenerVariante()); i++) {
            var vistaCimiento = new VistaCimiento(solitario.obtenerCimiento(i), i, vistaCarta);
            this.vistaCimientos.add(vistaCimiento);
        }

        this.vistaMazo = new VistaMazo(solitario.obtenerMazo(), vistaCarta);

        if (solitario.tieneBasura())
            this.vistaBasura = new VistaBasura(solitario.obtenerBasura(), solitario.obtenerVariante(), vistaCarta);

        this.solitario = solitario;
        this.controladorArchivos = controladorArchivos;
        this.controladorArchivos.configurarSolitario(solitario);
        this.vistaCarta = vistaCarta;
    }

    public VistaCarta obtenerVistaCarta() {
        return vistaCarta;
    }
    public Solitario obtenerSolitario() {
        return solitario;
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


        menuPane.setLayoutY(0);
        configurarEscena();
        stage.show();
    }

    public Label generarLabelPuntaje(int puntos, Pane pane) {
        Label puntajeLabel = new Label("PUNTAJE: " + puntos);
        puntajeLabel.setLayoutX(pane.getWidth() - 120);
        puntajeLabel.setLayoutY(5);
        puntajeLabel.setFont(new Font("Arial", 15));
        return puntajeLabel;
    }

    public void actualizar(){
        pane.getChildren().clear();
        vistaMazo.actualizar();
        if (solitario.tieneBasura())
            vistaBasura.actualizar();
        for (VistaCimiento vistaCimiento : vistaCimientos)
            vistaCimiento.actualizar();
        for (VistaPila vistaPila : vistaPilas)
            vistaPila.actualizar();

        configurarEscena();
    }

    public void configurarEscena() {
        Label puntajeLabel = generarLabelPuntaje(solitario.obtenerPuntos(), pane);
        pane.getChildren().addAll(menuPane, puntajeLabel);

        pane.getChildren().add(vistaMazo);
        if (solitario.tieneBasura())
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

        nuevoJuegoButton.setOnAction(event -> {
            // Lógica para "Nuevo Juego"
            try {
                VistaInicio vistaInicio = new VistaInicio(stage, this, controladorArchivos);
                vistaInicio.mostrar();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        contactanosButton.setOnAction(event -> {
            // Lógica para "Contáctanos"
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correos de contacto");
            alert.setHeaderText(null);
            alert.setContentText("Leandro Peña [lpena@fi.uba.ar]\nFrancisco Spaltro [fspaltro@fi.uba.ar]");
            alert.showAndWait();
        });

        menuPane.getChildren().addAll(nuevoJuegoButton, contactanosButton);
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

}
