import java.io.*;

public class Persistencia{
    public static void escribirObjeto(String nombreArchivoSalida, Object objeto) throws IOException {
        try (var objetoSalida = new ObjectOutputStream(new FileOutputStream(nombreArchivoSalida))) {
            objetoSalida.writeObject(objeto);
        }
    }

    public static Object importarObjeto(String nombreArchivoEntrada) throws IOException, ClassNotFoundException {
        try (var objetoEntrada = new ObjectInputStream(new FileInputStream(nombreArchivoEntrada))) {
            return objetoEntrada.readObject();
        }
    }
}
