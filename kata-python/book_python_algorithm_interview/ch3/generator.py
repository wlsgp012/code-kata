def get_natural_number():
    n = 0
    while True:
        n += 1
        yield n


def generator():
    yield 1
    yield 'string'
    yield True


if __name__ == '__main__':
    g = get_natural_number()

    # print(next(g))
    for _ in range(0, 100):
        print(next(g))

    print('=============')
    g1 = generator()
    print(next(g1))
    print(next(g1))
    print(next(g1))
