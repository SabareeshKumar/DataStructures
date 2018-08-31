import math
class SegmentTree:
    def __init__(self, a, n):
        #self.size = 2 * n - 1
        self.size = int(math.ceil(math.log(n, 2)))
        self.size = 2 * (2 ** self.size) - 1
        self.st = [None] * self.size
        self.construct(a, 0, 0, n - 1)
    def construct(self, arr, si, ss, se):
        if (ss == se):
            #print ss, se, si
            self.st[si] = arr[ss]
            return 
        mid = ss + (se - ss) // 2 
        self.construct(arr, si * 2 + 1, ss, mid)
        self.construct(arr, si * 2 + 2, mid + 1, se)
        self.st[si] = max(self.st[si * 2 + 1], self.st[si * 2 + 2])
    def rangemin(self, si, ss, se, qs, qe):
        if (qs <= ss and qe >= se):
            #print ss, se, si
            return self.st[si]
        if (se < qs or ss > qe):
            return 0
        mid = ss + (se - ss) // 2
        ans = max(self.rangemin(si * 2 + 1, ss, mid, qs, qe), self.rangemin(si * 2 + 2, mid + 1, se, qs, qe))
        return ans
    def update(self, index, k, ss, se, si):
        if (index > se or index < ss):
            return
        if (ss == se):
            self.st[si] = k
            return 
        mid = ss + (se - ss) // 2 
        self.update(index, k, ss, mid, si * 2 + 1)
        self.update(index, k, mid + 1, se, si * 2 + 2)
        self.st[si] = max(self.st[si * 2 + 1], self.st[si * 2 + 2])
        
def longestlength():
    s = max(a) + 1
    dp = [0] * s
    stree = SegmentTree(dp, s)
    for i in range(n):
        dp[a[i]] = stree.rangemin(0, 0, s - 1, 1, a[i] - 1) + 1
        stree.update(a[i], dp[a[i]], 0, s - 1, 0)
    return stree.st[0]

n = int(raw_input().strip())
a = [None] * n
for _ in range(n):
    a[_] = int(raw_input().strip())
print longestlength()