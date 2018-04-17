import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player extends Entity {

	public Player() {
        ImageIcon I = new ImageIcon("Guy.png");
        image = I.getImage();
        x = 700;
        y = 400;
        speed = 4;
        health = 750;
    }
    public Image getImage() {
        return image;
    }
    public void regen(){
    	health++;
    }
    public void setSpeed(boolean slow){
    	if(slow){
    		speed = 1;
    	}
    }
    public void setSpeed(int spd){
    	speed = spd;
    }
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            dy = -speed;
        }
        if (key == KeyEvent.VK_A) {
            dx = -speed;
        }
        if (key == KeyEvent.VK_S) {
            dy = speed;
        }
        if (key == KeyEvent.VK_D) {
            dx = speed;
        }
    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            dy = -0;
        }
        if (key == KeyEvent.VK_A) {
            dx = -0;
        }
        if (key == KeyEvent.VK_S) {
            dy = 0;
        }
        if (key == KeyEvent.VK_D) {
            dx = 0;
        }
    }
}