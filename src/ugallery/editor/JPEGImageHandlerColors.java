package ugallery.editor;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import ugallery.tools.ImageHandler;

public class JPEGImageHandlerColors extends ImageHandler {

    private BufferedImage bufferedImage;
    private String bmpFileName;

    public JPEGImageHandlerColors(String filename) {
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

        BufferedImage redImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage greenImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage blueImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        BufferedImage sepiaImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = bufferedImage.getRGB(x, y);
                Color color = new Color(rgb);

                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                // Generar imagen roja
                redImage.setRGB(x, y, new Color(red, 0, 0).getRGB());

                // Generar imagen verde
                greenImage.setRGB(x, y, new Color(0, green, 0).getRGB());

                // Generar imagen azul
                blueImage.setRGB(x, y, new Color(0, 0, blue).getRGB());

                // Generar imagen sepia
                int sepiaR = (int) Math.min(255, 0.393 * red + 0.769 * green + 0.189 * blue);
                int sepiaG = (int) Math.min(255, 0.349 * red + 0.686 * green + 0.168 * blue);
                int sepiaB = (int) Math.min(255, 0.272 * red + 0.534 * green + 0.131 * blue);
                sepiaImage.setRGB(x, y, new Color(sepiaR, sepiaG, sepiaB).getRGB());
            }
        }

        String parentPath = getParentPath();
        String fileNameWithoutExtension = getFileNameWithoutExtension();

        // Guardar imágenes generadas
        ImageIO.write(redImage, "jpeg", new File(parentPath + "/Red-" + fileNameWithoutExtension + ".jpeg"));
        ImageIO.write(greenImage, "jpeg", new File(parentPath + "/Green-" + fileNameWithoutExtension + ".jpeg"));
        ImageIO.write(blueImage, "jpeg", new File(parentPath + "/Blue-" + fileNameWithoutExtension + ".jpeg"));
        ImageIO.write(sepiaImage, "jpeg", new File(parentPath + "/Sepia-" + fileNameWithoutExtension + ".jpeg"));

        System.out.println("Imágenes de colores y sepia generadas.");
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

