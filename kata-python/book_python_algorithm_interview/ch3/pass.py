import pprint

class MyClass(object):
    def ma(self):
        # 여기에 pass 추가
        pass

    def mb(self):
        print("method b")


if __name__ == '__main__':
    c = MyClass()
    # locals
    pprint.pprint(locals())
