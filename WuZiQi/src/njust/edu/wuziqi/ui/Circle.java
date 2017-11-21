package njust.edu.wuziqi.ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/*
 * �������ɺ���Ͱ���   raditΪ�뾶    qiColorΪ1 ����  1  ����2
 *
 * */
public class Circle extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int radit = 0;  //��İ뾶
	int qiColor = 0;  //ֵΪ1����ʾ���ӵ���ɫΪ��ɫ��2��ʾ��ɫ
	public Circle(int radit,int qiColor){
		this.radit = radit;
		this.qiColor = qiColor;
		this.setOpaque(false);//�������Ϊ͸����
		
	}
	
	public void paint(Graphics g){
		super.paint(g);
		if(qiColor == 1)
			g.setColor(Color.black);
		else if(qiColor == 2)
			g.setColor(Color.white);
		g.fillOval(0, 0, radit*2, radit*2);
	}
}
