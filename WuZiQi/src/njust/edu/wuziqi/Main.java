package njust.edu.wuziqi;

import javax.swing.JOptionPane;

import njust.edu.wuziqi.algrithm.ComputerLuozi;
import njust.edu.wuziqi.algrithm.Evaluate;
import njust.edu.wuziqi.ui.MainFrame;
import njust.edu.wuziqi.util.ChessBoard;

public class Main {
	public static void main(String[] args) {

		MainFrame mainFrame = new MainFrame();

		ComputerLuozi luozi = new ComputerLuozi(mainFrame);
		while (true) {
			try {
				Thread.sleep(100);
				if (MainFrame.runFlag) {
					if (MainFrame.listenFlag == false) {// ��������
						int[] position = luozi.luozi();
						mainFrame.paintQi(mainFrame.getCompuColor(),
								position[0] * 35, position[1] * 35);// ��ͼ
						MainFrame.computerCount++;
						System.out.println("���Ե� " + MainFrame.computerCount
								+ " ����" + position[0] + "   " + position[1]);
						mainFrame.setLabelName("���");
						ChessBoard.chessBoard[position[0]][position[1]] = mainFrame
								.getCompuColor();// ���������¸���

						if (Evaluate.isWin(position[0], position[1],
								mainFrame.getCompuColor())) {// �жϵ���ʤ��

							JOptionPane.showMessageDialog(null, "����ʤ��!");
							MainFrame.runFlag = false;
							break;

						}

						MainFrame.listenFlag = true;
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}