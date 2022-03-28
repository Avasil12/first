import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * 5. ��������, ����������� �� ����� 
 */

public class Image extends Object {
    private java.awt.Image img;
    private double rotX, rotY;	// ���������� ������ ��������
    private double R;	// ������
    private double angle;	// ����
    private int direct;	// ����������� ��������

    Image(int x, int y, int height, int width) {
        super(x, y, height, width);
        setHeight(img.getHeight(null));
        setWidth(img.getWidth(null));
    }
    
    Image(int x, int y, String filename) {
        super(x, y, 0, 0);
        img = new ImageIcon(filename).getImage();

        setHeight(img.getHeight(null));
        setWidth(img.getWidth(null));

        setX(getX()-getWidth()/2);
        setY(getY()-getHeight()/2);
        
        init();
    }
    
    // ������������� 
    private void init() {
    	Random r = new Random();
    	
    	R = r.nextDouble(100) + 100;	// ������ �� 100 �� 200
    	angle = 0;
    	
    	if ( r.nextInt(100) > 50 ) {	// ��������� ���� 0 ��� ��
    		angle = Math.PI;
    		rotX = getX() + R;
    	}
    	else
    		rotX = getX() - R;
    	
    	if ( r.nextInt(100) > 50 ) // ����������� �������� �� ��� ������� ������� �������
    		direct = 1;
    	else
    		direct = -1;
    	rotY = getY();
    }

    public void paint(Graphics g) {
        g.drawImage(img, (int) getX(), (int) getY(), null);
    }

    public void setImage(java.awt.Image img) {
        this.img = img;
    }
    
    public java.awt.Image getImage() {
    	return img;
    }

    /**
     * 4. �������� �� ���������� �� ��������� ��������
    */
    @Override
    public void moveX(double t) {
        if (this.isMoving())
        	this.setX(rotX+R*Math.cos(angle+direct*getdX()*t*2*Math.PI/100000000.0));
    }

    @Override
    public void moveY(double t) {
        if(this.isMoving())
        	this.setY(rotY+R*Math.sin(angle+direct*getdX()*t*2*Math.PI/100000000.0));
    }
    
    @Override
    public void move(double t) {
    	moveX(t);
    	moveY(t);
    	angle = angle + direct*(2*Math.PI/100000000.0); 
    }

}
