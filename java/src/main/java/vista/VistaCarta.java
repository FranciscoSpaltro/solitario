package vista;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modelo.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class VistaCarta implements Serializable {
    private final Map<Carta, ImageView> catalogo = new HashMap<>();
    private final Image dorso;
    private final Image vacio;
    private final Variante variante;
    boolean hayEfectoActivado;
    private final DropShadow efecto = new DropShadow();
    private Carta ultimaCartaSeleccionada;

    public VistaCarta(Solitario solitario) {
        // Cargar las imágenes de las cartas y asociarlas con las combinaciones de palo y valor
        for (Carta carta : solitario.obtenerMazo()) {
            var imagen = new ImageView(new Image(obtenerNombreDeArchivo(carta)));
            catalogo.put(carta, imagen);
        }
        this.variante = solitario.obtenerVariante();
        dorso = obtenerImagenFondo();
        vacio = obtenerImagenNoCarta();
        hayEfectoActivado = false;
        efecto.setRadius(50);
        ultimaCartaSeleccionada = null;
    }

    public VistaCarta(Mazo mazo, Variante variante) {
        // Cargar las imágenes de las cartas y asociarlas con las combinaciones de palo y valor
        for (Carta carta : mazo) {
            var imagen = new ImageView(new Image(obtenerNombreDeArchivo(carta)));
            catalogo.put(carta, imagen);
        }
        this.variante = variante;
        dorso = obtenerImagenFondo();
        vacio = obtenerImagenNoCarta();
        hayEfectoActivado = false;
    }

    private String obtenerNombreDeArchivo(Carta carta) {
        return "/cartas/" + carta.verValor().toString() + "_" + carta.verPalo().toString() + ".png";
    }

    public Image obtenerImagenFondo() {
        return new Image ("/cartas/bkg_" + variante.toString().toLowerCase() + ".png");
    }

    public Image obtenerImagenNoCarta() {
        return new Image ("/cartas/vacio_" + variante.toString().toLowerCase() + ".png");
    }

    public void configurarEfecto(ImageView imagen) {
        Carta carta = encontrarCarta(imagen);

        if (carta == ultimaCartaSeleccionada) {
            eliminarEfectos();
            hayEfectoActivado = false;
            ultimaCartaSeleccionada = null;
            return;
        }

        ultimaCartaSeleccionada = carta;
        if (carta == null) // Fondo vacío o dorso de carta
           return;

        if (!hayEfectoActivado) {
            hayEfectoActivado = true;
            catalogo.get(carta).setEffect(efecto);
        } else {
            eliminarEfectos();
            hayEfectoActivado = false;
            catalogo.get(carta).setEffect(efecto);
        }
    }

    public void eliminarEfectos() {
        for (Map.Entry<Carta, ImageView> entry : catalogo.entrySet()) {
            entry.getValue().setEffect(null);
        }
    }

    private Carta encontrarCarta(ImageView imagen) {
        for (Map.Entry<Carta, ImageView> entry : catalogo.entrySet()) {
            if (entry.getValue().equals(imagen))
                return entry.getKey();
        }
        return null;
    }


    public ImageView obtenerImagen(Carta carta) {
        if (carta == null)
            return new ImageView(vacio);

        if (!carta.estaBocaArriba())
            return new ImageView(dorso);

        return catalogo.get(carta);
    }


    public void resetearControladores() {
        for (Map.Entry<Carta, ImageView> entry : catalogo.entrySet()) {
            entry.getValue().setOnMouseClicked(null);
        }
    }
}
