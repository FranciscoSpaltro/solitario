package vista;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import modelo.*;

import java.util.ArrayList;

public class VistaPrincipal {
    private static Stage stage;
    private Scene scene;
    private MenuBar menuBar;
    //private Solitario solitario;
    private ArrayList<VistaPila> vistaPilas;
    private ArrayList<VistaCimiento> vistaCimientos;
    private VistaMazo vistaMazo;
    private VistaBasura vistaBasura;
    private Pane pane;


    public VistaPrincipal(Stage stage , Klondike klondike){
        this.stage = stage;
        //this.solitario = solitario;
        this.vistaPilas = new ArrayList<>();
        for (int i = 0; i < Constantes.CANTIDAD_PILAS_TABLEAU_KLONDIKE; i++) {
            this.vistaPilas.add(new VistaPila(klondike.obtenerPilaDelTableau(i), i));
        }
        this.vistaCimientos = new ArrayList<>();
        for (int i = 0; i < Constantes.CANTIDAD_CIMIENTOS_KLONDIKE; i++) {
            var vistaCimiento = new VistaCimiento(klondike.obtenerCimiento(i), i);
            this.vistaCimientos.add(vistaCimiento);
        }

        this.vistaPilas = new ArrayList<>();
        for (int i = 0; i < Constantes.CANTIDAD_PILAS_TABLEAU_KLONDIKE; i++) {
            var vistaPila = new VistaPila(klondike.obtenerPilaDelTableau(i), i);
            this.vistaPilas.add(vistaPila);
        }

        this.vistaMazo = new VistaMazo(klondike.obtenerMazo());

        this.vistaBasura = new VistaBasura(klondike.obtenerBasura());
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

        menuBar.setPrefWidth(pane.getWidth());
        menuBar.setLayoutY(0);
        pane.getChildren().add(menuBar);

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
        pane.getChildren().add(menuBar);
        pane.getChildren().add(vistaMazo);
        pane.getChildren().add(vistaBasura);
        pane.getChildren().addAll(vistaPilas);
        pane.getChildren().addAll(vistaCimientos);
        stage.setScene(scene);
        stage.show();
    }

    private void armarMenu() {
        this.menuBar = new MenuBar();

        // Menú Juego
        Menu juegoMenu = new Menu("Juego");
        MenuItem nuevoJuegoItem = new MenuItem("Nuevo Juego");
        Menu juegosSubMenu = new Menu("Seleccionar Juego");
        MenuItem klondikeItem = new MenuItem("Klondike");
        MenuItem spiderItem = new MenuItem("Spider");
        juegosSubMenu.getItems().addAll(klondikeItem, spiderItem);

        MenuItem guardarJuegoItem = new MenuItem("Guardar Juego");
        juegoMenu.getItems().addAll(nuevoJuegoItem, juegosSubMenu, guardarJuegoItem);

        // Menú Ayuda
        Menu ayudaMenu = new Menu("Ayuda");
        MenuItem contactanosItem = new MenuItem("Contáctanos");
        MenuItem creditosItem = new MenuItem("Créditos");
        ayudaMenu.getItems().addAll(contactanosItem, creditosItem);

        // Agregar menús a la barra de menú
        this.menuBar.getMenus().addAll(juegoMenu, ayudaMenu);
    }

    public MenuItem obtenerNuevoJuegoItem() {
        return this.menuBar.getMenus().get(0).getItems().get(0);
    }

    public void configurarNuevaStage(Scene nuevaScene){
        scene = nuevaScene;
        stage.setScene(nuevaScene);
        stage.show();
    }

    public MenuItem obtenerKlondikeItem() {
        // Obtener el primer menú (Juego)
        Menu juegoMenu = (Menu) menuBar.getMenus().get(0);

        // Obtener el segundo elemento del menú (Seleccionar Juego)
        Menu juegosSubMenu = (Menu) juegoMenu.getItems().get(1);

        // Obtener el primer elemento del submenú (Klondike)
        MenuItem klondikeItem = juegosSubMenu.getItems().get(0);

        return klondikeItem;
    }

    public MenuItem obtenerSpiderItem() {
        // Obtener el primer menú (Juego)
        Menu juegoMenu = (Menu) menuBar.getMenus().get(0);

        // Obtener el segundo elemento del menú (Seleccionar Juego)
        Menu juegosSubMenu = (Menu) juegoMenu.getItems().get(1);

        // Obtener el primer elemento del submenú (Klondike)
        MenuItem spiderItem = juegosSubMenu.getItems().get(1);

        return spiderItem;
    }

    public MenuItem obtenerGuardarJuegoItem() {
        return this.menuBar.getMenus().get(0).getItems().get(2);
    }

    public MenuItem obtenerContactanosItem() {
        return this.menuBar.getMenus().get(1).getItems().get(0);
    }

    public MenuItem obtenerCreditosItem() {
        return this.menuBar.getMenus().get(1).getItems().get(1);
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
