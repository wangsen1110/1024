package gameball;
import java.awt.*;
import javax.swing.*;
import java.math.*;
/**
 * 
 * @author 王森
 *
 */
public class GameBall extends JFrame{
	
	Image ball = Toolkit.getDefaultToolkit().getImage("images/ball.png");//ball 大小40*40
	Image desk = Toolkit.getDefaultToolkit().getImage("images/desk.jpg");//desk 大小856*500
	
	double x = 100; //ball的横坐标
	double y = 100; //ball的纵坐标
	
	double degree = 3.14/3; //ball的角度
	//画画
	public void paint(Graphics g) {
	 System.out.println("画了一笔哦");
	 g.drawImage(desk,0,0,null);
	 g.drawImage(ball,(int)x,(int)y,null);
	 x = x + 20*Math.cos(degree);
	 y = y + 20*Math.sin(degree);
	 
	 if(x>856-40-30||x<40){
		 degree = 3.14-degree;
	}
	 if(y>500-40-30||y<40+30){
		 degree = -degree;
	 }
	}
	//窗口加载
	void launchFrame(){
	setSize(856,500);
	setLocation(100,100);
	setVisible(true);
	
	//永恒重复画
	while(true){
	repaint();
	try{
		Thread.sleep(30);
	}catch(Exception e){
		e.printStackTrace();
	    }
	  }
	}
	
	public static void main(String[] args){
	System.out.println("已启动，请系好安全带");
	GameBall game= new GameBall();
	game.launchFrame();
   }
}