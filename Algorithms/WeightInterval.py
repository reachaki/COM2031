import bisect

class Interval:
    def __init__(self, name, start, finish, value):
        self.name = name
        self.start = start
        self.finish = finish
        self.value = value

    def __repr__(self):
        return f"{self.name}: start={self.start}, finish={self.finish}, value={self.value}"

def find_p(intervals):
    '''Compute the p(i) for each interval based on the finish times.'''
    finish_times = [i.finish for i in intervals]
    p = [0] * len(intervals)  # p(i) array
    for j in range(len(intervals)):
        # bisect_right gives us the index of the first finish time > start
        i = bisect.bisect_right(finish_times, intervals[j].start) - 1
        p[j] = i + 1  # +1 because we want 1-based indexing
    return p

def calc_opt(n, p, values):
    '''Calculate the maximum weight set of compatible intervals.'''
    M = [0] * (n + 1)  # M[0] = 0
    for i in range(1, n + 1):
        M[i] = max(M[i - 1], values[i - 1] + M[p[i - 1]])
        print(f"M[{i}] = {M[i]} (max of M[{i - 1}] = {M[i - 1]} and value[{i - 1}] + M[p[{i - 1}]] = {values[i - 1]} + M[{p[i - 1]}] = {M[p[i - 1]]})")
    return M

def postprocess(n, M, p):
    '''Postprocess to find the selected intervals.'''
    j = n
    S = []
    while j > 0:
        if M[j] > M[j - 1]:
            S.append(j - 1)  # Store the index of the interval selected
            j = p[j - 1]  # Move to p(j)
        else:
            j -= 1
    return S

# Define intervals with names, start, finish, and values
intervals = [
    Interval("A", 7, 12, 6),
    Interval("B", 12, 14, 4),
    Interval("C", 17, 19, 8),
    Interval("D", 12, 18, 8),
    Interval("E", 6, 11, 5),
    Interval("F", 11, 16, 8),
    Interval("G", 4, 7, 8),
    Interval("H", 11, 15, 6),
]

# Step 1: Sort intervals by finish time
intervals.sort(key=lambda x: x.finish)

# Step 2: Compute p(i) for each interval
p = find_p(intervals)
print("p array:", p)

# Step 3: Calculate the values array
values = [i.value for i in intervals]

# Step 4: Calculate M
n = len(intervals)
M = calc_opt(n, p, values)

# Output M array
print("M array:", M)

# Step 5: Postprocess to find selected intervals
selected_indices = postprocess(n, M, p)

# Output the results
selected_intervals = [intervals[i] for i in selected_indices]
print("\nSelected intervals for maximum value:")
for interval in selected_intervals:
    print(interval)
