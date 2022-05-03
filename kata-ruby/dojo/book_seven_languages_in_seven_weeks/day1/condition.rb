# 2.2.4

x = 4

puts x < 5

puts x <= 4

puts x > 4

puts false.class

puts true.class

puts 'This appears to be false.' unless x == 4

puts 'This appears to be true.' if x == 4

if x == 4
  puts 'This appears to be true.(if)'
end

unless x == 4
  puts 'This appears to be false.'
else
  puts 'This appears to be true.(unless-else)'
end

puts 'if not ture' if not true

puts 'if !ture' if !true

x = x + 1 while x < 10

puts x

x = x - 1 until x == 1

puts x

while x < 10
  puts x
  x = x + 1
end

puts 'if 1' if 1

puts 'if "random string"' if 'random string'

puts 'if 0' if 0

puts 'if true' if true

puts 'if false' if false

puts 'if nil' if nil

puts (true and false)

puts (true or false)

puts (false && false)

puts (true | false)
# puts (true && this_will_cause_an_error)
#
# puts (false && this_will_cause_an_error)