package njust.edu.wuziqi.algrithm;

import njust.edu.wuziqi.FindBorder;
import njust.edu.wuziqi.util.ChessBoard;
import njust.edu.wuziqi.util.StatisticsQi;

/*
 * 对当前位置进行评估
 * */
public class Evaluate {

	// private int [][]value = new int[15][15]; //用以评估棋盘每个位置的评分
	private final static int FIRST_DEGREE = 100;// 成五
	private final static int SECEND_DEGREE = 90; // 活四，双死四，死四活三
	private final static int THIRD_DEGREE = 80; // 双活三
	private final static int FOURTH_DEGREE = 70; // 死三活三
	private final static int FIVE_DEGREE = 60; // 死四
	private final static int SIXTH_DEGREE = 50; // 活三
	private final static int SEVENTH_DEGREE = 40; // 活双二
	private final static int EIGHTH_DEGREE = 30; // 死三
	private final static int NIGHTH_DEGREE = 20; // 活二
	private final static int TENTH_DEGREE = 10; // 死二

	// private final int ELEVENTH_DEGREE = 0; //单子

	/*
	 * 对当前的位置进行评分
	 */
	private static int evaluate(int color, int coorx, int coory, int[][] qiArray) {
		int value = 0;
		StatisticsQi sq = new StatisticsQi(coorx, coory, color, qiArray);

		// 对不同方向不同棋型进行评分
		// 五子连成
		for (int i = 0; i < 4; i++) {
			if (isGetFive(i, sq)) {
				value += FIRST_DEGREE;
				return value;
			}
		}

		// 活四
		for (int i = 0; i < 4; i++) {
			if (isLiveFour(i, sq)) {
				value += SECEND_DEGREE;
				return value;
			}
		}

		// 死四活三,双死四,死四
		for (int i = 0; i < 4; i++) {
			if (isDeadFour(i, sq)) {
				for (int j = 0; j <= 3; j++) {
					if (j != i && (isLiveThree(j, sq) || isDeadFour(j, sq))) {
						value += SECEND_DEGREE; // 双死四 死四活三
						return value;
					}
				}
				value += FIVE_DEGREE; // 死四
				return value;
			}
		}

		// 双活三 活三 死三活三
		for (int i = 0; i < 4; i++) {
			if (isLiveThree(i, sq)) {
				// 双活三
				for (int j = 0; j <= 3; j++) {
					if (j != i && isLiveThree(j, sq)) {
						value += THIRD_DEGREE;
						return value;
					}
				}
				// 死三活三
				for (int j = 0; j <= 3; j++) {
					if (j != i && isDeadThree(j, sq)) {
						value += FOURTH_DEGREE;
						return value;
					}
				}
				// 活三
				value += SIXTH_DEGREE;
				return value;
			}
		}

		// 死三
		for (int i = 0; i < 4; i++) {
			if (isDeadThree(i, sq)) {
				value += EIGHTH_DEGREE;
				return value;
			}
		}

		// 双活二 活二
		for (int i = 0; i < 4; i++) {
			if (isLiveTwo(i, sq)) {
				for (int j = 0; j < 4; j++) {
					if (j != i && isLiveTwo(j, sq)) {
						value += SEVENTH_DEGREE;// 双活二
						return value;
					}
				}
				value += NIGHTH_DEGREE;// 活二
				return value;
			}
		}

		// 死二
		for (int i = 0; i < 4; i++) {
			if (isDeadTwo(i, sq)) {
				value += TENTH_DEGREE;
				return value;
			}
		}
		// 单子直接返回原始value 0
		return value;
	}

	// 判断这个方向的棋型是否成五
	private static boolean isGetFive(int direction, StatisticsQi sq) {
		if (sq.getDirectionCount(direction) >= 5)
			return true;
		return false;
	}

	// 判断这个方向的棋型是否为活四
	private static boolean isLiveFour(int direction, StatisticsQi sq) {
		if (sq.getDirectionCount(direction) == 4
				&& sq.isDirectionLive(direction))
			return true;
		return false;
	}

	// 判断这个方向棋型是否为死四
	private static boolean isDeadFour(int direction, StatisticsQi sq) {
		if (sq.getDirectionCount(direction) == 4
				&& sq.isDirectionDead(direction))
			return true;
		return false;
	}

	// 判断这个方向棋型是否为活三
	private static boolean isLiveThree(int direction, StatisticsQi sq) {
		if (sq.getDirectionCount(direction) == 3
				&& sq.isDirectionLive(direction))
			return true;
		return false;
	}

	// 判断这个方向棋型是否为死三
	private static boolean isDeadThree(int direction, StatisticsQi sq) {
		if (sq.getDirectionCount(direction) == 3
				&& sq.isDirectionDead(direction))
			return true;
		return false;
	}

	// 判断这个方向棋型是否为活二
	private static boolean isLiveTwo(int direction, StatisticsQi sq) {
		if (sq.getDirectionCount(direction) == 2
				&& sq.isDirectionLive(direction))
			return true;
		return false;
	}

	// 判断这个棋型是否为死二
	private static boolean isDeadTwo(int direction, StatisticsQi sq) {
		if (sq.getDirectionCount(direction) == 2
				&& sq.isDirectionDead(direction))
			return true;
		return false;
	}

	// 判断这个棋型是否为单子
	// private boolean isSingle(StatisticsQi sq){
	// boolean flag = true;
	// for(int i = 0;i<4;i++){
	// if(sq.getDirectionCount(i)>1)
	// flag = false;
	// }
	// return flag;
	// }

	/**
	 * 返回玩家当前棋局的最优值
	 * */
	private static int getPlayerMaxValue(int playerColor) {
		int border[] = FindBorder.findBorder(0);
		int maxValue = 0;
		for (int i = border[0]; i <= border[1]; i++)
			for (int j = border[2]; j <= border[3]; j++) {
				int value = evaluate(playerColor, i, j, ChessBoard.chessBoard);
				if (maxValue < value)
					maxValue = value;
			}
		return -maxValue;
	}

	/**
	 * 返回电脑当前棋局的最优值
	 * */
	private static int getComputerMaxValue(int compuColor) {
		int border[] = FindBorder.findBorder(0);
		int maxValue = 0;
		for (int i = border[0]; i <= border[1]; i++)
			for (int j = border[2]; j <= border[3]; j++) {
				int value = evaluate(compuColor, i, j, ChessBoard.chessBoard);
				if (maxValue < value)
					maxValue = value;
			}
		return maxValue;
	}

	/**
	 * 获得电脑当前位置的综合估值
	 * 
	 * @param compuColor
	 *            电脑的颜色
	 * @param playerColor
	 *            玩家的颜色
	 * @return
	 */
	public static int getComputerValue(int compuColor, int playerColor) {
		int computerValue = getComputerMaxValue(compuColor);
		int playerValue = getPlayerMaxValue(playerColor);
		return computerValue + playerValue;
	}

	// 判断棋子颜色为color的是否胜利
	public static boolean isWin(int x, int y, int color) {
		if (evaluate(color, x, y, ChessBoard.chessBoard) == 100) {
			return true;
		}
		return false;
	}
}
