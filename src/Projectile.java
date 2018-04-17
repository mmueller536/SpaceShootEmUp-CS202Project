import java.awt.Image;

public class Projectile {
	protected int x;
	protected int y;
	protected float dx = 0;
	protected float dy = 0;
	protected int speed = 10;
	protected int damage;
    protected Image image;
    protected int px[] = new int[50];
    protected int py[] = new int[50];
    protected int mx[] = new int[50];
    protected int my[] = new int[50];
    
    public void move(){
		x += dx;
		y += dy;
	}
    
	public int getDamage(){
    	return damage;
    }

	public Image getImage() {
        return image;
    }

	public int X(){
		return x;
	}

	public int Y(){
		return y;
	}
}
