import java.awt.*;
import java.util.Random;

/**
 * 2. Выпуклый многоугольник с произвольным количеством вершин, соединенных
 * последовательно.
 * */

// то что в скобках не совсем удавшееся создание генерации выпуклых многоугольников
public class Polygon extends Object {
  //  private int angle;	// Угол
    private int N;	// Количество вершин
    private double pX[]={10,30,0,-20,-30,-30};	// Координаты вершин X {10,30,0,-20,-30,-30}
    private double pY[]={-30,10,30,30,20,-20};// Y {-30,10,30,30,20,-20}
    private double f[]; //f[i]
    private double centerX, centerY;	// Координаты центра
    private int lengthen, ml; private double m,M,lenght;
    private int dXY;
    Polygon(int x, int y) {
    	super(x, y, 0, 0);
    	
        Random r = new Random();
        
       N =6;	// от 5 до 10 вершин
   // angle =0+r.nextInt(360) ;	// угол первой точки
        //ml=70;
        //lengthen =120;
        //pX = new double[N];
        //pY = new double[N];
        // Центры фигуры
        this.centerX = x;
        this.centerY = y;
        this.setX(x-30);
        this.setY(y-30);
        setWidth(60);
        setHeight(60);
       // m=ml/(2*Math.sin((180/N)*(Math.PI)/180));
        //M=lengthen/(2*Math.sin((180/N)*(Math.PI)/180));

            init();


    }
    

    private void init()
    {
        Random r = new Random();

        //f=new double[N];

        for(int i = 0; i < N; i++)
        {
           // f=new double[N];

             pX[i] =this.getCenterX()+pX[i];
             pY[i]=this.getCenterY()+pY[i];
         //   f[i]=0+r.nextDouble(360/N) -(360./N)/2;
           // lenght=m+r.nextDouble(M-m);
            //pX[i] =this.getCenterX() + Math.cos(angle+(360/N)*i+f[i]*Math.PI/180)*lenght;
        	//pY[i] = this.getCenterY() + Math.sin(angle+(360/N)*i+f[i]*Math.PI/180)*lenght;


        }
    }

    public void paint(Graphics g) {
    	
    	g.setColor(this.getColor());

            for (int i = 0; i < (N); i += 1){
                g.drawLine((int) pX[i], (int) pY[i], (int) pX[(i + 1) % N], (int) pY[(i + 1) % N]);
         //   g.drawLine((int) pX[i], (int) pY[i], (int) pX[(i + 2) % N], (int) pY[(i + 2) % N]);
        }


    }

    
    public void setCenterX(double centerX) {
    	this.centerX = centerX;
    }

    public double getCenterX() {
        return this.centerX;
    }
    
    public void setCenterY(double centerY) {
    	this.centerY = centerY;
    }
    
    public double getCenterY() {
        return this.centerY;
    }


    private void shift(double dx, double dy) {
        this.setX(this.getX() + dx);
        this.setY(this.getY() + dy);

        centerX += dx;
        centerY += dy;

        for(int i = 0; i < N; i++)
        {
            pX[i] += dx;
            pY[i] += dy;
        }
    }
    // ускоренное движение

    @Override
    public void moveX(double t) {

        if (this.isMoving()) {
            shift((this.getdX()) * t / 1000.0, 0);
        }
    }

    @Override
    public void moveY(double t) {
        if (this.isMoving()) {
            this.setdY(getdY()/1000.0);
            shift(0, (this.getdY() * t / 1000.0));

        }
    }
    public void moveXLeft(double t){
        setX(getX()+getdXY()* t / 100);}

    private double getdXY() {
        return dXY;
    }


    /**
     Равномерное движение со случайным направлением и скоростью, отскакивание от
     краев экрана
     */
    @Override
    public void move(double t) {

    	// Движение по горизонтали
        if( ((getX()+getWidth()) < Habitat.WIDTH) && (getX() > 0)) {
        	moveX(t); 
        }
        else {
        	// Смена вектора
            if( (getX()+getWidth()) >= Habitat.WIDTH )
                setX(Habitat.WIDTH -1 - getWidth());
            if(getX() <= 0)
                setX(1);
            setdX(-getdX());
        }
        
        // Движение по вертикали
        int dy = 55;	// Поправка на высоту меню
        if( ((getY()+getHeight()) < (Habitat.HEIGHT-dy)) && (getY() > 0)) {
            moveY(t);
        }
        else {
        	// Смена вектора
            if((getY()+getHeight()) >= (Habitat.HEIGHT-dy))
                setY(Habitat.HEIGHT -dy -1 -getHeight());
            if(getY() <= 0)
                setY(1);
            setdY(-getdY());
        }
    }


}
