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
					if (MainFrame.listenFlag == false) {// 电脑落子
						int[] position = luozi.luozi();
						mainFrame.paintQi(mainFrame.getCompuColor(),
								position[0] * 35, position[1] * 35);// 画图
						MainFrame.computerCount++;
						System.out.println("电脑第 " + MainFrame.computerCount
								+ " 步：" + position[0] + "   " + position[1]);
						mainFrame.setLabelName("玩家");
						ChessBoard.chessBoard[position[0]][position[1]] = mainFrame
								.getCompuColor();// 在棋盘上下该棋

						if (Evaluate.isWin(position[0], position[1],
								mainFrame.getCompuColor())) {// 判断电脑胜利

							JOptionPane.showMessageDialog(null, "电脑胜利!");
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