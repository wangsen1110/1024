package gameball;
import java.awt.*;
import javax.swing.*;
import java.math.*;
/**
 * 
 * @author ��ɭ
 *
 */
public class GameBall extends JFrame{
	
	Image ball = Toolkit.getDefaultToolkit().getImage("images/ball.png");//ball ��С40*40
	Image desk = Toolkit.getDefaultToolkit().getImage("images/desk.jpg");//desk ��С856*500
	
	double x = 100; //ball�ĺ�����
	double y = 100; //ball��������
	
	double degree = 3.14/3; //ball�ĽǶ�
	//����
	public void paint(Graphics g) {
	 System.out.println("����һ��Ŷ");
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
	//���ڼ���
	void launchFrame(){
	setSize(856,500);
	setLocation(100,100);
	setVisible(true);
	
	//�����ظ���
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
	System.out.println("����������ϵ�ð�ȫ��");
	GameBall game= new GameBall();
	game.launchFrame();
   }
}