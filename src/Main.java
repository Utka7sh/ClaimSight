import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            if (args.length < 2) {
                System.err.println("Please insert both the paths");
                return;
            }

            File originalFile = new File(args[0]);
            File comparisonFile = new File(args[1]);

            if (!originalFile.exists() || !comparisonFile.exists()) {
                System.err.println("One or both image files not found!");
                return;
            }

            BufferedImage originalImage = ImageIO.read(originalFile);
            BufferedImage comparisonImage = ImageIO.read(comparisonFile);

            if (originalImage == null || comparisonImage == null) {
                System.err.println("One or both image files are in an unsupported or corrupt format.");
                return;
            }

            double damagePercentage = ImageComparisonService.compareImages(originalImage, comparisonImage);
            System.out.printf("Damage percentage: %.2f%%%n", damagePercentage);
        } catch (Exception e) {
            System.err.println("Error processing images: " + e.getMessage());
        }
    }
}
