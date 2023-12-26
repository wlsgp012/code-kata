# https://leetcode.com/problems/group-anagrams/
import collections
from functools import reduce

strs = ["eat", "tea", "tan", "ate", "nat", "bat"]


def to_group(xs: list[str]) -> list[list[str]]:
    d = reduce(to_map, xs, collections.defaultdict(list))
    return d.values()


def to_map(m: dict[str, list[str]], x: str):
    k = "".join(sorted(x))
    xs = m[k]
    xs.append(x)
    return m


# in book
def answer(strs):
    anagrams = collections.defaultdict(list)

    for word in strs:
        anagrams[''.join(sorted(word))].append(word)
    return anagrams.values()
