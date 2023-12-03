package math;


public class Vector2f {

    public float x;
    public float y;

    public static float worldX;
    public static float worldY;
    public static float dimensionX;
    public static float dimensionY;

    public Vector2f() {
        x = 0;
        y = 0;
    }

    public Vector2f(Vector2f vec) {
        new Vector2f(vec.x, vec.y);
    }

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void addX(float f) { x += f; }
    public void addY(float f) { y += f; }

    public void setX(float f) { x = f; }
    public void setY(float f) { y = f; }

    public void setVector(Vector2f vec) {
        this.x = vec.x;
        this.y = vec.y;
    }

    public void setVector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setVectorMap(float x, float y) {
        this.x = -x;
        this.y = -x;
    }

    public static void setWorldVariables(float x, float y) {
        worldX = x;
        worldY = y;
    }



    public static void setMapDimensions(float x, float y) {
        dimensionX = x;
        dimensionY = y;
    }

    public Vector2f getMapDimensions() {
        return new Vector2f(Math.abs(dimensionX),Math.abs(dimensionY));
    }

    public static float getWorldVariableX(float x) {
        return x - worldX;
    }

    public static float getWorldVariableY(float y) {
        return y - worldY;
    }

    public Vector2f getWorldVariables() {
        return new Vector2f(x - worldX, y - worldY);
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }

}
