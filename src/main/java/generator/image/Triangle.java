package generator.image;

import generator.math.Color4;
import generator.math.Vector2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle
{

    public static Triangle random()
    {
        return new Triangle(
            Vector2.random(),
            Vector2.random(),
            Vector2.random(),
            Color4.random()
        );
    }


    private final Vector2 a;
    private final Vector2 b;
    private final Vector2 c;

    private final Color4 color;


    public Triangle(Vector2 a, Vector2 b, Vector2 c, Color4 color)
    {
        this.a = a;
        this.b = b;
        this.c = c;

        this.color = color;
    }


    public Triangle mutate()
    {
        Vector2 a = this.a.mutate();
        Vector2 b = this.b.mutate();
        Vector2 c = this.c.mutate();

        Color4 color = this.color.mutate();

        return new Triangle(a, b, c, color);
    }

    public void render(GraphicsContext gc, double width, double height)
    {
        gc.setFill(new Color(color.r, color.g, color.b, color.a));

        // Draw triangle
        gc.beginPath();
        gc.moveTo(a.x * width, a.y * height);
        gc.lineTo(b.x * width, b.y * height);
        gc.lineTo(c.x * width, c.y * height);
        gc.fill();
    }

}
