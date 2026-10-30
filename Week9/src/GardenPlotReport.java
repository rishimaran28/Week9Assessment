import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Base class for all plot shapes
abstract class Plot {
    protected String owner;
    protected String shape;

    public Plot(String owner, String shape) {
        this.owner = owner;
        this.shape = shape;
    }

    public String getOwner() {
        return owner;
    }

    public String getShape() {
        return shape;
    }

    public abstract double calculateArea();
}

// Circle Plot
class CirclePlot extends Plot {
    private double radius;

    public CirclePlot(String owner, double radius) {
        super(owner, "CIRCLE");
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

// Rectangle Plot
class RectanglePlot extends Plot {
    private double length;
    private double width;

    public RectanglePlot(String owner, double length, double width) {
        super(owner, "RECTANGLE");
        this.length = length;
        this.width = width;
    }

    @Override
    public double calculateArea() {
        return length * width;
    }
}

// Triangle Plot
class TrianglePlot extends Plot {
    private double base;
    private double height;

    public TrianglePlot(String owner, double base, double height) {
        super(owner, "TRIANGLE");
        this.base = base;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }
}

public class GardenPlotReport {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return;
        }
        int n = scanner.nextInt();
        List<Plot> plots = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String shape = scanner.next();
            String owner = scanner.next();
            if ("CIRCLE".equalsIgnoreCase(shape)) {
                double radius = scanner.nextDouble();
                plots.add(new CirclePlot(owner, radius));
            } else if ("RECTANGLE".equalsIgnoreCase(shape)) {
                double length = scanner.nextDouble();
                double width = scanner.nextDouble();
                plots.add(new RectanglePlot(owner, length, width));
            } else if ("TRIANGLE".equalsIgnoreCase(shape)) {
                double base = scanner.nextDouble();
                double height = scanner.nextDouble();
                plots.add(new TrianglePlot(owner, base, height));
            }
        }

        double totalArea = 0.0;
        for (Plot plot : plots) {
            double area = plot.calculateArea();
            totalArea += area;
            System.out.printf("%s (%s): %.2f\n", plot.getOwner(), plot.getShape(), area);
        }
        System.out.printf("Total Area: %.2f\n", totalArea);

        scanner.close();
    }
}