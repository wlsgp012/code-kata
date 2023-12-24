# https://leetcode.com/problems/reorder-data-in-log-files/

logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"]

def reorder(xs: list[str]) -> list[str]:
    return sorted(xs, key=key_for_sorting)


def key_for_sorting(s: str):
    parsed = s.split(maxsplit=1)
    if parsed[1].replace(" ", "").isalpha():
        return [0, parsed[1], parsed[0]]
    else:
        return [1]

# in book
def reorderLogFileds(logs):
    letters, digits = [], []
    for log in logs:
        if log.split()[1].isdigit():
            digits.append(log)
        else:
            letters.append(log)

    letters.sort(key=lambda x: (x.split()[1:], x.split()[0]))
    return letters + digits
