import java.awt.*;

/**
 * Necessary information is kept to show in the simulation
 */
public class PersonData {
    private double coordinateX;
    private double coordinateY;
    private int width;
    private int height;
    Color color;

    /**
     * Constructor
     * @param coordinateX
     * @param coordinateY
     * @param width
     * @param height
     * @param color
     */
    public PersonData(double coordinateX, double coordinateY, int width, int height,Color color) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public double getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
