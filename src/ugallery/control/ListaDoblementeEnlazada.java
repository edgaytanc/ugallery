
package ugallery.control;


public class ListaDoblementeEnlazada {
    private NodoImagen cabeza;
    private NodoImagen cola;
    private NodoImagen nodoActual;

    public ListaDoblementeEnlazada() {
        this.cabeza = null;
        this.cola = null;
    }

    public void agregarImagen(String imagen) {
        NodoImagen nuevoNodo = new NodoImagen(imagen);
        if (cabeza == null) {
            cabeza = nuevoNodo;
            cola = nuevoNodo;
        } else {
            cola.setSiguiente(nuevoNodo);
            nuevoNodo.setAnterior(cola);
            cola = nuevoNodo;
        }
    }

    public void eliminarImagen(String imagen) {
        if (cabeza == null) {
            return;
        }
        if (cabeza.getImagen().equals(imagen)) {
            cabeza = cabeza.getSiguiente();
            if (cabeza != null) {
                cabeza.setAnterior(null);
            } else {
                cola = null;
            }
            return;
        }
        NodoImagen actual = cabeza;
        while (actual != null) {
            if (actual.getImagen().equals(imagen)) {
                if (actual.getAnterior() != null) {
                    actual.getAnterior().setSiguiente(actual.getSiguiente());
                }
                if (actual.getSiguiente() != null) {
                    actual.getSiguiente().setAnterior(actual.getAnterior());
                } else {
                    cola = actual.getAnterior();
                }
                return;
            }
            actual = actual.getSiguiente();
        }
    }
    
    public String siguienteImagen() {
        if (nodoActual == null) {
            nodoActual = cabeza;
        } else {
            nodoActual = nodoActual.getSiguiente();
        }

        return nodoActual != null ? nodoActual.getImagen() : null;
    }

    public String imagenAnterior() {
        if (nodoActual == null) {
            nodoActual = cabeza;
        } else {
            nodoActual = nodoActual.getAnterior();
        }

        return nodoActual != null ? nodoActual.getImagen() : null;
    }
}

