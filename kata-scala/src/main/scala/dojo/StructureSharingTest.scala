package dojo

object StructureSharingTest extends App {
  val a = List(1,2,3)
  println(a)
  val b = 0 :: a
  println(b)
  val c = b.drop(1)
  println(c)

  println(a == c)
  println(a eq c)
  println(List(1,2,3) == List(1,2,3))
  println(List(1,2,3) eq List(1,2,3))
}
