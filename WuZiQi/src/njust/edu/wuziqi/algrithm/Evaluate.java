package njust.edu.wuziqi.algrithm;

import njust.edu.wuziqi.FindBorder;
import njust.edu.wuziqi.util.ChessBoard;
import njust.edu.wuziqi.util.StatisticsQi;

/*
 * �Ե�ǰλ�ý�������
 * */
public class Evaluate {

	// private int [][]value = new int[15][15]; //������������ÿ��λ�õ�����
	private final static int FIRST_DEGREE = 100;// ����
	private final static int SECEND_DEGREE = 90; // ���ģ�˫���ģ����Ļ���
	private final static int THIRD_DEGREE = 80; // ˫����
	private final static int FOURTH_DEGREE = 70; // ��������
	private final static int FIVE_DEGREE = 60; // ����
	private final static int SIXTH_DEGREE = 50; // ����
	private final static int SEVENTH_DEGREE = 40; // ��˫��
	private final static int EIGHTH_DEGREE = 30; // ����
	private final static int NIGHTH_DEGREE = 20; // ���
	private final static int TENTH_DEGREE = 10; // ����

	// private final int ELEVENTH_DEGREE = 0; //����

	/*
	 * �Ե�ǰ��λ�ý�������
	 */
	private static int evaluate(int color, int coorx, int coory, int[][] qiArray) {
		int value = 0;
		StatisticsQi sq = new StatisticsQi(coorx, coory, color, qiArray);

		// �Բ�ͬ����ͬ���ͽ�������
		// ��������
		for (int i = 0; i < 4; i++) {
			if (isGetFive(i, sq)) {
				value += FIRST_DEGREE;
				return value;
			}
		}

		// ����
		for (int i = 0; i < 4; i++) {
			if (isLiveFour(i, sq)) {
				value += SECEND_DEGREE;
				return value;
			}
		}

		// ���Ļ���,˫����,����
		for (int i = 0; i < 4; i++) {
			if (isDeadFour(i, sq)) {
				for (int j = 0; j <= 3; j++) {
					if (j != i && (isLiveThree(j, sq) || isDeadFour(j, sq))) {
						value += SECEND_DEGREE; // ˫���� ���Ļ���
						return value;
					}
				}
				value += FIVE_DEGREE; // ����
				return value;
			}
		}

		// ˫���� ���� ��������
		for (int i = 0; i < 4; i++) {
			if (isLiveThree(i, sq)) {
				// ˫����
				for (int j = 0; j <= 3; j++) {
					if (j != i && isLiveThree(j, sq)) {
						value += THIRD_DEGREE;
						return value;
					}
				}
				// ��������
				for (int j = 0; j <= 3; j++) {
					if (j != i && isDeadThree(j, sq)) {
						value += FOURTH_DEGREE;
						return value;
					}
				}
				// ����
				value += SIXTH_DEGREE;
				return value;
			}
		}

		// ����
		for (int i = 0; i < 4; i++) {
			if (isDeadThree(i, sq)) {
				value += EIGHTH_DEGREE;
				return value;
			}
		}

		// ˫��� ���
		for (int i = 0; i < 4; i++) {
			if (isLiveTwo(i, sq)) {
				for (int j = 0; j < 4; j++) {
					if (j != i && isLiveTwo(j, sq)) {
						value += SEVENTH_DEGREE;// ˫���
						return value;
					}
				}
				value += NIGHTH_DEGREE;// ���
				return value;
			}
		}

		// ����
		for (int i = 0; i < 4; i++) {
			if (isDeadTwo(i, sq)) {
				value += TENTH_DEGREE;
				return value;
			}
		}
		// ����ֱ�ӷ���ԭʼvalue 0
		return value;
	}

	// �ж��������������Ƿ����
	private static boolean isGetFive(int direction, StatisticsQi sq) {
		if (sq.getDirectionCount(direction) >= 5)
			return true;
		return false;
	}

	// �ж��������������Ƿ�Ϊ����
	private static boolean isLiveFour(int direction, StatisticsQi sq) {
		if (sq.getDirectionCount(direction) == 4
				&& sq.isDirectionLive(direction))
			return true;
		return false;
	}

	// �ж�������������Ƿ�Ϊ����
	private static boolean isDeadFour(int direction, StatisticsQi sq) {
		if (sq.getDirectionCount(direction) == 4
				&& sq.isDirectionDead(direction))
			return true;
		return false;
	}

	// �ж�������������Ƿ�Ϊ����
	private static boolean isLiveThree(int direction, StatisticsQi sq) {
		if (sq.getDirectionCount(direction) == 3
				&& sq.isDirectionLive(direction))
			return true;
		return false;
	}

	// �ж�������������Ƿ�Ϊ����
	private static boolean isDeadThree(int direction, StatisticsQi sq) {
		if (sq.getDirectionCount(direction) == 3
				&& sq.isDirectionDead(direction))
			return true;
		return false;
	}

	// �ж�������������Ƿ�Ϊ���
	private static boolean isLiveTwo(int direction, StatisticsQi sq) {
		if (sq.getDirectionCount(direction) == 2
				&& sq.isDirectionLive(direction))
			return true;
		return false;
	}

	// �ж���������Ƿ�Ϊ����
	private static boolean isDeadTwo(int direction, StatisticsQi sq) {
		if (sq.getDirectionCount(direction) == 2
				&& sq.isDirectionDead(direction))
			return true;
		return false;
	}

	// �ж���������Ƿ�Ϊ����
	// private boolean isSingle(StatisticsQi sq){
	// boolean flag = true;
	// for(int i = 0;i<4;i++){
	// if(sq.getDirectionCount(i)>1)
	// flag = false;
	// }
	// return flag;
	// }

	/**
	 * ������ҵ�ǰ��ֵ�����ֵ
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
	 * ���ص��Ե�ǰ��ֵ�����ֵ
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
	 * ��õ��Ե�ǰλ�õ��ۺϹ�ֵ
	 * 
	 * @param compuColor
	 *            ���Ե���ɫ
	 * @param playerColor
	 *            ��ҵ���ɫ
	 * @return
	 */
	public static int getComputerValue(int compuColor, int playerColor) {
		int computerValue = getComputerMaxValue(compuColor);
		int playerValue = getPlayerMaxValue(playerColor);
		return computerValue + playerValue;
	}

	// �ж�������ɫΪcolor���Ƿ�ʤ��
	public static boolean isWin(int x, int y, int color) {
		if (evaluate(color, x, y, ChessBoard.chessBoard) == 100) {
			return true;
		}
		return false;
	}
}
