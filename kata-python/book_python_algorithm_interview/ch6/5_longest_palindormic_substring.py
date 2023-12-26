from functools import reduce
from collections import deque
# https://leetcode.com/problems/longest-palindromic-substring/

s1 = "babad"
s2 = "cbbd"

def sol(s: str) -> str:
    return list(filter(lambda x: is_palindrome(x),sorted(reduce(add , list(reversed(s)),deque()), key = len, reverse=True)))[0]

def add(d: deque, x: str):
    xs = d.copy()
    for y in d:
        xs.appendleft(x+y)
    xs.append(x)
    return xs

def is_palindrome(s: str) -> bool:
    return s == s[::-1]

# in book
def longesPalindrome(s: str) -> str:
    def expand(left: int, right: int) -> str:
        while left >= 0 and right <= len(s) and s[left] == s[right - 1]:
            left -= 1
            right += 1
        return s[left + 1:right - 1]

    if len(s) < 2 or s == s[::-1]:
        return s

    result = ''
    for i in range(len(s) - 1):
        result = max(result, expand(i, i+1), expand(i, i+2), key=len)
    return result
