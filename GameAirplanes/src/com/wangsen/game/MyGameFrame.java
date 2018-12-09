package com.wangsen.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

/**
 *
 * @author ��ɭ
 *
 */
public class MyGameFrame  extends  Frame {
	
	Image   planeImg  = GameUtil.getImage("images/plane.png");
	Image   bg  = GameUtil.getImage("images/bg.jpg");
	
	Plane   plane = new Plane(planeImg,250,350);
	Shell[]   shells = new Shell[10];
	
	Explode   bao ;
	Date  startTime = new Date();
	Date  endTime;
	int period;   //��Ϸ������ʱ��
	
	@Override
	public void paint(Graphics g) {		//�Զ������á�  g�൱��һֻ����
		Color   c =  g.getColor();
		g.drawImage(bg, 0, 0, null);
		plane.drawSelf(g);  //���ɻ�

		//�������е��ڵ�
		for(int i=0;i<shells.length;i++){
			shells[i].draw(g);
			
			//��ײ���
			boolean  peng = shells[i].getRect().intersects(plane.getRect());
			if(peng){
				plane.live = false;
				if(bao ==null){
					bao  = new Explode(plane.x, plane.y);
					
					endTime = new Date();
					period = (int)((endTime.getTime()-startTime.getTime())/1000);
				}
				bao.draw(g);
				
			}
			
			//��ʱ������

			if(!plane.live){
				g.setColor(Color.green);
				Font   f  =  new Font("΢���ź�", Font.BOLD, 42);
				g.setFont(f);
				g.drawString("����ս��ʱ��Ϊ��"+period+"��", 30,200);
			}
			
		}
		
		g.setColor(c);
	}
	
	
	//���ڵ��ػ�
	class  PaintThread  extends  Thread  {
		@Override
		public void run() {
			while(true){
				repaint();		//�ػ�
				
				try {
					Thread.sleep(40);   	//1s=1000ms
				} catch (InterruptedException e) {
					e.printStackTrace();
				}		
			}
		}
		
	}
	
	//������̼������ڲ���
	class   KeyMonitor extends  KeyAdapter  {
		@Override
		public void keyPressed(KeyEvent e) {
			plane.addDirection(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			plane.minusDirection(e);
		}
		
		
	}
	
	
	/**
	 * ��ʼ������
	 */
	public  void  launchFrame(){
		this.setTitle("�������֣�����һ�¼���");
		this.setVisible(true);
		this.setSize(Constant.GAME_WIDTH	, Constant.GAME_HEIGHT);
		this.setLocation(300, 300);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		new PaintThread().start();	//�����ػ����ڵ��߳�
		addKeyListener(new KeyMonitor());   //���������Ӽ��̵ļ���
		
		
		//��ʼ��10���ڵ�
		for(int i=0;i<shells.length;i++){
			shells[i] = new Shell();
		}
		
	}
	
	public static void main(String[] args) {
		MyGameFrame  f = new MyGameFrame();
		f.launchFrame();
	}
	
	private Image offScreenImage = null;
	 
	public void update(Graphics g) {
	    if(offScreenImage == null)
	        offScreenImage = this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);//������Ϸ���ڵĿ�Ⱥ͸߶�
	     
	    Graphics gOff = offScreenImage.getGraphics();
	    paint(gOff);
	    g.drawImage(offScreenImage, 0, 0, null);
	}
	
}
