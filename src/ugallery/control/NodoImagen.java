
package ugallery.control;

public class NodoImagen {
    private String imagen;
    private NodoImagen siguiente;
    private NodoImagen anterior;

    public NodoImagen(String imagen) {
        this.imagen = imagen;
        this.siguiente = null;
        this.anterior = null;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public NodoImagen getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoImagen siguiente) {
        this.siguiente = siguiente;
    }

    public NodoImagen getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoImagen anterior) {
        this.anterior = anterior;
    }
}


