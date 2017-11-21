package njust.edu.wuziqi.util;

public class StatisticsQi {

	// private int hengCount = 0; //计数横着的指定颜色的棋子的数量
	// private int shuCount = 0; //竖着的指定颜色的棋子的数量
	// private int pieCount = 0; //撇方向指定颜色棋子的数量
	// private int naCount = 0; //捺方向指定颜色棋子的数量

	private int[] directionCount = new int[4];// 对每个方向的棋子的数量进行统计
	/*
	 * 用来判断改棋子所处的棋型是否为活棋
	 */
	// private boolean isHengLive = true;
	// private boolean isShuLive = true;
	// private boolean isPieLive = true;
	// private boolean isNaLive = true;
	//
	private boolean[] isDirectionLive = new boolean[4];

	// 判断该棋型是否为两头堵
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

	// 得到不同方向的棋子的数量
	private void getDirection(int direction) {
		// boolean flag = true; //两头是否为空格
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

	// 得到指定方向的棋子的数量
	public int getDirectionCount(int direction) {
		// getDirection(direction);
		return directionCount[direction];
	}

	// 查看指定方向的棋型是否两头为空
	public boolean isDirectionLive(int direction) {
		return isDirectionLive[direction];
	}

	// 判断该棋型是否一头被堵
	public boolean isDirectionDead(int direction) {
		return isDirectionDead[direction];
	}
}
