defmodule Times do
  def double(n) do
    n * 2
  end

  ## ex) 6-1
  def triple(n), do: n * 3

  ## ex) 6-3
  def quadruple(n) do
    double(double(n))
  end
end

Times.double(4)
Times.double("cat")

defmodule Practice do
  def double(n), do: n * 2

  def greee(greeting, name),
    do:
      (
        IO.puts(greeting)
        IO.puts("How're you doing, #{name}?")
      )
end

defmodule Times2, do: def(double(n), do: n * 2)

defmodule Factorial do
  def of(0), do: 1
  def of(n), do: n * of(n - 1)
end

defmodule BadFactorial do
  def of(n), do: n * of(n - 1)
  def of(0), do: 1
end

## ex) 6-4
defmodule Exer4 do
  def sum(0), do: 0
  def sum(1), do: 1
  def sum(n), do: n + sum(n - 1)
end

## ex) 6-5
defmodule Exer5 do
  def gcd(x, 0), do: x
  def gcd(0, y), do: y
  def gcd(x, y), do: gcd(y, rem(x, y))
end

## 6.4 guard clause
defmodule Guard do
  def what_is(x) when is_number(x) do
    IO.puts("#{x} is a number")
  end

  def what_is(x) when is_list(x) do
    IO.puts("#{inspect(x)} is a list")
  end

  def what_is(x) when is_atom(x) do
    IO.puts("#{x} is an atom")
  end
end

Guard.what_is(99)
Guard.what_is(:cat)
Guard.what_is([1, 2, 3])
