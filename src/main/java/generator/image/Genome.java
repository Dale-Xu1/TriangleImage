package generator.image;

import javafx.scene.canvas.GraphicsContext;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Genome
{

    private final ImageComparer comparer;

    private final List<Triangle> triangles = new ArrayList<>();

    private Triangle best;
    private double error;

    private Triangle current;


    public Genome(ImageComparer comparer)
    {
        this.comparer = comparer;
        random();
    }


    public void next()
    {
        // Move onto next triangle
        triangles.add(current);
        random();
    }

    private void random()
    {
        best = current = Triangle.random();
        error = comparer.compare(this);
    }


    public void mutate()
    {
        current = current.mutate();
        double next = comparer.compare(this);

        if (next < error)
        {
            // Save mutation because it is closer to the image
            best = current;
            error = next;
        }
        else
        {
            // Revert mutation
            current = best;
        }
    }

    public double getError()
    {
        return error;
    }


    public void render(GraphicsContext gc, int width, int height)
    {
        gc.clearRect(0, 0, width, height);

        // Draw all triangles
        for (Triangle triangle : triangles)
        {
            triangle.render(gc, width, height);
        }
        current.render(gc, width, height);
    }


    public void append(BufferedWriter writer) throws IOException
    {
        // Append current triangle to file
        current.serialize(writer);
    }

}
