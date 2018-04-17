import java.awt.Image;
import javax.swing.ImageIcon;

public class Boss extends Entity {
    private Image quad1;
    private int quad1x = 1366-43+225;
    private int quad1y = -226;
    private Image quad2;
    private int quad2x = 0-300;
    private int quad2y = -226;
    private Image quad3;
    private int quad3x = 0-300;
    private int quad3y = 874;
    private Image quad4;
    private int quad4x = 1366-43+225;
    private int quad4y = 874;
    //Initializes and sets all of the variables in the case of the boss
    public Boss(){
    	ImageIcon I = new ImageIcon("Boss.png");
        image = I.getImage();
    	health = 500;
    	speed = 1;
    	x= 602;
    	y= 316;
    }
    //Method edits the x&y coords of boss based on player.
    public void move(int playerX, int playerY) {
    	if(playerX >= 645 && x > 245){
			dx= -speed;
		}else if(x < 1002){
			dx= speed;
		}else{
			dx = 0;
		}
		if(playerY >= 374 && y > 224){
			dy= -speed;
		}else if(y < 524){
			dy= speed;	
		}else{
			dy = 0;
		}
        x += dx;
        y += dy;
    }
    //Boss Animation for each of the Four Quadrants
    public Image getImageQuad1(){
        ImageIcon I = new ImageIcon("quad1.png");
        quad1 = I.getImage();
        return quad1;
    }
    public int getQuad1X(){
    	return quad1x;
    }
    public int getQuad1Y(){
    	return quad1y;
    }
    public void moveQuad1(){
    	if(Frame.bossTime > 300 && quad1y < 316){
			quad1y += speed;
		}
		if(Frame.bossTime > 300 && quad1x > 645){
			quad1x -= speed;
		}
    }
    public Image getImageQuad2(){
        ImageIcon I = new ImageIcon("quad2.png");
        quad2 = I.getImage();
        return quad2;
    }
    public int getQuad2X(){
    	return quad2x;
    }
    public int getQuad2Y(){
    	return quad2y;
    }
    public void moveQuad2(){
    	if(Frame.bossTime > 300 && quad2y < 316){
    		quad2y += speed;
    	}
    	if(Frame.bossTime > 300 && quad2x < 602){
    		quad2x += speed;
    	}
    }
    public Image getImageQuad3(){
        ImageIcon I = new ImageIcon("quad3.png");
        quad3 = I.getImage();
        return quad3;
    }
    public int getQuad3X(){
    	return quad3x;
    }
    public int getQuad3Y(){
    	return quad3y;
    }
    public void moveQuad3(){
    	if(Frame.bossTime > 300 && quad3y > 374){
			quad3y -= speed;
		}
		if(Frame.bossTime > 300 && quad3x < 602){
			quad3x += speed;
		}
    }
    public Image getImageQuad4(){
        ImageIcon I = new ImageIcon("quad4.png");
        quad4 = I.getImage();
        return quad4;
    }
    public int getQuad4X(){
    	return quad4x;
    }
    public int getQuad4Y(){
    	return quad4y;
    }
    public void moveQuad4(){
    	if(Frame.bossTime > 300 && quad4y > 374){
			quad4y -= speed;
		}
		if(Frame.bossTime > 300 && quad4x > 645){
			quad4x -= speed;
		}
    }
}