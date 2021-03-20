package generator.math;

import generator.Generator;

import java.util.Random;

public class Vector2
{

    public static Vector2 random()
    {
        Random random = Generator.getRandom();
        return new Vector2(random.nextDouble(), random.nextDouble());
    }


    public final double x;
    public final double y;


    public Vector2(double x, double y)
    {
        this.x = x;
        this.y = y;
    }


    public Vector2 mutate()
    {
        double x = mutate(this.x);
        double y = mutate(this.y);

        return new Vector2(x, y);
    }

    private double mutate(double value)
    {
        Random random = Generator.getRandom();
        return (random.nextDouble() < 0.05) ? random.nextDouble() : value + random.nextGaussian() * 0.01;
    }

}
