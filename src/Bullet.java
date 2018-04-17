import java.awt.Image;

import javax.swing.ImageIcon;

public class Bullet extends Projectile{
    
	public Bullet(){
		ImageIcon I = new ImageIcon("BulletSmallTran.png");
        image = I.getImage();
		x = -50;
		y = -50;
		damage=1;
	}
	
	public void function(int number){
		float rad = (float)(Math.atan2(mx[number]-px[number], my[number]-py[number]));
		dx = ((float)Math.sin(rad)*speed);
		dy = ((float)Math.cos(rad)*speed);
	}

	public void setBulletX(float f, int mouseX, int bulletCount, int playerx){
		px[bulletCount] = playerx;
		mx[bulletCount] = mouseX;
		this.x = (int)f;
	}

	public void setBulletY(float x, int mouseY, int bulletCount, int playery){
		py[bulletCount] = playery;
		my[bulletCount] = mouseY;
		this.y = (int)x;
	}
}