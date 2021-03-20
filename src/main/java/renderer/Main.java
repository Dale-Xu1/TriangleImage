package renderer;

import generator.image.Triangle;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main extends Application
{

    private static String input;
    private static String output;


    public static void initialize(String[] args)
    {
        input = args[0];
        output = args[1];

        launch();
    }

    @Override
    public void start(Stage stage)
    {
        int width = 1980;
        int height = 1080;

        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        try
        {
            Scanner reader = new Scanner(new File(input));
            while (reader.hasNextLine())
            {
                // Parse and render triangle
                Triangle triangle = new Triangle(reader.nextLine());
                triangle.render(gc, width, height);
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        WritableImage image = canvas.snapshot(null, null);

        try
        {
            // Output image data
            File file = new File(output);
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        System.exit(0);
    }

}
