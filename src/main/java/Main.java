import generator.Generator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application
{

    private static String file;


    public static void initialize(String[] args)
    {
        file = args[0];
        launch();
    }

    @Override
    public void start(Stage stage)
    {
        Image image = new Image("file:" + file);
        Generator application = new Generator(image);

        Scene scene = new Scene(application);

        // Initialize stage
        stage.setTitle("Triangle Image");
        stage.setScene(scene);
        stage.show();
    }

}
