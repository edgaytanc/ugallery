package ugallery.control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.Serializable;

public class Categoria implements Serializable{
    private String nombre;
    private transient Path directorio;
    private String directorioString;
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
        // Obtén la lista de imágenes como ArrayList<Path>
        ArrayList<Path> imagenesPath = getImagenes();

        // Convierte la imagen (String) en un objeto Path
        Path imagenPath = Paths.get(imagen);

        // Verifica si la imagen existe en la lista de imágenes
        boolean imagenEncontrada = false;
        for (Path imgPath : imagenesPath) {
            if (imgPath.equals(imagenPath)) {
                imagenEncontrada = true;
                break;
            }
        }

        // Si la imagen existe en la lista, elimínala
        if (imagenEncontrada) {
            try {
                Files.delete(imagenPath);
                imagenes.eliminarImagen(imagen);
            } catch (IOException e) {
                System.err.println("Error al eliminar la imagen: " + e.getMessage());
            }
        } else {
            System.err.println("La imagen no se encontró en la lista de imágenes.");
        }
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
            directorioString = directorio.toString();
            if (!Files.exists(directorio)) {
                Files.createDirectory(directorio);
            }
        } catch (IOException e) {
            System.err.println("Error al crear la carpeta del usuario: " + e.getMessage());
        }
    }
    
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        directorio = Paths.get(directorioString);
    }
    
    public ArrayList<Path> getImagenes() {
        ArrayList<Path> imagenes = new ArrayList<>();

        try {
            Files.newDirectoryStream(directorio, path -> {
                String fileName = path.getFileName().toString().toLowerCase();
                return fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")
                    || fileName.endsWith(".png") || fileName.endsWith(".gif")
                    || fileName.endsWith(".bmp");
            }).forEach(imagenes::add);
        } catch (IOException e) {
            System.err.println("Error al obtener las imágenes: " + e.getMessage());
        }

        return imagenes;
    }

}


