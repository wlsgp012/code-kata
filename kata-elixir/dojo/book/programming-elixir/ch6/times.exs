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

defmodule Factorial2 do
  def of(0), do: 1

  def of(n) when is_integer(n) and n > 0 do
    n * of(n - 1)
  end
end

Factorial2.of(-1)

## 6.5 default parameter
defmodule Example do
  def func(p1, p2 \\ 2, p3 \\ 3, p4) do
    IO.inspect([p1, p2, p3, p4])
  end
end

Example.func("a", "b")
Example.func("a", "b", "c")
Example.func("a", "b", "c", "d")

defmodule DefaultParams1 do
  def func(p1, p2 \\ 123) do
    IO.inspect([p1, p2])
  end

  def func(p1, 99) do
    IO.puts("you said 99")
  end
end

defmodule DefaultParams2 do
  def func(p1, p2 \\ 123)

  def func(p1, p2) when is_list(p1) do
    "You said ${p2} with a list"
  end

  def func(p1, p2) do
    "You passed in #{p1} and #{p2}"
  end
end

DefaultParams2.func(99)

defmodule DefaultParams2 do
  def func(p1, p2 \\ 123)

  def func(p1, p2) when is_list(p1) do
    "You said #{p2} with a list"
  end

  def func(p1, p2) do
    "You passed in #{p1} and #{p2}"
  end
end

DefaultParams2.func(99)
DefaultParams2.func(99, "cat")
DefaultParams2.func([99])
DefaultParams2.func([99], "dog")

## ex) 6-6
defmodule Chop do
  defp half(f..l), do: div(f + l, 2)

  defp print(h), do: IO.puts("It is #{h}")

  def guess(n, range) do
    guess(n, range, half(range))
  end

  def guess(n, range, h) when h == n do
    print(h)
    n
  end

  def guess(n, f..l, h) when h > n do
    print(h)
    guess(n, f..h, half(f..h))
  end

  def guess(n, f..l, h) do
    print(h)
    guess(n, h..l, half(h..l))
  end
end

Chop.guess(273, 1..1000)

## 6.7 pipe operator
1..10 |> Enum.map(&(&1 * &1)) |> Enum.filter(&(&1 < 40))
1..10 |> Enum.map(&(&1 * &1)) |> Enum.filter(&(&1 > 40)) |> List.to_tuple()

## 6.8 module
defmodule Outer do
  defmodule Inner do
    def inner_func do
    end
  end

  def outer_func do
    Inner.inner_func()
  end
end

Outer.outer_func()
Outer.Inner.inner_func()

defmodule Mix.Tasks.Doctest do
  def run do
  end
end

### import
defmodule ImportExample do
  def func1 do
    List.flatten([1, [2, 3], 4])
  end

  def func2 do
    import List, only: [flatten: 1]
    flatten([1, [2, 3], 4])
  end
end

ImportExample.func1()
ImportExample.func2()

defmodule AliasExample do
  def compile_and_go(source) do
    alias Enum, as: E
    source |> E.map(&(&1 * 10)) |> E.filter(&(&1 > 40))
  end

  def abc() do
    # alias My.Other.Module.{Parser, Runner}
  end
end

1..10 |> Enum.to_list() |> AliasExample.compile_and_go()

1..10 |> Enum.to_list() |> Enum.map(&(&1 * 10)) |> Enum.filter(&(&1 < 40))
# wired result
1..10 |> Enum.to_list() |> Enum.map(&(&1 * 10)) |> Enum.filter(&(40 < &1))
# wired result
1..10 |> Enum.to_list() |> Enum.map(&(&1 * 10)) |> Enum.filter(&(&1 == 40))

## 6.9 attribute
defmodule Attr do
  @author "Dave Thomas"
  def get_author do
    @author
  end
end

IO.puts("This book was written by #{Attr.get_author()}")

defmodule Attr2 do
  @attr "one"
  def first, do: @attr
  @attr "two"
  def second, do: @attr
end

IO.puts("#{Attr2.second()} #{Attr2.first()}")

## 6.10 module names
is_atom(IO)
to_string(IO)
:"Elixir.IO" === IO
IO.puts(123)
:"Elixir.IO".puts(123)

my_io = IO
my_io.puts(123)

## 6.11 calling a function in an erlang library
:io.format("The number is ~3.1f~n", [5.678])

## ex) 6-7
# – Convert a float to a string with two decimal digits. (Erlang)
:io_lib.format("~.2f", [2.2222])
# – Get the value of an operating-system environment variable. (Elixir)
System.get_env()

# – Return the extension component of a file name (so return .exs if given "dave/test.exs"). (Elixir)
Path.extname("dave/test.exs")
# – Return the process’s current working directory. (Elixir)
File.cwd
# – Convert a string containing JSON into Elixir data structures. (Just find; don’t install.)
https://hex.pm/packages/poison
https://hex.pm/packages/jason
# – Execute a command in your operating system’s shell.
System.cmd
