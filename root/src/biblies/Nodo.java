package biblies;

public class Nodo {
    public Texto textoActual;
    public Nodo siguiente;
    public Nodo anterior;
    public Nodo(Texto textoActual){
        this.textoActual=textoActual;
        this.siguiente=null;
        this.anterior=null;
    }
    public String imprimir(){
        return textoActual.getTitulo();
    }

}