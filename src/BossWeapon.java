import java.awt.Image;

import javax.swing.ImageIcon;

public class BossWeapon extends Projectile{
	
	public BossWeapon(int bossx, int bossy){
		ImageIcon I = new ImageIcon("BossWep.png");
        image = I.getImage();
		this.x = bossx;
		this.y = bossy;
		damage=800;
	}
	
	public void function(int number){
		float rad = (float)(Math.atan2(px[number]-mx[number], py[number]-my[number]));
		dx = ((float)Math.sin(rad)*speed);
		dy = ((float)Math.cos(rad)*speed);
	}
	
	public void setBulletX(float f, int playerX, int bulletCount, int bossx){
		mx[bulletCount] = bossx;
		px[bulletCount] = playerX;
		this.x = (int)f;
	}

	public void setBulletY(float x, int playerY, int bulletCount, int bossy){
		my[bulletCount] = bossy;
		py[bulletCount] = playerY;
		this.y = (int)x;
	}
}