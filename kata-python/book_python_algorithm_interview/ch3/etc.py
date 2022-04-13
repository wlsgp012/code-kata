if __name__ == '__main__':
    # division
    print(5 / 3)
    print(type(5 / 3))
    print(5 // 3)
    print(type(5 // 3))
    # remainder
    print(5 % 3)
    # both
    print(divmod(5, 3))

    # print
    print('A1', 'B2')
    print('A1', 'B2', sep=',')
    a = ['1', '2']
    print(a)
    print(' '.join(a))
    idx = 1
    fruit = "Apple"
    print('{0}: {1}'.format(idx + 1, fruit))
    print('{}: {}'.format(idx + 1, fruit))
    # f-string
    print(f'{idx + 1}: {fruit}')
