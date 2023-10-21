public class InvalidMovementException extends RuntimeException{
    ErrorAlMover motivo;
    public InvalidMovementException(ErrorAlMover motivo){
        this.motivo = motivo;
    }
    public ErrorAlMover obtenerMotivo(){
        return motivo;
    }
}
