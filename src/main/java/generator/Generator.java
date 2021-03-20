package generator;

import generator.image.Genome;
import generator.image.ImageComparer;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

public class Generator extends Group
{

    private class Timer extends AnimationTimer
    {
        @Override
        public void handle(long now)
        {
            render();
        }
    }


    private static final Random random = new Random();

    public static Random getRandom()
    {
        return random;
    }


    private final Genome genome;

    private double error = 1;
    private int iterations = 0;


    public Generator(Image image)
    {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        // Create canvas
        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        getChildren().add(canvas);

        // Create genome
        ImageComparer comparer = new ImageComparer(image, gc);
        genome = new Genome(comparer);

        // Start animation timer
        Timer timer = new Timer();
        timer.start();
    }


    private void render()
    {
        genome.mutate();

        // Mutate until error is surpassed
        if (genome.getError() < error)
        {
            iterations++;
            if (iterations > 200)
            {
                // More iterations to fine tune
                genome.next();

                error = genome.getError();
                iterations = 0;
            }
        }
    }

}
