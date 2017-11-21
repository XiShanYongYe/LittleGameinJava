package njust.edu.wuziqi;

import njust.edu.wuziqi.util.ChessBoard;

public class FindBorder {

	/**
	 * 确定边界
	 * 
	 * @return 返回边界，border[0]表示上边界，border[1]表示下边界，border[2]表示左边界，border[3]表示右边界
	 */
	public static int[] findBorder(int n) {
		// TODO Auto-generated method stub
		int border_x0 = 0, border_x1 = 14, border_y0 = 0, border_y1 = 14;

		// 判断上边界
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
		// 判断下边界
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
		// 判断左边界
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
		// 判断右边界
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

		// 边界范围加1
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
