a = 1

1 = a

# 2 = a

# -------
[[a, b, c]] = [[1,2,3]]

# -------
[1, _, _] = [1,2,3]

[a, a] = [1, 1]

[b ,b] = [1 ,2]

# -------
a = 1

[1, a, 3] = [1, 2, 3]

# pin operator  -------
a = 1
a = 2
^a = 1

# pin operator2  -------
a = 1
[^a, 2, 3] = [1, 2, 3]
a = 2
[^a ,2] = [1, 2]

# -------
[a,b,a] = [1,2,3]
[a,b,a]=[1,2,1]
