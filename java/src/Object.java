import java.awt.*;
import java.util.Random;

abstract public class Object implements Drawable, Moveable{
    private double x, y;
    private int height, width;
    
    private double velocityX, velocityY;
    private boolean isMoving = false;
    private Color c;

    Object(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;

        Random r = new Random();
        
        // Начальная скорость
        velocityX = -100+r.nextInt(200);
        velocityY = -100+r.nextInt(200);

        this.height = height;
        this.width = width;
        
        c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }
    
    public void setY(double y) {
        this.y = y;
    }
    
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setdX(double dX) {
        this.velocityX = dX;
    }

    public double getdX() {
        return velocityX;
    }

    public void setdY(double dY) {
        this.velocityY = dY;
    }

    public double getdY() {
        return velocityY;
    }

    // Проверка, что точка принадлежит фигуре
    public boolean isBelongs(int x, int y) {
        System.out.println(this.x);
        System.out.println(this.y);
        System.out.println(x);
        System.out.println(y);
        if(this.x < x & (this.x + width) > x ) {
            if(this.y < y & (this.y + height) > y) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void moveX(double t) {
        if (isMoving)
            this.x = x + velocityX * t / 1000.0;
    }

    @Override
    public void moveY(double t) {
        if(isMoving)
            this.y = y + velocityY * t / 1000.0;
    }

    @Override
    public void move(double t) {
    	moveX(t);
    	moveY(t);
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public void setColor(Color c) {
        this.c = c;
    }

    public Color getColor() {
        return c;
    }

}
