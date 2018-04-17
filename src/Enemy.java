import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;

public class Enemy extends Entity {
	private int damage;
	private Random r = new Random((int) System.currentTimeMillis());
    private boolean slow = false;

	public Enemy (){
		int temp = r.nextInt((int) System.currentTimeMillis())%4;
		ImageIcon I = null;
		switch(temp){
		case 0:I = new ImageIcon("MonsterSmallTran1.png");
			health = 1;
			damage = 75;
			speed = 2;
			break;
		case 1:I = new ImageIcon("MonsterSmallTran2.png");
			health = 5;
			damage = 3;
			speed = 4;
			slow = true;
			break;
		case 2:I = new ImageIcon("MonsterSmallTran3.png");
			health = 7;
			damage = 7;
			speed = 3;
			break;
		case 3:I = new ImageIcon("MonsterSmallTran4.png");
			health = 3;
			damage = 7;
			speed = 4;
			break;
		}
		//health = 0;
        image = I.getImage();
		x = r.nextInt((int) System.currentTimeMillis())%1125+90;
		y = r.nextInt((int) System.currentTimeMillis())%576+104;
	}
	public int damage(){
		return damage;
	}
	public void move() {
		if(x < Frame.player.X()){
			x += speed;
		}else if(x > Frame.player.X()){
			x += -speed;
		}

		if(y < Frame.player.Y()){
			y += speed;
		}else if(y > Frame.player.Y()){
			y += -speed;
		}
    }
    public void OBLIVION(){
    	y = 0;
    	x = 0;
    }
    public boolean getSlow(){
    	return slow;
    }
}