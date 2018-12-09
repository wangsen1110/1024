package com.wangsen.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
/**
 * Object类
 * @author 王森
 *
 */
public class GameAllObject {
	  Image  img;
	  double  x,y;
	   int   speed;
	   int  width, height;
	public  void  drawSelf(Graphics  g){
		g.drawImage(img, (int)x,(int) y, null);
	}

	public GameAllObject(Image img, double x, double y, int speed, int width, int height) {
		super();
		this.img = img;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.width = width;
		this.height = height;
	}

	public GameAllObject(Image img, double x, double y) {
		super();
		this.img = img;
		this.x = x;
		this.y = y;
	}
	
	public GameAllObject() {
	}
	
	/**
	 * 返回物体所在的矩形。便于后续的碰撞检测
	 * @return
	 */
	public  Rectangle   getRect(){
		return  new Rectangle((int)x, (int)y, width, height);
	}
}
      class Shell extends GameAllObject{
	  double  degree;
	  public  Shell(){
		x = 100;
		y = 100;
		width=10;
		height = 10;
		speed = 3;
		degree = Math.random()*Math.PI*2;
	}
	
	public  void   draw(Graphics  g){
		Color   c =  g.getColor();
		g.setColor(Color.red);
		
		g.fillOval((int)x,(int) y, width, height);
		
		//炮弹沿着任意角度去飞
		x += speed*Math.cos(degree);
		y += speed*Math.sin(degree);
		
		
		if(x<0||x>Constant.GAME_WIDTH-width){
			degree  = Math.PI - degree;
		}
		
		if(y<30||y>Constant.GAME_HEIGHT-height){
			degree  = - degree;
		}
		g.setColor(c);
		}
      }
	
	class Plane extends GameAllObject{
	boolean  left,up,right,down;
  	boolean  live = true;  
  	public  void  drawSelf(Graphics  g){
  		if(live){
  				g.drawImage(img, (int)x,(int) y, null);
  				if(left){
  					x -=speed;
  				}
  				if(right){
  					x += speed;
  				}
  				if(up){
  					y -=speed;    //y = y-speed;
  				}
  				if(down){
  					y += speed;
  			}
  		}else{	
  		}
  	}
  	
	public  Plane(Image  img, double i, double k){	
	  		this.img = img;
	  		this.x = i;
	  		this.y = k;
	  		this.speed = 7;
	  		this.width = img.getWidth(null);
	  		this.height = img.getHeight(null);

	  	}

	  	//按键，增加相应的方向
	  	public  void   addDirection(KeyEvent  e){
	  		switch (e.getKeyCode()) {
	  		case KeyEvent.VK_LEFT:
	  			left = true;
	  			break;
	  		case KeyEvent.VK_UP:
	  			up = true;
	  			break;
	  		case KeyEvent.VK_RIGHT:
	  			right = true;
	  			break;
	  		case KeyEvent.VK_DOWN:
	  			down = true;
	  			break;
	  		}
	  	}
	  	
	  	//按下某个键，取消相应的方向
	  		public  void   minusDirection(KeyEvent  e){
	  			switch (e.getKeyCode()) {
	  			case KeyEvent.VK_LEFT:
	  				left = false;
	  				break;
	  			case KeyEvent.VK_UP:
	  				up = false;
	  				break;
	  			case KeyEvent.VK_RIGHT:
	  				right = false;
	  				break;
	  			case KeyEvent.VK_DOWN:
	  				down = false;
	  				break;
	  			}
	  		}
	}
