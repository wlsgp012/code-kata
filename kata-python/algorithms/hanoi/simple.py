def hanoi(n, frm=1, to=3, empty=2):
    if n == 1:
        print(f'{frm}>>{to}')
    else:
        hanoi(n - 1, frm, empty, to)
        print(f'{frm}>>{to}')
        hanoi(n - 1, empty, to, frm)


if __name__ == '__main__':
    hanoi(3)
