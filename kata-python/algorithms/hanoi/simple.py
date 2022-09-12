import time


def hanoi2(n, frm=1, to=3, empty=2):
    if n == 1:
        print(f'{frm}>>{to}')
    else:
        hanoi2(n - 1, frm, empty, to)
        print(f'{frm}>>{to}')
        hanoi2(n - 1, empty, to, frm)


def hanoi(n, frm=1, to=3, empty=2):
    if n > 0:
        hanoi(n - 1, frm, empty, to)
        print(f'disc={n},{frm}>>{to}')
        hanoi(n - 1, empty, to, frm)


if __name__ == '__main__':
    start = time.time()
    hanoi(10)
    print(time.time() - start)
