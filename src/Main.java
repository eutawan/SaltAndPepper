import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {

        // DEFINIR O NIVEL DE RUIDO
        double noiseLevel = 0.08;

        // MEU CAMINHO PARA A PASTA DE IMAGENS LIMPAS
        String cleanFolder = "src/clear";
        String dirtyFolder = "src/dirty";

        // PEGA TODA AS IMAGENS DA PASTA 'CLEAR'
        File dirClear = new File(cleanFolder);
        File[] images = dirClear.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg"));

        // VERIFICA SE TEM IMAGENS DENTRO DA PASTA 'CLEAR'
        if (images == null || images.length == 0) {
            System.out.println("no images where found in the 'clear' folder.");
            return;
        }

        Random random = new Random();

        for (File imageFile : images) {
            BufferedImage image = ImageIO.read(imageFile);

            int vizinhanca = 1;
            int width = image.getWidth();
            int height = image.getHeight();

            for (int i = vizinhanca; i < width - vizinhanca; i++) {
                for (int j = vizinhanca; j < height - vizinhanca; j++) {
                    double randomValue = random.nextDouble();

                    if (randomValue < noiseLevel) {
                        boolean whiteOrBlack = random.nextBoolean();

                        if (whiteOrBlack) {
                            image.setRGB(i, j, Color.WHITE.getRGB());
                        } else {
                            image.setRGB(i, j, Color.BLACK.getRGB());
                        }
                    }
                }
            }

            File output = new File(dirtyFolder, imageFile.getName());

            ImageIO.write(image, "jpg", output);
            System.out.println("images saved successfully in: " + output.getPath());
        }
    }
}