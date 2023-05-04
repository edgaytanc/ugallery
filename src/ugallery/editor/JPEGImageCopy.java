
package ugallery.editor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import ugallery.tools.ImageHandler;

public class JPEGImageCopy extends ImageHandler {

    private BufferedImage bufferedImage;

    public JPEGImageCopy(String filename) {
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
        String outputFileName = createOutputImagePath(handledFileName);
        File outputFile = new File(outputFileName);
        ImageIO.write(bufferedImage, "jpeg", outputFile);
        System.out.println("Imagen copiada: " + outputFileName);
    }

    private String createOutputImagePath(String inputImagePath) {
        String baseName = getFileNameWithoutExtension(inputImagePath);
        String parentPath = getParentPath(inputImagePath);
        return parentPath + "/copia-" + baseName +".jpg";
    }

    private String getFileNameWithoutExtension(String filePath) {
        File file = new File(filePath);
        String fileName = file.getName();
        int lastIndex = fileName.lastIndexOf(".");
        return fileName.substring(0, lastIndex);
    }

    private String getParentPath(String filePath) {
        File file = new File(filePath);
        return file.getParent();
    }
}
