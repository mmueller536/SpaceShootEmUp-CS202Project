import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;


@SuppressWarnings({ "unused", "deprecation", "serial" })
public class Frame extends JFrame implements ActionListener{
	//Row, Column, and EnemyWaves are all pieces that handle the 2-D array of enemies.
	private static int row=60;
	private static int column=5;
	public static Enemy EnemyWaves[][] = new Enemy[row][column];
	//Variables for images that are related to the game board
	private Image YouWin;
	private Image background;
	private Image heath;
	private Image heathDetail;
	private Image GameOver;
	//Counters that are useful for timing
	private int waveCounter = 0;
	public static int bossTime = 0;
	//Arrays for bullets
	static Bullet ammo[] = new Bullet[50];
	static BossWeapon laser[] = new BossWeapon[50];
	//Boss and Player initialized
	private static Boss cheng = new Boss();
	static Player player = new Player();
	//JPanel for content and a Timer to listen for actions
	private Timer timer = new Timer(17, this);
	JPanel contentPane = new JPanel(){
		//Paints the screen and all entities and projectiles that are on it
	    public void paintComponent(Graphics g) {
	    	Graphics2D g2d =(Graphics2D)g;
	    	//Checks to see if boss is alive (Win condition)
	    	System.out.println(cheng.getHealth());
	    	if(cheng.getHealth() > 0){
		    	//Checks to see if player is alive (Loss condition)
	    		if (player.getHealth()>0){
	    			g2d.drawImage(background, 0, 0, this);
	    			//draws bullets
	    			for(int x = 0; x < ammo.length; x++){
	    				ammo[x].function(x);
	    				ammo[x].move();
	    				g2d.drawImage(ammo[x].getImage(), ammo[x].X(), ammo[x].Y(), this);
	    			}
	    			//Boss entrance animation
	    			if(bossTime > 2540 && bossTime < 3460){
	    				//Quad1
	    				g2d.drawImage(cheng.getImageQuad1(), cheng.getQuad1X(), cheng.getQuad1Y(), this);
	    				cheng.moveQuad1();
	    				//Quad2
	    				g2d.drawImage(cheng.getImageQuad2(), cheng.getQuad2X(), cheng.getQuad2Y(), this);
	    				cheng.moveQuad2();
	    				//Quad3
	    				g2d.drawImage(cheng.getImageQuad3(), cheng.getQuad3X(), cheng.getQuad3Y(), this);
	    				cheng.moveQuad3();
	    				//Quad4
	    				g2d.drawImage(cheng.getImageQuad4(), cheng.getQuad4X(), cheng.getQuad4Y(), this);
	    				cheng.moveQuad4();
	    			}
	    			//Handles Boss Spawning
	    			if(bossTime > 3461){
	    				g2d.drawImage(cheng.getImage(), cheng.X(), cheng.Y(), this);
	    				cheng.move(player.X(), player.Y());
	    				if(bossTime%150 == 0){
        					for(int b = 0; b < laser.length; b++){
        						laser[b].setBulletX(cheng.X()+54, player.X(), b, cheng.X());
    			        		laser[b].setBulletY(cheng.Y()+52, player.Y(), b, cheng.Y());
        					}
    					}
    					for(int b = 0; b < laser.length; b++){
    						g2d.drawImage(laser[b].getImage(), laser[b].X(), laser[b].Y(), this);
    			        	laser[b].function(b);
    			        	laser[b].move();
    					}
	    			}
	    			//HealthBar (Size is related to Player health
	    			if(!(player.getHealth()<=0))
	    				g2d.drawImage(heath, 400, 35, player.getHealth(), 36, this);
	    			g2d.drawImage(heathDetail, 400, 35, 750, 36, this);
	    			//Handles Wave Spawning
	    			for(int x=0; x<row; x++){
	    				for(int y=0; y<column; y++){
	    					if(EnemyWaves[x][y].getHealth() > 0){
	    						g2d.drawImage(EnemyWaves[x][y].getImage(), EnemyWaves[x][y].X(), EnemyWaves[x][y].Y(), this);
	    						CollisionDetection(x,y);
	    						EnemyWaves[x][y].move();
	    					}
	    					else{
	    						EnemyWaves[x][y].OBLIVION();
	    					}
	    				}
	    				if(WaveTracker(x)) break;
	    			}
	    			//Easter Egg
	    			if(bossTime > 100 && bossTime < 102){
	    				g2d.drawImage(laser[0].getImage(),300,30,40,40, this);
	    			}
	    			if(bossTime > 200 && bossTime < 202){
	    				g2d.drawImage(cheng.getImage(),100,30,40,40, this);
	    			}
	    			//Rotates the player based off of mouse position
	    			double angle = (Math.atan2(player.Y() - MyMouseListener.getCurrentY(), player.X() - MyMouseListener.getCurrentX()) - Math.PI / 2)+4.9;
	    			g2d.rotate(angle, player.X()+20, player.Y()+15);
	    			g2d.drawImage(player.getImage(), player.X()+10, player.Y()+5, this);
	    			//Regenerates Player Health
	    			if(player.getHealth()<=749)
	    				player.regen();
	    			//Counters are incremented by one
	    			bossTime++;
	    			waveCounter++;
	    			CollisionDetection();
	    		}
	    		//Game Over Screen is drawn
	    		else{
	    			g2d.drawImage(GameOver, 0, 0, 1366, 768, this);
	    		}
	    	//You Win Screen is drawn
	    	}else{
	    		g2d.drawImage(YouWin, 0, 0, 1366, 768, this);
	    	}
	    	//Handles the speed poisoning of green enemies
	    	if(waveCounter%500 == 0){
	    		player.setSpeed(4);
	    	}
	        Toolkit.getDefaultToolkit().sync();
	    }
	};
	private void CollisionDetection(int x, int y){    	
		//Detects Enemy Related Collision
		if ((EnemyWaves[x][y].Y() > player.Y() && EnemyWaves[x][y].X() > player.X()) && (EnemyWaves[x][y].Y() < player.Y()+18 && EnemyWaves[x][y].X() < player.X()+18)){
			player.damaged(EnemyWaves[x][y].damage());
			if(EnemyWaves[x][y].getSlow()){
				player.setSpeed(true);
			}
		}
		for(int bulletCount = 0; bulletCount < ammo.length; bulletCount++){
			if(EnemyWaves[x][y].X() < ammo[bulletCount].X() && EnemyWaves[x][y].X()+35 > ammo[bulletCount].X()){
				if(EnemyWaves[x][y].Y() < ammo[bulletCount].Y() && EnemyWaves[x][y].Y()+26 > ammo[bulletCount].Y()){
					EnemyWaves[x][y].damaged(ammo[bulletCount].getDamage());
				}
			}
		}
    }
    private void CollisionDetection(){
		//Detects Player and Boss Collisions
		for(int bulletCount = 0; bulletCount < ammo.length; bulletCount++){
			if((player.X() < laser[bulletCount].X() && player.X()+18 > laser[bulletCount].X()) && (player.Y() < laser[bulletCount].Y() && player.Y()+18 > laser[bulletCount].Y())){
				player.damaged(laser[bulletCount].getDamage());
			}
			if(bossTime > 3470 && (cheng.X() < ammo[bulletCount].X() && cheng.X()+86 > ammo[bulletCount].X()) && (cheng.Y() < ammo[bulletCount].Y() && cheng.Y()+116 > ammo[bulletCount].Y())){
				cheng.damaged(ammo[bulletCount].getDamage());
			}
		}
    }
	//*************************************************************************
	//Program Start
	//*************************************************************************
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//Creates the frame for the game
	public Frame()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(0, 0, 1366, 1000);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		TimerStart();
	}
	
	//this creates the listener for detecting keys
	public void TimerStart(){
		addKeyListener(new keyDetect());
		setFocusable(true);
		MyMouseListener listener = new MyMouseListener();
		addMouseListener(listener);
		addMouseMotionListener(listener);
        timer.start();
        enemyCreate();
    	loadImages();
	}
    @Override
    //When Keys are pressed, send data to player class
    public void actionPerformed(ActionEvent e) {
    	if(player.X() > 1215){
    		player.setX(1215);
    	}else if(player.X() < 90){
    		player.setX(90);
    	}
    	if(player.Y() > 680){
    		player.setY(680);
    	}else if(player.Y() < 104){
    		player.setY(104);
    	}

    	player.move();
        repaint();
    }
    //Initialize Enemies
  	public void enemyCreate(){
  		for (int x = 0; x < row; x++)
  		{
  			for(int y =0; y < column; y++){
  				try {
  				    Thread.sleep(17);
  				} catch(InterruptedException ex) {
  				    Thread.currentThread().interrupt();
  				}
  				EnemyWaves[x][y] = new Enemy();
  			}
  		}
  		for(int x = 0; x < ammo.length; x++){
  			try {
  			    Thread.sleep(1);
  			} catch(InterruptedException ex) {
  			    Thread.currentThread().interrupt();
  			}
  			laser[x] = new BossWeapon(cheng.X(), cheng.Y());
  			ammo[x] =new Bullet();
  		}
  	}
  	//Loads images for the Paint Method
  	private void loadImages(){
  		//Game Board's Map
  		ImageIcon I = new ImageIcon("MapUpdated.png");
      	background = I.getImage();
      	//HealthBar
      	ImageIcon h = new ImageIcon("HeathBar.png");
      	heath = h.getImage();
      	//Details (Lines) Around HealthBar
      	ImageIcon hd = new ImageIcon("heathbardetail.png");
      	heathDetail = hd.getImage();
      	//Game Over Screen
      	ImageIcon go = new ImageIcon("GameOverScreen.png");
      	GameOver = go.getImage();
      	//You Win Screen
      	ImageIcon yw = new ImageIcon("YouWin!!!.png");
      	YouWin = yw.getImage();
  	}
    //Key-detecting
    private class keyDetect extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
        	player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
        	player.keyPressed(e);
        }
    }
    //Listener for the mouse
    public static class MyMouseListener implements MouseListener, MouseMotionListener{

    	private static int currentX;
    	private static int currentY;
    	public int bulletCount = 0;

        public void mouseClicked(MouseEvent e) {
        	currentX = e.getX();
        	currentY = e.getY();
        	ammo[bulletCount].setBulletX(player.X()+10, currentX, bulletCount, player.X());
        	ammo[bulletCount].setBulletY(player.Y()+5, currentY, bulletCount, player.Y());
        	if(bulletCount<ammo.length-1){
        		bulletCount++;
        	} else bulletCount = 0;
        }

        public void mousePressed(MouseEvent e) {
        	currentX = e.getX();
        	currentY = e.getY();
        	ammo[bulletCount].setBulletX(player.X()+10, currentX, bulletCount, player.X());
        	ammo[bulletCount].setBulletY(player.Y()+5, currentY, bulletCount, player.Y());
        	if(bulletCount<ammo.length-1){
        		bulletCount++;
        	} else bulletCount = 0;
        }

        public void mouseReleased(MouseEvent e) {}

        public void mouseEntered(MouseEvent e) {}

        public void mouseExited(MouseEvent e) {}

        public void mouseDragged(MouseEvent e) {}

        public void mouseMoved(MouseEvent e) {
        	currentX = e.getX();
        	currentY = e.getY();
        }

        public static int getCurrentX(){
        	return currentX;
        }

        public static int getCurrentY(){
        	return currentY;
        }
    }
    //Keeps track of waves for the game
	public boolean WaveTracker(int x){
		if(waveCounter<100) return true;
		if(waveCounter<200&&x==1) return true;
		if(waveCounter<315&&x==2) return true;
		if(waveCounter<400&&x==3) return true;
		if(waveCounter<518&&x==4) return true;
		if(waveCounter<610&&x==5) return true;
		if(waveCounter<725&&x==6) return true;
		if(waveCounter<818&&x==7) return true;
		if(waveCounter<920&&x==8) return true;
		if(waveCounter<1020&&x==9) return true;
		if(waveCounter<1140&&x==10) return true;
		if(waveCounter<1240&&x==11) return true;
		if(waveCounter<1360&&x==12) return true;
		if(waveCounter<1480&&x==13) return true;
		if(waveCounter<1580&&x==14) return true;
		if(waveCounter<1670&&x==15) return true;
		if(waveCounter<1760&&x==16) return true;
		if(waveCounter<2200&&x==17) return true;
		if(waveCounter<2220&&x==18) return true;
		if(waveCounter<2240&&x==19) return true;
		if(waveCounter<2260&&x==20) return true;
		if(waveCounter<2280&&x==21) return true;
		if(waveCounter<2300&&x==22) return true;
		if(waveCounter<2320&&x==23) return true;
		if(waveCounter<2340&&x==24) return true;
		if(waveCounter<2360&&x==25) return true;
		if(waveCounter<2380&&x==26) return true;
		if(waveCounter<2400&&x==27) return true;
		if(waveCounter<2420&&x==28) return true;
		if(waveCounter<2440&&x==29) return true;
		if(waveCounter<2460&&x==30) return true;
		if(waveCounter<2480&&x==31) return true;
		if(waveCounter<2500&&x==32) return true;
		if(waveCounter<2520&&x==33) return true;
		if(waveCounter<2540&&x==34) return true;
		if(waveCounter<2560&&x==35) return true;
		if(waveCounter<2580&&x==36) return true;
		if(waveCounter<2600&&x==37) return true;
		if(waveCounter<3600&&x==38) return true;
		if(waveCounter<3610&&x==39) return true;
		if(waveCounter<3620&&x==40) return true;
		if(waveCounter<3800&&x==41) return true;
		if(waveCounter<3810&&x==42) return true;
		if(waveCounter<3820&&x==43) return true;
		if(waveCounter<4000&&x==44) return true;
		if(waveCounter<4010&&x==45) return true;
		if(waveCounter<4020&&x==46) return true;
		if(waveCounter<4200&&x==47) return true;
		if(waveCounter<4210&&x==48) return true;
		if(waveCounter<4220&&x==49) return true;
		if(waveCounter<4400&&x==50) return true;
		if(waveCounter<4410&&x==51) return true;
		if(waveCounter<4420&&x==52) return true;
		if(waveCounter<4600&&x==53) return true;
		if(waveCounter<4610&&x==54) return true;
		if(waveCounter<4620&&x==55) return true;
		if(waveCounter<4800&&x==56) return true;
		if(waveCounter<4810&&x==57) return true;
		if(waveCounter<4820&&x==58) return true;
		if(waveCounter<4830&&x==59) return true;
		else return false;
	}
}