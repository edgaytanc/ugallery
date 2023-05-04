package ugallery.editor;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import ugallery.tools.ImageHandler;

public class JPEGImageHandlerBN extends ImageHandler {

    private BufferedImage bufferedImage;

    public JPEGImageHandlerBN(String filename) {
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

        BufferedImage bnImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = bufferedImage.getRGB(x, y);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;

                // Calcular la intensidad de gris
                int gray = (int) (0.299 * red + 0.587 * green + 0.114 * blue);

                // Convertir a blanco y negro
                int bw = (gray < 128) ? 0 : 255;
                int bwRgb = (bw << 16) | (bw << 8) | bw;

                bnImage.setRGB(x, y, bwRgb);
            }
        }

        String parentPath = getParentPath();
        String fileNameWithoutExtension = getFileNameWithoutExtension();

        // Guardar imágenes generadas
        ImageIO.write(bnImage, "jpeg", new File(parentPath + "/BN-" + fileNameWithoutExtension + ".jpeg"));

        System.out.println("Imágenes en blanco y negro generadas.");
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

