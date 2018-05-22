package problems.base.pratice;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 寻找素数，筛选法
 * @author zxr
 *
 */
public class SuShuFind {
	public static void main(String[] args) {
		List<Integer> origin = new LinkedList<>();
		for(int i = 2;i<=1000*1000;i++) {
			origin.add(i);
		}
		int target = 10002;
		int k = 0;	//筛选指针
		while(k != target -1) {
			Iterator<Integer> iter = origin.iterator();
			int base = origin.get(k);
			iter.next();
			while(iter.hasNext()) {
				if(iter.next() % base == 0) {
					iter.remove();
				}
			}
			k++;
		}
		System.out.println(origin.get(k));
	}
}
