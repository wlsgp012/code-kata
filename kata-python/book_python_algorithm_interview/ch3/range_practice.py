import sys

if __name__ == '__main__':
    print(list(range(5)))
    print(range(5))
    print(type(range(5)))

    for i in range(5):
        print(i, end=' ')

    print('==================')
    a = [n for n in range(100000)]
    b = range(100000)

    print(len(a))
    print(len(b))

    print(a)
    print(b)

    print(sys.getsizeof(a))
    print(sys.getsizeof(b))

    print(b[999])
