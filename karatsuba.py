
# Karatsuba algorithm for efficient multiplication of large integers.
# It uses divide-and-conquer to reduce the number of multiplications needed.

import time
import sys

def karatsuba(x, y):
    if x < 10 or y < 10:
        return x * y

    n = max(len(str(x)), len(str(y)))
    half = n // 2

    a = x // (10 ** half)
    b = x % (10 ** half)
    c = y // (10 ** half)
    d = y % (10 ** half)

    ac = karatsuba(a, c)
    bd = karatsuba(b, d)
    ad_plus_bc = karatsuba(a + b, c + d) - ac - bd

    return ac * (10 ** (2 * half)) + (ad_plus_bc * (10 ** half)) + bd

# Get user input
x = int(input('x: '))
y = int(input('y: '))

# Measure time taken
start_time = time.time()
result = karatsuba(x, y)
end_time = time.time()

# Measure memory usage in bytes and convert to bits
memory_usage_bytes = sys.getsizeof(result)
memory_usage_bits = memory_usage_bytes * 8

# ANSI escape codes for colors
RED = "\033[91m"
WHITE = "\033[97m"
RESET = "\033[0m"

# Print results with colored numbers
print(f'{WHITE}Result: {RED}{result}{RESET}')
print(f'{WHITE}Time taken: {RED}{end_time - start_time:.6f} seconds{RESET}')
print(f'{WHITE}Space taken: {RED}{memory_usage_bits} bits{RESET}')
