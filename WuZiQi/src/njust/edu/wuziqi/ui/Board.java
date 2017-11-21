package njust.edu.wuziqi.ui;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

/*
 * ���������������̵�����
 * */
public class Board extends JPanel {
	// private Line2D line = null; //��

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Board() {
		paneInit();
		this.setLayout(null);// ����Ϊ���Բ���
	}

	private void paneInit() {
		// this.setBackground(Color.yellow);
		this.setOpaque(false);
	}

	// ������������Զ�����
	public void paint(Graphics g) {
		super.paint(g);// ���ø���paint����
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
