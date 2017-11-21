package njust.edu.wuziqi.algrithm;

import java.util.ArrayList;
import java.util.List;

import njust.edu.wuziqi.FindBorder;
import njust.edu.wuziqi.Location;
import njust.edu.wuziqi.ui.MainFrame;
import njust.edu.wuziqi.util.ChessBoard;

public class ComputerLuozi {

	// 0��ʾ���壬1��ʾ���壬2��ʾ���壬3��ʾ����ĺ��壬4��ʾ����İ���

	int max = 10000;// ���̹�����ߵ÷�
	int min = -10000;// ���̹�����͵÷�

	int self;// ���Խ�ɫ
	MainFrame mainFrame = null;

	public ComputerLuozi(MainFrame mainFrame) {

		this.mainFrame = mainFrame;

	}

	/**
	 * ȷ������
	 * 
	 * @return ���ӵ�λ��
	 */
	public int[] luozi() {
		// TODO Auto-generated method stub

		self = mainFrame.getCompuColor();

		int x = 0, y = 0;
		int[] border = FindBorder.findBorder(1);// ��ʾ��ǰ�����������ڵķ���ı߽�

		int temp_x = -1, temp_y = -1, temp_score = -10000;

		List<Location> locations = new ArrayList<Location>();
		for (int i = border[0]; i <= border[1]; i++) {// �ռ������ӵ�λ���б�
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

			ChessBoard.chessBoard[x][y] = self; // ����������

			if (Evaluate.isWin(x, y, self)) {// �ж��Ƿ���Ӯ

				ChessBoard.chessBoard[x][y] = 0;
				temp_x = x;
				temp_y = y;
				break;

			}

			int score = depthAlphaBeta(4, alpha, beta, x, y, change(self));

			ChessBoard.chessBoard[x][y] = 0;// �����������

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
	 * �жϸ�λ��������3*3���Ƿ�Ϊ�տ�
	 * 
	 * @param x
	 * @param y
	 * @return ���ǿհ׷���true
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
	 * ת�������ɫ
	 * 
	 * @param character
	 *            ��һ������Ľ�ɫ
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
	 * ���õݹ����������ȱ���
	 * 
	 * @param depth
	 *            ���
	 * @param alpha
	 *            ��
	 * @param beta
	 *            ��
	 * @param coorx
	 *            ������
	 * @param coory
	 *            ������
	 * @param character
	 *            ��ǰ�����ɫ
	 * @return
	 */
	private int depthAlphaBeta(int depth, int alpha, int beta, int coorx,
			int coory, int character) {
		// TODO Auto-generated method stub
		int value = 0;
		if (depth == 1) {// �����������Ͳ�ֱ�ӹ�ֵ�����ع�ֵ
			value = Evaluate.getComputerValue(character, change(character));// ��ֵ
			return value;
		} else {
			int temp_x = -1, temp_y = -1, temp_score = -10000;// �м����
			int[] border = new int[4];
			border = FindBorder.findBorder(1);// ��ñ߽�

			List<Location> locations = new ArrayList<Location>();
			for (int i = border[0]; i <= border[1]; i++) {// �ռ������ӵ�λ���б�
				for (int j = border[2]; j <= border[3]; j++) {
					if (ChessBoard.chessBoard[i][j] == 0 && isnotBlock(i, j)) {
						Location location = new Location(i, j);
						locations.add(location);
					}

				}
			}

			for (Location location : locations) {// ��ÿ�������ӵ�λ��ֵ���б��������ݦ����㷨���м�֦

				temp_x = location.getX_site();
				temp_y = location.getY_site();

				ChessBoard.chessBoard[temp_x][temp_y] = character;// ����������

				temp_score = depthAlphaBeta(depth - 1, alpha, beta, temp_x,
						temp_y, change(character));

				ChessBoard.chessBoard[temp_x][temp_y] = 0;// ���������

				if (character == (self)) { // �����ǰ��ɫΪ���ԣ�ȷ������ֵ�������øõ��valueΪ��

					if (temp_score > alpha) {
						alpha = temp_score;
						value = alpha;
					}

					if (temp_score == 100)
						break;

				} else {// �����ǰ��ɫΪ��ң�ȷ���µ�ֵ�������øõ��valueΪ��

					if (temp_score < beta) {
						beta = temp_score;
						value = beta;
					}

					if (temp_score == -100)
						break;

				}

				if (alpha > beta)// �����ڦ£���֦
					break;
			}
		}
		return value;
	}
}