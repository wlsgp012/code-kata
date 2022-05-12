puts "enumerable, comparable"

puts 'begin' <=> 'end'
puts 'same' <=> 'same'
a = [5, 3, 4, 1]
p a
p a.any? { |i| i > 6 }
p a.any? { |i| i > 4 }
p a.all? { |i| i > 4 }
p a.all? { |i| i > 0 }
p a.collect { |i| i * 2 }
p a.map { |i| i * 2 }
p a.select { |i| i % 2 == 0 }
p a.select { |i| i % 2 == 1 }
p a.max
p a.member?(2)
p a.inject(0) { |sum, i| sum + i }
p a.inject { |sum, i| sum + i }
p a.inject { |product, i| product * i }
puts "-----------------------"
a.inject(0) do |sum, i|
  puts "sum: #{sum} i: #{i} sum + i: #{sum + i}"
  sum + i
end
