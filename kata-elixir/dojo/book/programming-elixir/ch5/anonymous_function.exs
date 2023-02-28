sum = fn (a,b) -> a + b end

greet = fn -> IO.puts "Hellom" end

f1 = fn a,b -> a*b end

## 5.1 pattern matching
swap = fn {a,b} -> {b,a} end

## ex) 5-1
list_concat = fn xs,ys -> xs ++ ys end
list_concat.([1,2],[3,4])

sum = fn (a,b,c) -> a + b + c end
sum.(1,2,3)

# pair_tuple_to_list = fn t -> Tuple.to_list(t)  end
pair_tuple_to_list = fn {a, b} -> [a, b]  end
pair_tuple_to_list.({1234,5678})


## 5.2 one function, multiple bodies
handle_open = fn
  {:ok, file} -> "Read data: #{IO.read(file, :line)}"
  {_, error} -> "Error: #{:file.format_error(error)}"
end

handle_open.(File.open("../ch1/hello.exs"))
handle_open.(File.open("nonexistent"))

## ex) 5-2
fizzbuzz = fn
  0,0,_ -> "FizzBuzz"
  0,_,_ -> "Fizz"
  _,0,_ -> "Buzz"
  _,_,z -> z
end

fizzbuzz.(0,1,1)
fizzbuzz.(1,0,1)
fizzbuzz.(1,1,1)

## ex) 5-3
to_fizzbuzz = fn n -> fizzbuzz.(rem(n,3),rem(n,5),n) end
to_fizzbuzz.(10)
to_fizzbuzz.(11)
to_fizzbuzz.(12)
to_fizzbuzz.(15)

## 5.3 hof
fun1 = fn -> fn -> "Hello" end end
fun1.().()

greeter = fn name -> (fn -> "Hello #{name}" end) end
dave_greeter = greeter.("Dave")
dave_greeter.()

add_n = fn n -> (fn other -> n + other end) end
add_two = add_n.(2)
add_two.(3)

## ex) 5-4
prefix = fn x -> (fn y -> "#{x} #{y}"  end) end
prefix.("Mrs").("Smith")

## 5.4 hof2
times_2 = fn n -> n * 2 end
apply = fn (fun, value) -> fun.(value) end
apply.(times_2,6)

list = [1,2,3,4]
Enum.map list,fn e -> e*2 end

defmodule Greeter do
  def for(name, greeting) do
          fn
            (^name) -> "#{greeting} #{name}"
            (_) -> "I don't know you"
           end
  end
end

mr_valim = Greeter.for("Jose","Oi!")
mr_valim.("Jose")
mr_valim.("Dave")

### & notation
add_one = &(&1 + 1)
add_one.(44)

divrem = &{div(&1, &2), rem(&1, &2)}
divrem.(13, 5)

s = &"bacon and #{&1}"

l = &length/1
l.([1,2,3,4])

Enum.map list,&(&1 * &1)

## ex) 5-5
Enum.map list,&(&1 + 2)
Enum.each list,&IO.inspect/1
