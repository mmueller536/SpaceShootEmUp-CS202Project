import java.awt.Image;

public class Entity {
	protected int dx;
	protected int dy;
	protected int x;
	protected int y;
	protected int speed;
    protected int health;
    protected Image image;
    
    public int getHealth(){
    	return health;
    }
    public int X() {
        return x;
    }
    public int Y() {
        return y;
    }
    public int getSpeed(){
    	return speed;
    }
    public Image getImage() {
        return image;
    }
    public void damaged(int damage){
    	health -= damage;
    }
    public void move() {
        x += dx;
        y += dy;
    }
    public void setX(int setX){
    	x = setX;
    }
    public void setY(int setY){
    	y = setY;
    }
}
