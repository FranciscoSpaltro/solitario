package modelo;

import java.io.*;

public class Persistencia {

    public static boolean existeArchivo(String ruta) {
        File archivo = new File(ruta);
        return archivo.exists();
    }
    public static void escribirObjeto(OutputStream salida, Object objeto) throws IOException {
        try (var objetoSalida = new ObjectOutputStream(salida)) {
            objetoSalida.writeObject(objeto);
        }
    }

    public static Object importarObjeto(InputStream entrada) throws IOException, ClassNotFoundException {
        try (var objetoEntrada = new ObjectInputStream(entrada)) {
            return objetoEntrada.readObject();
        }
    }

    public static void borrarArchivo(String rutaPorDefecto) {
        File archivo = new File(rutaPorDefecto);
        archivo.delete();
    }
}
