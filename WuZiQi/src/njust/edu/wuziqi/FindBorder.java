package njust.edu.wuziqi;

import njust.edu.wuziqi.util.ChessBoard;

public class FindBorder {

	/**
	 * ȷ���߽�
	 * 
	 * @return ���ر߽磬border[0]��ʾ�ϱ߽磬border[1]��ʾ�±߽磬border[2]��ʾ��߽磬border[3]��ʾ�ұ߽�
	 */
	public static int[] findBorder(int n) {
		// TODO Auto-generated method stub
		int border_x0 = 0, border_x1 = 14, border_y0 = 0, border_y1 = 14;

		// �ж��ϱ߽�
		boolean flag = false;
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (ChessBoard.chessBoard[i][j] != 0) {
					flag = true;
					break;
				}
			}
			if (flag) {
				border_x0 = i;
				break;
			}
		}
		// �ж��±߽�
		flag = false;
		for (int i = 14; i >= 0; i--) {
			for (int j = 0; j < 15; j++) {
				if (ChessBoard.chessBoard[i][j] != 0) {
					flag = true;
					break;
				}
			}
			if (flag) {
				border_x1 = i;
				break;
			}
		}
		// �ж���߽�
		flag = false;
		for (int j = 0; j < 15; j++) {
			for (int i = 0; i < 15; i++) {
				if (ChessBoard.chessBoard[i][j] != 0) {
					flag = true;
					break;
				}
			}
			if (flag) {
				border_y0 = j;
				break;
			}
		}
		// �ж��ұ߽�
		flag = false;
		for (int j = 14; j >= 0; j--) {
			for (int i = 0; i < 15; i++) {
				if (ChessBoard.chessBoard[i][j] != 0) {
					flag = true;
					break;
				}
			}
			if (flag) {
				border_y1 = j;
				break;
			}
		}

		// �߽緶Χ��1
		border_x0 -= n;
		border_x1 += n;
		border_y0 -= n;
		border_y1 += n;
		if (border_x0 < 0)
			border_x0 = 0;
		if (border_x1 > 14)
			border_x1 = 14;
		if (border_y0 < 0)
			border_y0 = 0;
		if (border_y1 > 14)
			border_y1 = 14;

		int[] borders = new int[4];
		borders[0] = border_x0;
		borders[1] = border_x1;
		borders[2] = border_y0;
		borders[3] = border_y1;
		return borders;
	}

}
