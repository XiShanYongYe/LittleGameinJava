package njust.edu.wuziqi.ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/*
 * 用来生成黑棋和白棋   radit为半径    qiColor为1 黑棋  1  白棋2
 *
 * */
public class Circle extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int radit = 0;  //棋的半径
	int qiColor = 0;  //值为1，表示棋子的颜色为黑色，2表示白色
	public Circle(int radit,int qiColor){
		this.radit = radit;
		this.qiColor = qiColor;
		this.setOpaque(false);//设置面板为透明的
		
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
