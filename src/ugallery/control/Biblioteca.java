package ugallery.control;

import java.io.Serializable;

public class Biblioteca implements Serializable{
    private ListaUsuarios usuarios;

    public Biblioteca() {
        this.usuarios = new ListaUsuarios();
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.agregarUsuario(usuario);
    }
    
    //darlo haste despues de crear metodo en listaUsuarios
    public Usuario recuperarUsuario(String usuario){
        return usuarios.buscarUsuario(usuario);
    }

    public void eliminarUsuario(Usuario usuario) {
        usuarios.eliminarUsuario(usuario);
    }
    public boolean existeUsuario(String nombre) {
        NodoUsuario nodoUsuarioActual = usuarios.getCabeza();
        while (nodoUsuarioActual != null) {
            if (nodoUsuarioActual.getUsuario().getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
            nodoUsuarioActual = nodoUsuarioActual.getSiguiente();
        }
        return false;
    }

    public void agregarUsuarioSiNoExiste(String nombre) {
        Usuario usuario = usuarios.buscarUsuario(nombre);
        if (usuario == null) {
            usuario = new Usuario(nombre);
            //usuario.crearCarpetaUsuario(); // Llama al método para crear la carpeta
            usuarios.agregarUsuario(usuario);
        }
    }
}

