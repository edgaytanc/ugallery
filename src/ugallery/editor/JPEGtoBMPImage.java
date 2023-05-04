package ugallery.editor;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import ugallery.tools.ImageHandler;

public class JPEGtoBMPImage extends ImageHandler {
    private BufferedImage bufferedImage;
    private String outputImagePath;

    public JPEGtoBMPImage(String inputImagePath) {
        super(inputImagePath);
        this.outputImagePath = createOutputImagePath(inputImagePath);
        //temporal
        System.out.println(outputImagePath);
    }

    private String createOutputImagePath(String inputImagePath) {
        String directory = new File(inputImagePath).getParent();
        String baseName = inputImagePath.substring(0, inputImagePath.lastIndexOf('.'));
        
        //temporal
        System.out.println(directory);
        System.out.println(baseName);
        
        return directory + File.separator + "converted-" + new File(baseName).getName() + ".bmp";
    }

    @Override
    public void readFile() throws IOException {
        File inputFile = new File(handledFileName);
        bufferedImage = ImageIO.read(inputFile);
        System.out.println("Imagen JPEG leída: " + handledFileName);
    }

    @Override
    public void generateFiles() throws IOException {
        File outputFile = new File(outputImagePath);
        ImageIO.write(bufferedImage, "bmp", outputFile);
        System.out.println("Imagen BMP generada: " + outputImagePath);
    }

    public String getOutputImagePath() {
        return outputImagePath;
    }
}
