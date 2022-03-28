import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class Habitat extends JPanel {
    final public static int WIDTH = 800;
    final public static int HEIGHT = 600;
    final private int POSITION_X = 0;
    final private int POSITION_Y = 0;
    private boolean isRunImage = true;
    private boolean isRunPolygon = true;
    private Thread threadObjects;
    private ArrayList<Object> listObjects = new ArrayList<Object>();
    
    private boolean toDraw = true;
    private boolean isRun = true;

    public Habitat() {
        setSize(WIDTH, HEIGHT);
        setLocation(POSITION_X, POSITION_Y);
        setFocusable(true);
        addMouseListener(new PanelMouseListener());
        addKeyListener(new PanelKeyboardListener());
        setLayout(null);

        threadObjects = new Thread(new Runnable() {
            @Override
            public void run() {
                updateObjects();
            }
        });
        
        threadObjects.start();
    }

    public void addImage(int x, int y) {
    	listObjects.add(new Image(x, y, "icon.jpg"));
    }

    public void addStar(int x, int y) {
        listObjects.add(new Polygon(x, y));
    }

    public void moveObjects(double t) {
        Object o;

        for (int i = 0; i < listObjects.size(); i++) {
        	try {
        		o = listObjects.get(i);
                if ( o.isMoving() )
                	o.move(t);
        	}
        	catch(NullPointerException  e) {};
        }
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        
        for (int i = 0; i < listObjects.size(); i++) {
        	try {
        		listObjects.get(i).paint(g);
        	}
        	catch(NullPointerException  e) {};
        }
    }
    
    public void updateObjects() {

        double before, now;
        before = System.currentTimeMillis();

        while (true) {
            now = System.currentTimeMillis();
            if (isRun) {
                moveObjects((now - before));
            }
            before = now;
            repaint();
        }
    }

    class PanelMouseListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            super.mouseClicked(e);

            // ��������, ��� ������ ���� ��������� �� ����� ���� ������� 
            for (int i = 0; i < listObjects.size(); i++) {
                if (listObjects.get(i).isBelongs(e.getX(), e.getY())) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                    	// ����� ������ ���� - ��������
                    	listObjects.remove(i);
                    } else if (e.getButton() == MouseEvent.BUTTON3) {
                    	// ������ ������ ���� - �����/���� ��������
                        if (listObjects.get(i).isMoving()) {
                        	listObjects.get(i).setMoving(false);
                        } else {
                        	listObjects.get(i).setMoving(true);
                        }
                    }
                    toDraw = false;	// ������� ����������
                }
            }
            
            if (toDraw) {
            	// ������� �� ����������
                if (e.getButton() == MouseEvent.BUTTON1) {
                	// ����� ������ ���� - �������� �������������
                	addStar(e.getX(), e.getY());
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                	// ����� ������ ���� - �������� �����������
                    addImage(e.getX(), e.getY());
                }
            }
            toDraw = true;
        }
    }

    class PanelKeyboardListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
            if (e.getKeyCode() == KeyEvent.VK_S) {
            	// ���������� ��� �������
                for (int i = 0; i < listObjects.size(); i++) {
                	try {
                		listObjects.get(i).setMoving(false);
                	}
                	catch(NullPointerException ex) {};
                }

            }
            else if (e.getKeyCode() == KeyEvent.VK_R) {
            	// ��������� ��� �������
                for (int i = 0; i < listObjects.size(); i++) {
                	try {
                		listObjects.get(i).setMoving(true);
                	}
                	catch(NullPointerException ex) {};
                }


            }

        }
        }
    }
