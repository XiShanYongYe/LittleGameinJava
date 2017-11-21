package njust.edu.wuziqi.algrithm;

import java.util.ArrayList;
import java.util.List;

import njust.edu.wuziqi.FindBorder;
import njust.edu.wuziqi.Location;
import njust.edu.wuziqi.ui.MainFrame;
import njust.edu.wuziqi.util.ChessBoard;

public class ComputerLuozi {

	// 0表示无棋，1表示黑棋，2表示白棋，3表示虚拟的黑棋，4表示虚拟的白棋

	int max = 10000;// 棋盘估分最高得分
	int min = -10000;// 棋盘估分最低得分

	int self;// 电脑角色
	MainFrame mainFrame = null;

	public ComputerLuozi(MainFrame mainFrame) {

		this.mainFrame = mainFrame;

	}

	/**
	 * 确定落子
	 * 
	 * @return 落子的位置
	 */
	public int[] luozi() {
		// TODO Auto-generated method stub

		self = mainFrame.getCompuColor();

		int x = 0, y = 0;
		int[] border = FindBorder.findBorder(1);// 表示当前棋盘中棋所在的方框的边界

		int temp_x = -1, temp_y = -1, temp_score = -10000;

		List<Location> locations = new ArrayList<Location>();
		for (int i = border[0]; i <= border[1]; i++) {// 收集可落子的位置列表
			for (int j = border[2]; j <= border[3]; j++) {

				if (ChessBoard.chessBoard[i][j] == 0 && isnotBlock(i, j)) {
					Location location = new Location(i, j);
					locations.add(location);
				}

			}
		}

		for (Location location : locations) {

			int alpha = min;
			int beta = max;

			x = location.getX_site();
			y = location.getY_site();

			ChessBoard.chessBoard[x][y] = self; // 下虚拟棋子

			if (Evaluate.isWin(x, y, self)) {// 判断是否已赢

				ChessBoard.chessBoard[x][y] = 0;
				temp_x = x;
				temp_y = y;
				break;

			}

			int score = depthAlphaBeta(4, alpha, beta, x, y, change(self));

			ChessBoard.chessBoard[x][y] = 0;// 清空虚拟棋子

			if (score > temp_score) {
				temp_x = x;
				temp_y = y;
				temp_score = score;
			}

			if (temp_score == 100)
				break;

		}

		int[] res = { temp_x, temp_y };

		return res;
	}

	/**
	 * 判断该位置所处的3*3框是否为空框
	 * 
	 * @param x
	 * @param y
	 * @return 若非空白返回true
	 */
	private boolean isnotBlock(int x, int y) {
		// TODO Auto-generated method stub
		for (int i = x - 1; i <= x + 1; i++)
			for (int j = y - 1; j <= y + 1; j++) {
				if (i < 0 || i > 14)
					break;
				if (j < 0 || j > 14)
					continue;
				if (ChessBoard.chessBoard[i][j] != 0)
					return true;
			}
		return false;
	}

	/**
	 * 转换下棋角色
	 * 
	 * @param character
	 *            上一次下棋的角色
	 * @return
	 */
	private int change(int character) {
		// TODO Auto-generated method stub
		if (character == 1)
			return character + 1;
		else
			return character - 1;
	}

	/**
	 * 利用递归进行深度优先遍历
	 * 
	 * @param depth
	 *            深度
	 * @param alpha
	 *            α
	 * @param beta
	 *            β
	 * @param coorx
	 *            横坐标
	 * @param coory
	 *            纵坐标
	 * @param character
	 *            当前下棋角色
	 * @return
	 */
	private int depthAlphaBeta(int depth, int alpha, int beta, int coorx,
			int coory, int character) {
		// TODO Auto-generated method stub
		int value = 0;
		if (depth == 1) {// 深度搜索到最低层直接估值并返回估值
			value = Evaluate.getComputerValue(character, change(character));// 估值
			return value;
		} else {
			int temp_x = -1, temp_y = -1, temp_score = -10000;// 中间变量
			int[] border = new int[4];
			border = FindBorder.findBorder(1);// 获得边界

			List<Location> locations = new ArrayList<Location>();
			for (int i = border[0]; i <= border[1]; i++) {// 收集可落子的位置列表
				for (int j = border[2]; j <= border[3]; j++) {
					if (ChessBoard.chessBoard[i][j] == 0 && isnotBlock(i, j)) {
						Location location = new Location(i, j);
						locations.add(location);
					}

				}
			}

			for (Location location : locations) {// 对每个可落子的位置值进行遍历，根据αβ算法进行剪枝

				temp_x = location.getX_site();
				temp_y = location.getY_site();

				ChessBoard.chessBoard[temp_x][temp_y] = character;// 下虚拟棋子

				temp_score = depthAlphaBeta(depth - 1, alpha, beta, temp_x,
						temp_y, change(character));

				ChessBoard.chessBoard[temp_x][temp_y] = 0;// 清空虚拟子

				if (character == (self)) { // 如果当前角色为电脑，确定α的值，并设置该点的value为α

					if (temp_score > alpha) {
						alpha = temp_score;
						value = alpha;
					}

					if (temp_score == 100)
						break;

				} else {// 如果当前角色为玩家，确定β的值，并设置该点的value为β

					if (temp_score < beta) {
						beta = temp_score;
						value = beta;
					}

					if (temp_score == -100)
						break;

				}

				if (alpha > beta)// α大于β，剪枝
					break;
			}
		}
		return value;
	}
}