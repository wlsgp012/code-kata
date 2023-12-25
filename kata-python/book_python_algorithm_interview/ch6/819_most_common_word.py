# https://leetcode.com/problems/most-common-word

import re
import collections

paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
banned = ["hit"]

def get_most_common_word(s: str, prohibited: list[str]):
    refined = re.sub('[^a-zA-Z0-9\s]', '',s)
    filtered = list(filter(lambda x: prohibited.count(x) == 0,refined.split()))
    counted  = collections.Counter(map(lambda x: x.lower(),filtered))
    picked = counted.most_common(1)[0][0]
    return list(filter(lambda x: x.lower() == picked,filtered))[0]


get_most_common_word(paragraph, banned)

# in book
def answer(paragraph: str, banned: list[str]) -> str:
    words = [word for word in re.sub(r'[^\w]', ' ', paragraph).lower().split() if word not in banned]
    counts = collections.Counter(words)
    return counts.most_common(1)[0][0]

