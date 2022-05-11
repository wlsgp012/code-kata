animals = ['lions', 'tigers', 'bears']

puts animals
puts "----------------"
puts animals[0]
puts animals[2]
puts "----------------"
puts animals[-1]
puts animals[-2]
puts "----------------"
puts animals[3]
puts "----------------"
puts animals[0..1]
puts "----------------"
puts (0..1).class
puts "----------------"
a = []
puts a
puts [1].class
puts [1].methods.include?('[]')
puts [1].methods.include?(':[]')
a[0] = 'zero'
a[1] = 1
a[2] = ['two', 'things']
puts "----------------"
print(a)
puts "----------------"
a = [[1,2,3], [10, 20, 30], [40, 50, 60]]
puts a[0][0]
puts a[1][2]
puts "----------------"
a = [1]
a.push(2)
print(a)
puts "----------------"
puts a.pop
puts "----------------"
print(a)

