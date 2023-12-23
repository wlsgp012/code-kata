sample1 = "A man, a plan, a canal: Panama"
sample2 = "race a car"

def is_palindrome(s):
    refined = refine_string(s)
    return refined == list(reversed(refined))

def refine_string(s: str):
    lowered = s.lower()
    return [l for l in lowered if l.isalnum()]

# in book
def answear1(self, s: str) -> bool:
    strs = []
    for char in s:
        if char.isalnum():
            strs.append(char.lower())

    while len(strs) > 1:
        if strs.pop(0) != strs.pop():
            return False
    return True

def answear2(self, s: str) -> bool:
    strs: Deque = collections.deque()
    for char in s:
        if char.isalnum():
            strs.append(char.lower())

    while len(strs) > 1:
        if strs.popleft() != strs.pop():
            return False
    return True

def answear3(self, s: str) -> bool:
    s = s.lower()
    s = re.sub('[^a-z0-9]','',s)
    return s == s[::-1]
