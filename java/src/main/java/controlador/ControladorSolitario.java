package controlador;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import modelo.*;
import vista.VistaSolitario;

import java.util.ArrayList;

public abstract class ControladorSolitario {
    protected static VistaSolitario vistaSolitario;
    protected static ControladorMazo controladorMazo;
    protected static ArrayList<ControladorPila> controladoresPila = new ArrayList<>();
    protected static ArrayList<ControladorCimiento> controladoresCimiento = new ArrayList<>();
    protected static Solitario solitario;
    protected static DatosMovimiento datosMovimiento;

    public ControladorSolitario(VistaSolitario vistaSolitario, Solitario solitario){
        datosMovimiento = new DatosMovimiento();
        this.vistaSolitario = vistaSolitario;
        this.solitario = solitario;

        for(int i = 0; i < Constantes.obtenerCantidadCimientos(solitario.obtenerVariante()); i++)
            controladoresCimiento.add(new ControladorCimiento(vistaSolitario, solitario.obtenerCimiento(i), i, datosMovimiento, this));

        for(int i = 0; i < Constantes.obtenerCantidadPilasTableau(solitario.obtenerVariante()); i++)
            controladoresPila.add(new ControladorPila(vistaSolitario, solitario.obtenerPilaDelTableau(i), datosMovimiento, this));
    }

    public abstract void actualizar();
    protected static void evaluarGanador() {
        if (solitario.jugadorGano()) {
            StackPane root = new StackPane();
            // Configurar el fondo en verde
            root.setStyle("-fx-background-color: green;");

            // Crear un mensaje Label
            Label mensajeGanador = new Label(Constantes.MENSAJE_GANADOR);
            mensajeGanador.setStyle("-fx-font-size: 24; -fx-text-fill: white;");
            mensajeGanador.setTranslateY(-50);

            Label mensajePuntos = new Label(Constantes.MENSAJE_GANADOR_PUNTOS + solitario.obtenerPuntos());
            mensajePuntos.setStyle("-fx-font-size: 24; -fx-text-fill: white;");
            mensajePuntos.setTranslateY(0);

            // Agregar el mensaje Label al StackPane
            root.getChildren().addAll(mensajeGanador, mensajePuntos);

            Button boton = new Button("Salir");
            boton.setOnAction(e -> System.exit(0));
            boton.setTranslateY(100);
            root.getChildren().add(boton);

            // Crear la escena
            Scene scene = new Scene(root, 640, 480, Color.GREEN);

            // Configurar la escena en el stage
            vistaSolitario.configurarNuevaStage(scene);
        }
    }
}
