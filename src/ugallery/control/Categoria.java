package ugallery.control;

import java.io.IOException;
import java.util.LinkedList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Categoria {
    private String nombre;
    private Path directorio;
    private ListaDoblementeEnlazada imagenes;

    public Categoria(String nombre) {
        this.nombre = nombre;
        
        this.imagenes = new ListaDoblementeEnlazada();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void agregarImagen(String imagen) {
        imagenes.agregarImagen(imagen);
    }

    public void eliminarImagen(String imagen) {
        imagenes.eliminarImagen(imagen);
    }
    
    public String siguienteImagen() {
        return imagenes.siguienteImagen();
    }

    public String imagenAnterior() {
        return imagenes.imagenAnterior();
    }
    
    public Path getDirectorio() {
        return directorio;
    }
    
    public void crearCarpetaCategoria(String nombreUsuario) {
        try {
            Path directorioUsuario = Paths.get(nombreUsuario);
            directorio = directorioUsuario.resolve(nombre);
            if (!Files.exists(directorio)) {
                Files.createDirectory(directorio);
            }
        } catch (IOException e) {
            System.err.println("Error al crear la carpeta del usuario: " + e.getMessage());
        }
    }
}


