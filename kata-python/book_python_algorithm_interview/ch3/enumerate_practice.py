if __name__ == '__main__':
    a = [1, 2, 3, 2, 45, 2, 5]
    print(a)
    en = enumerate(a)
    print(en)
    print(list(en))
    print('================')
    for i, v in enumerate(a):
        print(i, v)
