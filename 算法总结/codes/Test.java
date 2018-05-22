package problems;
import java.math.BigDecimal;
import java.text.Bidi;
/**
 * 测试类，写算法过程中，一些不确定的东西可以用来测试
 * 
 * @author zxr
 *
 */
/*
 * JAVA中的无穷大和NaN的构造方法
 */
public class Test {
	public static double NaN = 0.0 / 0.0;
	public static double INF = 1.0 / 0.0;
	public static void main(String[] args) {
		String origin = "ABCDE";
		char[] o = origin.toCharArray();
		f(o,0);
		System.out.println(count);
	}

	static int count;
	public static void f(char[] o,int index) {
		if(index == o.length) {
			System.out.println(o);
			count++;
			return;
		}
		for(int i = index;i<o.length;i++) {
			char c = o[i];o[i] = o[index];o[index] = c;
			f(o,index+1);
			c = o[i];o[i] = o[index];o[index] = c;
		}
	}
}
