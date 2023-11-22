package vista;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import modelo.Constantes;

public class VistaGanador {
    Scene scene;
    public VistaGanador(int puntos){
        StackPane root = new StackPane();
        // Configurar el fondo en verde
        root.setStyle("-fx-background-color: green;");

        // Crear un mensaje Label
        Label mensajeGanador = new Label(Constantes.MENSAJE_GANADOR);
        mensajeGanador.setStyle("-fx-font-size: 24; -fx-text-fill: white;");
        mensajeGanador.setTranslateY(-50);

        Label mensajePuntos = new Label(Constantes.MENSAJE_GANADOR_PUNTOS + puntos);
        mensajePuntos.setStyle("-fx-font-size: 24; -fx-text-fill: white;");
        mensajePuntos.setTranslateY(0);

        // Agregar el mensaje Label al StackPane
        root.getChildren().addAll(mensajeGanador, mensajePuntos);

        Button boton = new Button("Salir");
        boton.setOnAction(e -> System.exit(0));
        boton.setTranslateY(100);
        root.getChildren().add(boton);
        scene = new Scene(root, 640, 480, Color.GREEN);
    }

    public Scene obtenerScene(){
        return scene;
    }
}
