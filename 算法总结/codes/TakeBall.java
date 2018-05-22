package algorithm.boyi;

/**
 * 博弈论的暴力枚举 
 * 今盒里有n个小球，A、B两人轮流从盒中取球。 每个人都可以看到另一个人取了多少个，也可以看到盒中还剩下多少个。
 * 两人都很聪明，不会做出错误的判断。
 * 每个人从盒子中取出的球的数目必须是：1，3，7或者8个。 轮到某一方取球时不能弃权！ A先取球，然后双方交替取球，直到取完。
 * 被迫拿到最后一个球的一方为负方（输方）
 * 编程确定出在双方都不判断失误的情况下，对于特定的初始球数，A是否能赢？
 * 
 * @author zxr
 *
 */
public class TakeBall {
	static int[] type = { 1, 3, 7, 8 };

	public static void main(String[] args) {
		for (int i = 1; i <= 50; i++) {
			System.out.println(f(i));
		}
	}

	public static boolean f(int n) {
		if (n == 0) {
			return true;
		}
		for (int i = 0; i < type.length; i++) {
			if (n >= type[i]) {
				boolean y = f(n - type[i]);
				if (!y) {
					return true;
				}
			}
		}
		return false;
	}
}
