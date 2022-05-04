# print Hello, world
puts "Hello, world"

# find Ruby index
ruby = "Ruby"
puts "Hello, #{ruby}".index(ruby)

# prints my name 10 times
(1..10).each do
  puts "Driss"
end

# prints "This is sentence number x"
(1..10).each do |i|
  puts "This is sentence number #{i}"
end

# guess number
x = rand(10)
puts "Guess number : "
y = gets().to_i
puts "x is #{x}. Your guess is..."
if x > y
  puts "It's small"
else
  puts "It's big or equal"
end