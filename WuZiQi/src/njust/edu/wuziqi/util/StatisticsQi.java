package njust.edu.wuziqi.util;

public class StatisticsQi {

	// private int hengCount = 0; //�������ŵ�ָ����ɫ�����ӵ�����
	// private int shuCount = 0; //���ŵ�ָ����ɫ�����ӵ�����
	// private int pieCount = 0; //Ʋ����ָ����ɫ���ӵ�����
	// private int naCount = 0; //�෽��ָ����ɫ���ӵ�����

	private int[] directionCount = new int[4];// ��ÿ����������ӵ���������ͳ��
	/*
	 * �����жϸ����������������Ƿ�Ϊ����
	 */
	// private boolean isHengLive = true;
	// private boolean isShuLive = true;
	// private boolean isPieLive = true;
	// private boolean isNaLive = true;
	//
	private boolean[] isDirectionLive = new boolean[4];

	// �жϸ������Ƿ�Ϊ��ͷ��
	// private boolean isHengDead = false;
	// private boolean isShuDead = false;
	// private boolean isPieDead = false;
	// private boolean isNaDead = false;

	private boolean[] isDirectionDead = new boolean[4];

	private int[][] qiArray = null;
	private int coorx;
	private int coory;
	private int color;

	public StatisticsQi(int coorx, int coory, int color, int[][] qiArray) {
		this.coorx = coorx;
		this.coory = coory;
		this.color = color;
		this.qiArray = qiArray;
		for (int i = 0; i < 4; i++) {
			getDirection(i);
		}
	}

	// �õ���ͬ��������ӵ�����
	private void getDirection(int direction) {
		// boolean flag = true; //��ͷ�Ƿ�Ϊ�ո�
		boolean flag = false;
		int x = 0;
		int y = 0;
		for (int i = 0; i < 5; i++) {
			switch (direction) {
			case 0:
				x = coorx;
				y = coory - i;
				break;
			case 1:
				x = coorx - i;
				y = coory;
				break;
			case 2:
				x = coorx + i;
				y = coory - i;
				break;
			case 3:
				x = coorx - i;
				y = coory - i;
			}
			if (x >= 0 && x < 15 && y >= 0 && y <= 15)
				if (qiArray[x][y] == 0) {
					flag = true;
					break;
				} else if (qiArray[x][y] == color)
					directionCount[direction]++;
				else
					break;
		}
		for (int i = 1; i < 5; i++) {
			switch (direction) {
			case 0:
				x = coorx;
				y = coory + i;
				break;
			case 1:
				x = coorx + i;
				y = coory;
				break;
			case 2:
				x = coorx - i;
				y = coory + i;
				break;
			case 3:
				x = coorx + i;
				y = coory + i;
			}
			if (x >= 0 && x < 15 && y >= 0 && y < 15)
				if (qiArray[x][y] == 0) {
					if (flag == true) {
						isDirectionLive[direction] = true;
						break;
					} else
						isDirectionDead[direction] = true;
				} else if (qiArray[x][y] == color)
					directionCount[direction]++;
				else if (qiArray[x][y] == -color && flag == true) {
					isDirectionDead[direction] = true;
					break;
				} else
					break;
		}
	}

	// �õ�ָ����������ӵ�����
	public int getDirectionCount(int direction) {
		// getDirection(direction);
		return directionCount[direction];
	}

	// �鿴ָ������������Ƿ���ͷΪ��
	public boolean isDirectionLive(int direction) {
		return isDirectionLive[direction];
	}

	// �жϸ������Ƿ�һͷ����
	public boolean isDirectionDead(int direction) {
		return isDirectionDead[direction];
	}
}
