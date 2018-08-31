import java.util.*;

class SegmentTree {
    int size;
    int[] st;
    public SegmentTree(int[] a, int n) {
        this.size = (int)(Math.ceil(Math.log(n) / Math.log(2)));
        this.size = 2 * (int)(Math.pow(2, this.size)) - 1;
        this.st = new int[this.size];
        this.construct(a, 0, 0, n - 1);
    }
    public void construct(int[] a, int si, int ss, int se) {
        if (ss == se) {
            this.st[si] = a[ss];
            return;
        }
        int mid = ss + (se - ss) / 2; 
        construct(a, si * 2 + 1, ss, mid);
        construct(a, si * 2 + 2, mid + 1, se);
        this.st[si] = Math.max(this.st[si * 2 + 1], this.st[si * 2 + 2]);
    }
    public int rangemin(int si, int ss, int se, int qs, int qe) {
        if (qs <= ss && qe >= se) {
            return this.st[si];
        }
        if (se < qs || ss > qe) {
            return 0;
        }
        int mid = ss + (se - ss) / 2;
        int ans = Math.max(rangemin(si * 2 + 1, ss, mid, qs, qe), 
                            rangemin(si * 2 + 2, mid + 1, se, qs, qe));
        return ans;
    }
    public void update(int index, int k, int ss, int se, int si) {
        if (index > se || index < ss) {
            return;
        }
        if (ss == se) {
            this.st[si] = k;
            return;
        }
        int mid = ss + (se - ss) / 2; 
        update(index, k, ss, mid, si * 2 + 1);
        update(index, k, mid + 1, se, si * 2 + 2);
        this.st[si] = Math.max(this.st[si * 2 + 1], this.st[si * 2 + 2]);
    }
}
public class LCSSegTree {
    public static int longestlength(int[] a) {
        int s = a[0];
        for (int i: a) {
            s = Math.max(i, s);
        }
        s += 1;
        int[] dp = new int[s];
        SegmentTree stree = new SegmentTree(dp, s);
        for (int i = 0; i < a.length; i++) {
            dp[a[i]] = stree.rangemin(0, 0, s - 1, 0, a[i] - 1) + 1;
            stree.update(a[i], dp[a[i]], 0, s - 1, 0);
        }
        return stree.st[0];
    }
    public static void main(String args[]) {
        Scanner get = new Scanner(System.in);
        int n = get.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = get.nextInt();
        }
        System.out.println(LCSSegTree.longestlength(arr));
    }
}