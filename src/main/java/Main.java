import generator.Generator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;

public class Main extends Application
{

    private static String file;
    private static String output;


    public static void initialize(String[] args)
    {
        file = args[0];
        output = args[1];

        launch();
    }

    @Override
    public void start(Stage stage)
    {
        try
        {
            // Clear file
            FileWriter writer = new FileWriter(output);
            writer.close();

            Image image = new Image("file:" + file);
            Generator application = new Generator(image, output);

            Scene scene = new Scene(application);

            // Initialize stage
            stage.setTitle("Triangle Image");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
