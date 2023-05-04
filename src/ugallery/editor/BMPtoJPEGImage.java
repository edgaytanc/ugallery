package ugallery.editor;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import ugallery.tools.ImageHandler;

public class BMPtoJPEGImage extends ImageHandler {

    private BufferedImage bufferedImage;

    public BMPtoJPEGImage(String filename) {
        super(filename);
    }

    @Override
    public void readFile() throws Exception {
        File file = new File(handledFileName);
        bufferedImage = ImageIO.read(file);
        System.out.println("Imagen BMP leída: " + handledFileName);
    }

    @Override
    public void generateFiles() throws Exception {
        String outputFileName = createOutputImagePath(handledFileName);
        File outputFile = new File(outputFileName);
        ImageIO.write(bufferedImage, "jpg", outputFile);
        System.out.println("Imagen JPEG generada: " + outputFileName);
    }

    private String getFileNameWithoutExtension() {
        int lastIndex = handledFileName.lastIndexOf(".");
        return handledFileName.substring(0, lastIndex);
    }

    private String createOutputImagePath(String inputImagePath) {
        String directory = new File(inputImagePath).getParent();
        String baseName = inputImagePath.substring(0, inputImagePath.lastIndexOf('.'));
        return directory + File.separator + "converted-" + new File(baseName).getName() + ".jpg";
    }
}
