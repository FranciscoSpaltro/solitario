package modelo;

public enum ErrorAlMover {
    PILA_VACIA_NO_REY,
    ORDEN_NO_DESCENDENTE,
    ORDEN_NO_ASCENDENTE,
    CIMIENTO_VACIO_NO_AS,
    PILA_CARTAS_MISMO_COLOR,
    CARTA_A_MOVER_NO_BOCA_ARRIBA,
    CIMIENTO_CARTAS_DISTINTO_PALO,
    MAZO_VACIO,
    ERROR_DE_PROGRAMA,
    PILAS_VACIA_NO_PUEDE_SACAR_DEL_MAZO,
    PILA_INCOMPLETA_NO_PUEDE_IR_A_CIMIENTO,
    CARTAS_A_MOVER_DISTINTO_PALO,
    PILA_DIFERENTE_PALO_NO_PUEDE_IR_A_CIMIENTO,
    CIMIENTO_VACIO_NO_SACAR_CARTA,
    BASURA_VACIA_NO_SACAR_CARTA,
    PILA_VACIA_NO_SACAR_CARTA;

    public static String obtenerMensaje(ErrorAlMover error) {
        switch (error) {
            case PILA_VACIA_NO_REY : return "A una pila vacía solo puede moverse un rey";
            case ORDEN_NO_DESCENDENTE : return "Las cartas en la pila deben colocarse en orden descendente y continuo";
            case ORDEN_NO_ASCENDENTE : return "Las cartas en el cimiento deben colocarse en orden ascendente y continuo";
            case CIMIENTO_VACIO_NO_AS : return "Los cimientos comienzan con un AS";
            case PILA_CARTAS_MISMO_COLOR : return "En esta versión del solitario, los colores de las cartas en la pila deben colocarse de forma intercalada";
            case CARTA_A_MOVER_NO_BOCA_ARRIBA : return "Para mover una carta, debe estar boca arriba";
            case CIMIENTO_CARTAS_DISTINTO_PALO : return "En un cimiento se colocan solo las cartas del mismo palo";
            case MAZO_VACIO : return "El mazo está vacío";
            case ERROR_DE_PROGRAMA : return "Ocurrion un error en el programa";
            case PILAS_VACIA_NO_PUEDE_SACAR_DEL_MAZO : return "No se puede sacar cartas del mazo si hay pilas vacías";
            case PILA_INCOMPLETA_NO_PUEDE_IR_A_CIMIENTO : return "No se puede mover una pila incompleta a un cimiento";
            case CARTAS_A_MOVER_DISTINTO_PALO : return "Las cartas a mover deben ser del mismo palo";
            case PILA_DIFERENTE_PALO_NO_PUEDE_IR_A_CIMIENTO : return "El cimiento solo puede recibir cartas del mismo palo";
            case CIMIENTO_VACIO_NO_SACAR_CARTA : return "El cimiento está vacío";
            case BASURA_VACIA_NO_SACAR_CARTA : return "La basura está vacía";
            case PILA_VACIA_NO_SACAR_CARTA : return "La pila está vacía";
            default : return "Movimiento desconocido";
        }
    }
}
