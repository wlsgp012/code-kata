numbers = { 1 => 'one', 2 => 'two' }
puts numbers[1]
stuff = { :array => [1, 2, 3], :string => 'Hi, mom!' }
puts stuff[:string]

puts "----------------"
puts 'string'.object_id == 'string'.object_id
puts :string.object_id == :string.object_id

puts "----------------"

def tell_the_truth(options = {})
  if options[:profession] == :lawyer
    'it could be believed that this is almost certainly not false'
  else
    true
  end
end

puts tell_the_truth
puts tell_the_truth(:profession => :lawyer)
puts tell_the_truth({ :profession => :lawyer })

puts "----------------"

def to_arr(h)
  r = []
  h.each { |k, v| r.push(k, v)}
  r
end
p to_arr(numbers)

