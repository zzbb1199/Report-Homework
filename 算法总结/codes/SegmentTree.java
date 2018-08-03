package algorithm.boyilun;
/**
 * 线段树
 * @author Raven
 *
 */
public class SegmentTree {
	static int[] a = new int[]{-1,1,4,1,5,13,2,51,4};
	static Tree[] tree = new Tree[4*(a.length-1)];//0位不使用
	public static void main(String[] args) {
		build(1,1,a.length-1);
		System.out.println(query_sum(1,4,a.length-1));
		System.out.println(query_max(1,4,a.length-1));
	}
	public static void build(int c,int l,int r){
		tree[c] = new Tree();
		tree[c].begin = l;
		tree[c].end = r;
		if(l == r){
			tree[c].sum = a[l];
			tree[c].max = a[l];
			return;
		}
		int mid = (tree[c].begin + tree[c].end)/2;
		build(c<<1,l,mid);
		build(c<<1|1,mid+1,r);
		//刷新
		push_up(c);
	}
	public static void push_up(int c){
		tree[c].sum = tree[c<<1].sum + tree[c<<1|1].sum;
		tree[c].max = Math.max(tree[c<<1].max, tree[c<<1|1].max);
	}
	public static void update(int c,int x,int val){
		if(tree[c].begin == tree[c].end){
			tree[c].sum = val;
			tree[c].max = val;
			return;
		}
		//否则
		int mid = (tree[c].begin+tree[c].end)/2;
		if(x <= mid){
			update(c<<1,x,val);
		}else if(x > mid){
			update(c<<1|1,x,val);
		}
		push_up(c);
	}
	public static int query_sum(int c,int l,int r){
		if(tree[c].begin == l && tree[c].end == r){
			return tree[c].sum;
		}
		int mid = (tree[c].begin+tree[c].end)/2;
		if(l > mid){
			return query_sum(c<<1|1,l,r);
		}
		if(r<= mid){
			return query_sum(c<<1,l,r);
		}
		return query_sum(c<<1,l,mid)+query_sum(c<<1|1,mid+1,r);
	}
	public static int query_max(int c,int l,int r){
		if(tree[c].begin == l && tree[c].end == r){
			return tree[c].max;
		}
		int mid = (tree[c].begin + tree[c].end)/2;
		if( l > mid){
			return query_max(c<<1|1,l,r);
		}
		if( r <= mid){
			return query_max(c<<1,l,r);
		}
		return Math.max(query_max(c<<1,l,mid), query_max(c<<1|1,mid+1,r));
	}
	static class Tree{
		int begin;
		int end;
		int sum;
		int max;
	}
}
