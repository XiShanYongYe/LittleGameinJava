package njust.edu.wuziqi.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import njust.edu.wuziqi.algrithm.Evaluate;
import njust.edu.wuziqi.util.ChessBoard;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static int playerCount = 0; // 玩家第几步
	public static int computerCount = 0; // 电脑第几步

	private JPanel mainPane = null; // frame的主面板

	private JPanel leftPane = null; //
	private JLabel characterLabel = null;
	private JButton reSet = null;

	private Board board = null; // 棋盘

	private JPanel rightPane = null; // 位于主面板的右边
	private JLabel titleLabel = null; // 显示五子棋名称
	private JLabel computer = null; // 显示电脑
	private Circle showComputer = null; // 显示电脑执棋颜色
	private JLabel player = null; // 显示玩家
	private Circle showPlayer = null;// 显示玩家执棋颜色

	private JButton compuFirst = null; // 电脑先手button
	private JButton playerFirst = null; // 玩家先后button

	public static boolean listenFlag = true; // 用以标记执子方是电脑还是玩家 true 玩家 false电脑
	public static boolean runFlag = true;
	// public static boolean resetFlag = true; //ture表示重置
	public int playerColor; // 玩家执子颜色
	private int compuColor; // 电脑执子颜色
	public MouseAdapter mouseAdapter;// 棋盘的鼠标监听

	public MainFrame() {

		mainPaneInit();
		leftPaneInit();
		rightPaneInit();
		setBackground();
		buttonActionPerformed();
		initFrame();

	}

	private void initFrame() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(1000, 600);
		this.setVisible(true);
		this.setTitle("五子棋");
		this.setIconImage(new ImageIcon("image/icon.png").getImage());// 窗口图标

		setLocationRelativeTo(null);
	}

	/*
	 * 背景初始化
	 */
	private void setBackground() {
		ImageIcon icon = new ImageIcon("image/background.jpg");
		icon.setImage(icon.getImage().getScaledInstance(1000, 600,
				Image.SCALE_DEFAULT));// 按比例拉伸图片
		JLabel background = new JLabel(icon);
		mainPane.add(background);
	}

	/*
	 * 主面板初始化
	 */
	private void mainPaneInit() {

		mainPane = (JPanel) this.getContentPane();

		// 加入棋盘
		board = new Board();
		board.setBounds(150, 40, 500, 500);
		mainPane.add(board);

		// 初始化leftPane

		leftPane = new JPanel();
		leftPane.setBounds(35, 40, 115, 500);
		leftPane.setOpaque(false);
		leftPane.setLayout(null);
		mainPane.add(leftPane);

		// 初始化rightPane
		rightPane = new JPanel();
		rightPane.setBounds(600, 40, 350, 500);
		rightPane.setOpaque(false);
		rightPane.setLayout(null);
		mainPane.add(rightPane);
	}

	/*
	 * leftPane初始化
	 */
	private void leftPaneInit() {
		characterLabel = new JLabel("执子方");
		characterLabel.setFont(new Font("华文行楷", Font.BOLD, 25));
		characterLabel.setForeground(new Color(85, 107, 47));
		characterLabel.setBounds(10, 100, 80, 30);
		leftPane.add(characterLabel);

		characterLabel = new JLabel("电脑");
		characterLabel.setFont(new Font("华文行楷", Font.BOLD, 25));
		characterLabel.setForeground(new Color(85, 107, 47));
		characterLabel.setBounds(20, 200, 80, 30);
		leftPane.add(characterLabel);

		reSet = new JButton("重置");
		reSet.setFont(new Font("华文行楷", Font.BOLD, 25));
		reSet.setForeground(new Color(85, 107, 47));
		reSet.setBounds(20, 400, 90, 50);
		reSet.setContentAreaFilled(false);
		leftPane.add(reSet);

	}

	/*
	 * rightPane初始化
	 */
	private void rightPaneInit() {

		titleLabel = new JLabel("五子棋");
		titleLabel.setFont(new Font("华文行楷", Font.BOLD, 33));
		titleLabel.setForeground(new Color(85, 107, 47));
		titleLabel.setBounds(150, 10, 150, 80);
		rightPane.add(titleLabel);

		computer = new JLabel("电脑");
		computer.setFont(new Font("华文行楷", Font.BOLD, 31));
		computer.setForeground(new Color(85, 107, 47));
		computer.setBounds(80, 100, 100, 80);
		rightPane.add(computer);

		showComputer = new Circle(20, 2);
		showComputer.setBounds(200, 120, 80, 80);
		rightPane.add(showComputer);

		player = new JLabel("玩家");
		player.setFont(new Font("华文行楷", Font.BOLD, 31));
		player.setForeground(new Color(85, 107, 47));
		player.setBounds(80, 200, 100, 80);
		rightPane.add(player);

		showPlayer = new Circle(20, 1);
		showPlayer.setBounds(200, 220, 80, 80);
		rightPane.add(showPlayer);

		compuFirst = new JButton("电脑先手");
		compuFirst.setFont(new Font("华文行楷", Font.BOLD, 30));
		compuFirst.setForeground(new Color(85, 107, 47));
		compuFirst.setBounds(100, 300, 180, 50);
		compuFirst.setContentAreaFilled(false);
		rightPane.add(compuFirst);

		playerFirst = new JButton("玩家先手");
		playerFirst.setFont(new Font("华文行楷", Font.BOLD, 30));
		playerFirst.setForeground(new Color(85, 107, 47));
		playerFirst.setBounds(100, 400, 180, 50);
		playerFirst.setContentAreaFilled(false);
		rightPane.add(playerFirst);

	}

	/*
	 * 得到电脑执子颜色
	 */
	public int getCompuColor() {
		return compuColor;
	}

	/**
	 * 设置characterLabel 的名称
	 * */
	public void setLabelName(String name) {
		characterLabel.setText(name);
	}

	/**
	 * 设置落子位置为35的整数倍
	 * */
	private int getInt(int coor) {
		int m = coor % 35;
		if (m > 17)
			coor = (coor / 35 + 1) * 35;
		else
			coor = (coor / 35) * 35;
		return coor;
	}

	/**
	 * 在棋盘上画棋
	 * */
	public boolean paintQi(int color, int x, int y) {
		// if (color == playerColor) {
		// playerCount++;
		// System.out.println("玩家第 "+playerCount+" 步："+x/35+"   "+y/35);
		// }
		// if(color == compuColor){
		// computerCount++;
		// System.out.println("电脑第 "+computerCount+" 步："+x/35+"   "+y/35);
		// }
		ChessBoard.chessBoard[x / 35][y / 35] = color; // 存储进棋盘数组
		Circle c = new Circle(15, color);
		c.setBounds(x - 15, y - 15, 30, 30); // 设置棋的大小
		board.add(c);
		board.repaint();
		board.validate();

		return true;
	}

	/*
	 * 添加按钮监听事件
	 */
	private void buttonActionPerformed() {

		// 鼠标监听事件
		mouseAdapter = new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (runFlag) {

					if (listenFlag == true) {

						int coorX = e.getX();
						int coorY = e.getY();
						int x = getInt(coorX);
						int y = getInt(coorY);
						if (ChessBoard.chessBoard[x / 35][y / 35] == 0) {
							// 玩家落子
							paintQi(playerColor, x, y);
							playerCount++;
							System.out.println("玩家第 " + playerCount + " 步：" + x
									/ 35 + "   " + y / 35);
							ChessBoard.chessBoard[x / 35][y / 35] = playerColor;
							characterLabel.setText("电脑");
							if (Evaluate.isWin(x / 35, y / 35, playerColor)) {// 判断玩家胜利

								runFlag = false;
								JOptionPane.showMessageDialog(null, "恭喜玩家胜利！");
							}

							listenFlag = false;

						}

					}

				}

			}

		};

		compuFirst.addActionListener(new ActionListener() {
			// 改变棋子颜色
			private void changeColor() {
				rightPane.remove(showComputer);
				showComputer = new Circle(20, 1);
				showComputer.setBounds(200, 120, 80, 80);
				rightPane.add(showComputer);
				// rightPane.validate();
				rightPane.repaint();

				rightPane.remove(showPlayer);
				showPlayer = new Circle(20, 2);
				showPlayer.setBounds(200, 220, 80, 80);
				rightPane.add(showPlayer);
				// rightPane.validate();
				rightPane.repaint();
				compuFirst.setEnabled(false);
				playerFirst.setEnabled(false);
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				compuColor = 1;
				playerColor = 2;
				listenFlag = false;
				changeColor();
				// 给棋盘添加鼠标监听事件
				board.addMouseListener(mouseAdapter);
				paintQi(compuColor, 7 * 35, 7 * 35);
				computerCount++;
				System.out.println("电脑第 " + computerCount + " 步： 7		7");
				ChessBoard.chessBoard[7][7] = compuColor;
				listenFlag = true;
				runFlag = true;
				// resetFlag = true;
				// characterLabel.setText("电脑");
			}
		});

		playerFirst.addActionListener(new ActionListener() {
			// 改变棋子颜色
			private void changeColor() {
				rightPane.remove(showComputer);
				showComputer = new Circle(20, 2);
				showComputer.setBounds(200, 120, 80, 80);
				rightPane.add(showComputer);
				rightPane.validate();
				rightPane.repaint();

				rightPane.remove(showPlayer);
				showPlayer = new Circle(20, 1);
				showPlayer.setBounds(200, 220, 80, 80);
				rightPane.add(showPlayer);
				rightPane.validate();
				rightPane.repaint();
				playerFirst.setEnabled(false);
				compuFirst.setEnabled(false);
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				listenFlag = true;
				// resetFlag = true;
				runFlag = true;
				playerColor = 1;
				compuColor = 2;
				changeColor();
				// 给棋盘添加鼠标监听事件
				board.addMouseListener(mouseAdapter);
				characterLabel.setText("玩家");
			}
		});

		reSet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int choice = JOptionPane.showConfirmDialog(null, "重新开始游戏？",
						"警告", JOptionPane.YES_NO_OPTION);
				if (choice == 0) {
					runFlag = true;
					listenFlag = true;
					// resetFlag = false;
					ChessBoard.initChessBoard();
					playerCount = 0;
					computerCount = 0;
					board.removeAll();
					board.repaint();
					board.validate();

					compuFirst.setEnabled(true);
					playerFirst.setEnabled(true);
				}
			}
		});

	}
}