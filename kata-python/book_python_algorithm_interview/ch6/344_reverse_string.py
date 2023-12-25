from functools import reduce
from collections import deque

sample1=list("hello")
sample2=list("Hannah")

def reverse(xs: list):
    return reduce(lambda r,x: append(r, x), xs, deque())

def append(q, x):
    q.appendleft(x)
    return q

# in book
def answer1(s: list[str]) -> None:
    left, right = 0, len(s) - 1
    while left < right:
        s[left], s[right] = s[right], s[left]
        left += 1
        right -= 1

def answer2(s: list[str]) -> None:
    s.reverse()
