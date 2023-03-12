a = [1 | [2 | [3 | []]]]
[head|tail] = a
head
tail

defmodule MyList do
  def len([]), do: 0
  def len([_ | tail]), do: 1 + len(tail)

  def square([]), do: []
  def square([h|t]), do: [h*h | square(t)]

  def map([], _), do: []
  def map([h|t], f), do: [f.(h) | map(t, f)]

  def reduce([], v, _) do
    v
  end
  def reduce([h|t], v, f) do
    reduce(t, f.(h, v), f)
  end

  ## ex) 7-1
  def mapsum(xs, f) do
    reduce(xs, 0, &(f.(&1) + &2))
  end

  ## ex) 7-2
  def max(xs) do
    [h|_] = xs
    reduce(xs, h, &(max(&1, &2)))
  end

  ## ex) 7-3
  def caesar(xs, n) do
    map(xs,fn
      x when x + n > 122 -> x + n - 123 + 97
      x -> x + n
    end)
  end

  defp plusChar(x, n)  do
    y = x + n
  end
end

MyList.len a
MyList.len []

MyList.square []
MyList.square a

MyList.map a, fn n -> n+1 end
MyList.map a, &(&1 > 2)
MyList.map a, &(&1 < 2)

MyList.reduce(a, 0, &(&1 + &2))

MyList.mapsum a, &(&1 * &1)

MyList.max([3, 20, 23, 200, 0])

MyList.caesar('ryvkve',13)
