if __name__ == '__main__':
    a = list(map(lambda x: x + 10, [1, 2, 3]))
    print(a)
    print([n * 2 for n in range(1, 10 + 1) if n % 2 == 1])
    print([n for n in range(1, 10 + 1)])
    print("---")
    original = {'jack': 4098, 'sape': 4139}
    b = {key: value for key, value in original.items()}
    print(b)
