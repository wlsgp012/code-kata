# https://leetcode.com/problems/two-sum/

nums = [3, 2, 4]
target = 6


def sol(xs: list[int], t: int) -> list[int]:
    for i, x in enumerate(xs):
        for j, y in enumerate(xs[i + 1:]):
            if (t == x + y):
                return [i, i + j + 1]


def sol2(nums: list[int], target: int) -> list[int]:
    x_to_i = {}
    for i, x in enumerate(nums):
        diff = target - x
        try:
            j = x_to_i[diff]
            return [i, j]
        except KeyError:
            try:
                j = nums.index(diff)
                if (i != j):
                    return [i, j]
            except ValueError:
                x_to_i[x] = i


# in book
def answer(nums: list[int], target: int) -> list[int]:
    nums_map = {}
    for i, num in enumerate(nums):
        if target - num in nums_map:
            return [nums_map[target - num], i]
        nums_map[num] = i


# chatgpt
def find_indices(nums, target):
    # Create a function to find the complement of each number with respect to the target
    def find_complement(pair):
        index, num = pair
        complement = target - num
        return complement, index

    # Use map to apply the find_complement function to each (index, num) pair
    complements_with_indices = map(find_complement, enumerate(nums))

    # Filter the pairs to keep only those where the complement exists in the original list
    valid_complements = filter(
        lambda pair: pair[0] in nums and pair[1] != nums.index(pair[0]),
        complements_with_indices)

    # Extract the indices from the valid pairs
    indices = map(lambda pair: [pair[1], nums.index(pair[0])],
                  valid_complements)

    # Return the first valid pair of indices
    return next(indices)
