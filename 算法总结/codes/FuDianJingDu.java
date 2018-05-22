package algorithm.math;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
/**
 * 精度问题
 * 
 * @author zxr
 *
 */
public class FuDianJingDu {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ra sum = new Ra(BigInteger.ZERO);
		for(int i = 2;i<=100;i++) {
			sum=sum.add(new Ra(BigInteger.ONE,new BigInteger(i+"")));
		}
		System.out.println(sum);
	}



	static class Ra {
		BigInteger[] num = { BigInteger.ZERO, BigInteger.ONE };

		/**
		 * 构造函数
		 */
		public Ra() {
		}

		public Ra(BigInteger numerator, BigInteger denominator) {
			BigInteger c = gcd(numerator, denominator);
			num[0] = numerator.divide(c);
			num[1] = denominator.divide(c);
		}

		public Ra(BigInteger numerator) {
			this(numerator, BigInteger.ONE);
		}

		/**
		 * 求解最大公约数
		 * 
		 * @param a
		 * @param b
		 * @return
		 */
		public BigInteger gcd(BigInteger a, BigInteger b) {
			if (b.equals(BigInteger.ZERO)) {
				return a;
			}
			return gcd(b, a.remainder(b));
		}

		/**
		 * 四则运算
		 */
		public Ra add(Ra b) {
			BigInteger numerator = this.num[0].multiply(b.num[1]).add(
					this.num[1].multiply(b.num[0]));
			BigInteger denominator = this.num[1].multiply(b.num[1]);
			return new Ra(numerator, denominator);
		}

		public Ra subtract(Ra b) {
			return add(negate(b));
		}

		public Ra multiply(Ra b) {
			return new Ra(this.num[0].multiply(b.num[0]),
					this.num[1].multiply(b.num[1]));
		}

		public Ra divide(Ra b) throws Exception {
			if (b.equals(BigInteger.ZERO)) {
				throw new Exception("分母为0");
			}
			return new Ra(this.num[0].multiply(b.num[1]),
					this.num[1].multiply(b.num[0]));
		}

		/**
		 * 其他运算
		 */
		public Ra negate(Ra b) {
			b.num[0] = b.num[0].negate();
			return b;
		}

		public boolean equals(Ra b) {
			if (this.subtract(b).num[0].equals(BigInteger.ZERO)) {
				return true;
			}
			return false;
		}

		public String toString() {
			String str = "";
			if (this.num[1].equals(BigInteger.ONE)) {
				str = num[0] + "";
			} else {
				str = num[0] + "/" + num[1];
			}
			return str;

		}
	}
}
