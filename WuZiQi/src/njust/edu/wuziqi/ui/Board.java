package njust.edu.wuziqi.ui;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

/*
 * 用来画五子棋棋盘的连线
 * */
public class Board extends JPanel {
	// private Line2D line = null; //线

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Board() {
		paneInit();
		this.setLayout(null);// 设置为绝对布局
	}

	private void paneInit() {
		// this.setBackground(Color.yellow);
		this.setOpaque(false);
	}

	// 这个方法程序自动调用
	public void paint(Graphics g) {
		super.paint(g);// 调用父类paint方法
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_SQUARE,
				BasicStroke.JOIN_ROUND));
		for (int i = 0; i < 525; i = i + 35) {
			g2d.draw(new Line2D.Double(i, 0, i, 490));

		}
		for (int j = 0; j < 525; j = j + 35) {
			// g.drawLine(0, j, 490, j);
			g2d.draw(new Line2D.Double(0, j, 490, j));
		}
	}

}
