package algorithm.random;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Random;
import java.util.Stack;

/**
 * 24点计算,给定4个数字求解是否有可能经过四则运算得到24点
 * 
 * @author zxr
 *
 */
public class CalCu24 {
	public static void main(String[] args) {
		int[] origin = new int[] {3,4,5,6};
		f(origin);
	}

	/**
	 * 试着计算
	 */
	static Random r = new Random();

	public static void f(int[] origin) {
		int[] buff = new int[7];
		for (int i = 0; i < 4; i++) {
			buff[i] = origin[i];
		}
		int N1 = 10000 * 1000; // 实验次数
		int N2 = 5; // 每种运算符号使用多少次
		for (int i = 0; i < N1; i++) {
			randomSwap(buff);
			generateOpr(buff);
			for (int j = 0; j < N2; j++) {
				swapOpr(buff);
				// 计算开始
				try {
					calculate(buff);
				} catch (EmptyStackException e) {
					continue;
				} catch (Exception e) {
					return;
				}
			}
		}
		System.out.println("don't find a answer");
	}

	private static void calculate(int[] buff) throws EmptyStackException, Exception {
		Stack<Integer> s = new Stack<Integer>();
		for (int i = 0; i < 4; i++) {
			s.push(buff[i]);
		}

		for (int i = 4; i < 7; i++) {
			switch ((int) buff[i]) {
			case 0:
				// +
				s.push(s.pop() + s.pop());
				break;
			case 1:
				// -
				s.push(s.pop() - s.pop());
				break;
			case 2:
				// *
				s.push(s.pop() * s.pop());
				break;
			case 3:
				// /
				int m1 = s.pop();
				int m2 = s.pop();
				if (m2 == 0 || m1 % m2 != 0) {
					return;
				}
				s.push(s.pop() / s.pop());
				break;
			default:
				break;
			}
		}
		if (s.pop() == 24) {
			show(buff);
			throw new Exception("find a answeer");
		}
	}

	private static void show(int[] buff) {
		Stack<String> s = new Stack<>();
		for (int i = 0; i < 4; i++) {
			s.push(buff[i]+"");
		}

		for (int i = 4; i < 7; i++) {
			switch ((int) buff[i]) {
			case 0:
				// +
				s.push("("+s.pop() +"+"+ s.pop()+")");
				break;
			case 1:
				// -
				s.push("("+s.pop() +"-" + s.pop()+")");
				break;
			case 2:
				// *
				s.push("("+s.pop()+ "*"+ s.pop()+")");
				break;
			case 3:
				// /
				s.push("("+s.pop() +"/" +s.pop()+")");
				break;
			default:
				break;
			}
		}
		System.out.println(s.pop());
	}

	private static void generateOpr(int[] buff) {
		for (int i = 4; i < 7; i++) {
			buff[i] = r.nextInt(4);
		}
	}

	public static void randomSwap(int[] array) {
		int i1 = r.nextInt(4);
		int i2 = r.nextInt(4);
		int temp = array[i1];
		array[i1] = array[i2];
		array[i2] = temp;
	}

	public static void swapOpr(int[] array) {
		int i1 = r.nextInt(3) + 4;
		int i2 = r.nextInt(3) + 4;
		int temp = array[i1];
		array[i1] = array[i2];
		array[i2] = temp;
	}
}
