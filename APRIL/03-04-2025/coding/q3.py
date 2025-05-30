import collections

def solve():
    n, m = map(int, input().split())
    earnings = list(map(int, input().split()))
    edges = []
    for _ in range(m):
        u, v = map(int, input().split())
        edges.append((u - 1, v - 1))

    adj = collections.defaultdict(list)
    rev_adj = collections.defaultdict(list)
    for u, v in edges:
        adj[u].append(v)
        rev_adj[v].append(u)

    total_increment = 0
    mod = 10**9 + 7

    for i in range(n):
        reachable = set()
        q = collections.deque([i])
        reachable.add(i)
        while q:
            curr = q.popleft()
            for neighbor in rev_adj[curr]:
                if neighbor not in reachable:
                    reachable.add(neighbor)
                    q.append(neighbor)

        max_earning_reachable = -float('inf')
        for city_index in reachable:
            max_earning_reachable = max(max_earning_reachable, earnings[city_index])

        increment = (max_earning_reachable - earnings[i]) % mod
        total_increment = (total_increment + increment) % mod

    print(total_increment)

t = 1
for _ in range(t):
    solve()