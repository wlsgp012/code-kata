# 4.2.1 Integer
1234
0xcafe
0o765

# 4.2.2 Floating-point number
1.0
0.245
314159.0e-5

# 4.2.3 atom
:fred
:is_binary?
:<>
:var@2
:===
:"func/3"
:"long jonh silver"

# 4.2.4 ranges
1..10

# 4.2.5 regex
target = "caterpillar"
r = ~r{[aeiou]}
Regex.run r, target
Regex.scan r, target
Regex.split r, target
Regex.replace r,target, "*"

# 4.4.1 tuple
{1, 2}
{:ok, 42, "next"}
{1, 2, 3, 4, 5, 6, 7}
{status, count, action} = {:ok, 42, "next"}

# 4.4.2 list
[1, 2, 3] ++ [4, 5, 6]
[1, 2, 3, 4] -- [2, 4]
1 in [1, 2, 3, 4]
11 in [1, 2, 3, 4]
## keyword list
kl = [name: "Dave", city: "Dallas", likes: "Programming"]
[1, fred: 1, dave: 2]

# 4.5 map
states = %{"AL" => "Alabama", "WI" => "Wisconsin"}
states["AL"]

responses = %{{:error, :enoent} => :fatal}
responses[{:error, :enoent}]

colors = %{:red => "r", :green => "g", :blue => "b"}
colors[:red]
colors.green
colors[:yellow]

%{"one" => 1, :two => 2}
%{red: "r", blue: "b"}
name = "Jose Valim"
%{String.downcase(name) => name}

# 4.6 binary
bin = <<1, 2>>
bin2 = <<3 :: size(2), 5 :: size(4), 1 :: size(2)>>
:io.format("~-8.2b~n", :binary.bin_to_list(bin2))
byte_size(bin2)

# 4.7 Dates and Times
d1 = Date.new(2018, 12, 25)
{:ok, d1} = Date.new(2018, 12, 25)
d2 = ~D[2018-12-25]
d1 == d2
Date.day_of_week(d1)
Date.add(d1, 7)
inspect d1, structs: false

d3 = ~D[2018-01-01]
d4 = ~D[2018-06-30]
first_half = Date.range(d3, d4)
Enum.count(first_half)
~D[2018-03-15] in first_half

## time
{:ok, t1} = Time.new(12, 34, 56)
t2 = ~T[12:34:56.0]
t1 == t2
Time.add(t1, 3600)
Time.add(t1, 3600, :millisecond)

# 4.8
1 === 1.0
1 == 1.0
true or false
true and false
not true
true || false
true && false
!true

<<1, 2>> <> <<3, 4>>

# 4.9
line_no = 50
if(line_no == 50) do
  IO.puts "new-page\f"
  line_no = 0
end

IO.puts line_no

### with
with [a | _] <- [1, 2, 3], do: a
with [a | _] <- [], do: a

values = [1, 2, 3, 4]
mean = with count = Enum.count(values),
            sum = Enum.sum(values) do sum/count end

with(
     count = Enum.count(values),
  sum = Enum.sum(values)
) do
  sum/count
end

with count = Enum.count(values),
     sum = Enum.sum(values),
  do: sum/count
