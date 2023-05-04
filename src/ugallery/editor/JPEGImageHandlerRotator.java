package ugallery.editor;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import ugallery.tools.ImageHandler;

public class JPEGImageHandlerRotator extends ImageHandler {

    private BufferedImage bufferedImage;

    public JPEGImageHandlerRotator(String filename) {
        super(filename);
    }

    @Override
    public void readFile() throws Exception {
        File file = new File(handledFileName);
        bufferedImage = ImageIO.read(file);
        System.out.println("Imagen JPEG leída: " + handledFileName);
    }

    @Override
    public void generateFiles() throws Exception {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        BufferedImage hRotationImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage vRotationImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = bufferedImage.getRGB(x, y);

                // Rotar 180 grados horizontalmente
                hRotationImage.setRGB(width - x - 1, y, rgb);

                // Rotar 180 grados verticalmente
                vRotationImage.setRGB(x, height - y - 1, rgb);
            }
        }

        String parentPath = getParentPath();
        String fileNameWithoutExtension = getFileNameWithoutExtension();

        // Guardar imágenes generadas
        ImageIO.write(hRotationImage, "jpeg", new File(parentPath +"/Hrotation-" + fileNameWithoutExtension + ".jpeg"));
        ImageIO.write(vRotationImage, "jpeg", new File(parentPath + "/Vrotation-" + fileNameWithoutExtension + ".jpeg"));

        System.out.println("Imágenes rotadas generadas.");
    }

    private String getFileNameWithoutExtension() {
        File file = new File(handledFileName);
        String fileName = file.getName();
        int lastIndex = fileName.lastIndexOf(".");
        return fileName.substring(0, lastIndex);
    }

    private String getParentPath() {
        File file = new File(handledFileName);
        return file.getParent();
    }
}

