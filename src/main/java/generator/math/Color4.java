package generator.math;

import generator.Generator;

import java.util.Random;

public class Color4
{

    public static Color4 random()
    {
        Random random = Generator.getRandom();
        return new Color4(random.nextDouble(), random.nextDouble(), random.nextDouble(), random.nextDouble());
    }


    public final double r;
    public final double g;
    public final double b;
    public final double a;


    public Color4(double r, double g, double b, double a)
    {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }


    public Color4 mutate()
    {
        double r = mutate(this.r);
        double g = mutate(this.g);
        double b = mutate(this.b);
        double a = mutate(this.a);

        return new Color4(r, g, b, a);
    }

    private double mutate(double value)
    {
        Random random = Generator.getRandom();

        if (random.nextDouble() < 0.05)
        {
            // Reset value
            return random.nextDouble();
        }
        else
        {
            double result = value + random.nextGaussian() * 0.05;

            // Constrain values to between 0 and 1
            if (result < 0) result = 0;
            else if (result > 1) result = 1;

            return result;
        }
    }

}
