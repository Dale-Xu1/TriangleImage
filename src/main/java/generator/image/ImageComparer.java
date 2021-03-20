package generator.image;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class ImageComparer
{

    private final int width;
    private final int height;

    private final PixelReader target;
    private final GraphicsContext gc;


    public ImageComparer(Image image, GraphicsContext gc)
    {
        width = (int) image.getWidth();
        height = (int) image.getHeight();

        target = image.getPixelReader();
        this.gc = gc;
    }


    public double compare(Genome genome)
    {
        genome.render(gc, width, height);

        // Get snapshot of canvas
        WritableImage image = gc.getCanvas().snapshot(null, null);
        PixelReader reader = image.getPixelReader();

        // Sum error
        double error = 0;

        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                Color a = target.getColor(i, j);
                Color b = reader.getColor(i, j);

                // Calculate difference
                double red = Math.abs(a.getRed() - b.getRed());
                double green = Math.abs(a.getGreen() - b.getGreen());
                double blue = Math.abs(a.getBlue() - b.getBlue());

                error += red + green + blue;
            }
        }

        return error / (width * height * 3);
    }

}
