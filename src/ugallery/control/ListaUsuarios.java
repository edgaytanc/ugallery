
package ugallery.control;

public class ListaUsuarios {
    private NodoUsuario cabeza;

    public ListaUsuarios() {
        this.cabeza = null;
    }

    public void agregarUsuario(Usuario usuario) {
        NodoUsuario nuevoNodo = new NodoUsuario(usuario);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            NodoUsuario actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
    }

    public void eliminarUsuario(Usuario usuario) {
        if (cabeza == null) {
            return;
        }
        if (cabeza.getUsuario().equals(usuario)) {
            cabeza = cabeza.getSiguiente();
            return;
        }
        NodoUsuario actual = cabeza;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getUsuario().equals(usuario)) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                return;
            }
            actual = actual.getSiguiente();
        }
    }
    
    public Usuario buscarUsuario(String nombre){
        NodoUsuario actual = cabeza;
        while(actual != null){
            if(actual.getUsuario().getNombre().equals(nombre)){
                return actual.getUsuario();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    
    public NodoUsuario getCabeza() {
        return cabeza;
    }
    
    
}

