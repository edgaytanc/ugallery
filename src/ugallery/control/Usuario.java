
package ugallery.control;


//import java.awt.List;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Usuario implements Serializable {
    private String nombre;
    private ArrayList<Categoria> categorias;
    private transient Path directorio;
    private String directorioString;

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.categorias = new ArrayList<>();
        
        // Crear la carpeta del usuario
        crearCarpetaUsuario();
        
        // Crear la categoría General y su carpeta
        Categoria categoriaGeneral = new Categoria("General");
        categoriaGeneral.crearCarpetaCategoria(nombre);
        this.categorias.add(categoriaGeneral);
        
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarCategoria(Categoria categoria) {
        categoria.crearCarpetaCategoria(nombre);
        categorias.add(categoria);
    }

    public void eliminarCategoria(Categoria categoria) {
        categorias.remove(categoria);
    }
    
    public ArrayList<String> getListaCategorias() {
        ArrayList<String> nombresCategorias = new ArrayList<>();
        for (Categoria categoria : categorias) {
            nombresCategorias.add(categoria.getNombre());
        }
        return nombresCategorias;
    }
    
    public void crearCarpetaUsuario() {
        try {
            directorio = Paths.get(nombre);
            directorioString = directorio.toString();
            if (!Files.exists(directorio)) {
                Files.createDirectory(directorio);
            }
        } catch (IOException e) {
            System.err.println("Error al crear la carpeta del usuario: " + e.getMessage());
        }
    }
    
    //actualiza la variable directorio despues de la deserializacion
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        directorio = Paths.get(directorioString);
    }
    
    public Path getDirectorio() {
        return directorio;
    }
    
    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }
    
    public Categoria getCategoriaPorNombre(String nombreCategoria) {
        for (Categoria categoria : categorias) {
            if (categoria.getNombre().equals(nombreCategoria)) {
                return categoria;
            }
        }
        return null;
    }



}

