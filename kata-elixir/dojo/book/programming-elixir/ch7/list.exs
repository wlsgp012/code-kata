a = [1 | [2 | [3 | []]]]
[head | tail] = a
head
tail

defmodule MyList do
  def len([]), do: 0
  def len([_ | tail]), do: 1 + len(tail)

  def square([]), do: []
  def square([h | t]), do: [h * h | square(t)]

  ## 7.4
  def map([], _), do: []
  def map([h | t], f), do: [f.(h) | map(t, f)]

  ## 7.5
  def reduce([], v, _) do
    v
  end

  def reduce([h | t], v, f) do
    reduce(t, f.(h, v), f)
  end

  ## ex) 7-1
  def mapsum(xs, f) do
    reduce(xs, 0, &(f.(&1) + &2))
  end

  ## ex) 7-2
  def max(xs) do
    [h | _] = xs
    reduce(xs, h, &max(&1, &2))
  end

  ## ex) 7-3
  def caesar(xs, n) do
    map(xs, fn
      x when x + n > 122 -> x + n - 123 + 97
      x -> x + n
    end)
  end

  defp plusChar(x, n) do
    y = x + n
  end

  ## ex) 7-4
  def span(from, to) when from >= to, do: [to]

  def span(from, to) do
    [from | span(from + 1, to)]
  end
end

MyList.len(a)
MyList.len([])

MyList.square([])
MyList.square(a)

MyList.map(a, fn n -> n + 1 end)
MyList.map(a, &(&1 > 2))
MyList.map(a, &(&1 < 2))

MyList.reduce(a, 0, &(&1 + &2))

MyList.mapsum(a, &(&1 * &1))

MyList.max([3, 20, 23, 200, 0])

MyList.caesar('ryvkve', 13)

## 7.6
[1, 2, 3 | [4, 5, 6]]

defmodule Swapper do
  def swap([]), do: []
  def swap([a, b | tail]), do: [b, a | swap(tail)]
  def swap([_]), do: raise("Can't swap a list with an odd number of elements")
end

xs = Enum.to_list(1..6)

Swapper.swap(xs)
Swapper.swap(xs ++ [7])

defmodule WeatherHistory do
  def for_location_27([]), do: []

  def for_location_27([[time, 27, temp, rain] | tail]) do
    [[time, 27, temp, rain] | for_location_27(tail)]
  end

  def for_location_27([_ | tail]), do: for_location_27(tail)

  def test_data do
    [
      [1_366_225_622, 26, 15, 0.125],
      [1_366_225_622, 27, 15, 0.45],
      [1_366_225_622, 28, 21, 0.25],
      [1_366_229_222, 26, 19, 0.081],
      [1_366_229_222, 27, 17, 0.468],
      [1_366_229_222, 28, 15, 0.60],
      [1_366_232_822, 26, 22, 0.095],
      [1_366_232_822, 27, 21, 0.05],
      [1_366_232_822, 28, 24, 0.03],
      [1_366_236_422, 26, 17, 0.025]
    ]
  end
end

import WeatherHistory

for_location_27(test_data)

defmodule WeatherHistory2 do
  def for_location([], _), do: []

  def for_location([head = [_, target_loc, _, _] | tail], target_loc) do
    [head | for_location(tail, target_loc)]
  end

  def for_location([_ | tail], target_loc), do: for_location(tail, target_loc)

  def test_data do
    [
      [1_366_225_622, 26, 15, 0.125],
      [1_366_225_622, 27, 15, 0.45],
      [1_366_225_622, 28, 21, 0.25],
      [1_366_229_222, 26, 19, 0.081],
      [1_366_229_222, 27, 17, 0.468],
      [1_366_229_222, 28, 15, 0.60],
      [1_366_232_822, 26, 22, 0.095],
      [1_366_232_822, 27, 21, 0.05],
      [1_366_232_822, 28, 24, 0.03],
      [1_366_236_422, 26, 17, 0.025]
    ]
  end
end

WeatherHistory2.for_location(test_data, 28)

## 7.7
[1, 2, 3] ++ [4, 5, 6]
List.flatten([[[1], 2], [[[3]]]])
List.foldl([1, 2, 3], "", fn x, acc -> "#{x}(#{acc})" end)
List.foldr([1, 2, 3], "", fn x, acc -> "#{x}(#{acc})" end)
y = [1, 2, 3]
List.replace_at(y, 2, 29)

kw = [{:name, "Dave"}, {:likes, "Programming"}, {:where, "Dallas", "Tx"}]

List.keyfind(kw, "Dallas", 1)
List.keyfind(kw, "Tx", 2)
List.keyfind(kw, "Tx", 1)
List.keyfind(kw, "Tx", 1, "No city called TX")
kw = List.keydelete(kw, "Tx", 2)
kw = List.keyreplace(kw, :name, 0, {:first_name, "Dave"})
