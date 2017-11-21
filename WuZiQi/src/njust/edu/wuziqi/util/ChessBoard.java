package njust.edu.wuziqi.util;

public class ChessBoard {

	public static int[][] chessBoard = new int[15][15];

	public static void initChessBoard() {

		for (int i = 0; i < 15; i++)
			for (int j = 0; j < 15; j++)
				chessBoard[i][j] = 0;

	}
}
