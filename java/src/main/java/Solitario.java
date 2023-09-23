import  java.util.List;
abstract class Solitario {
    //Atributos
    String variante;
    Mazo mazo;
    List<Cimiento> cimientos;
    List<Pila> pilas;
    Basura basura;

    //Métodos

    abstract void inicializarJuego();

    public boolean jugadorGano(){
        for (Cimiento cimiento : cimientos){
            if (!cimiento.estaCompleto())
                return false;
        }
        return true;
    }




}
