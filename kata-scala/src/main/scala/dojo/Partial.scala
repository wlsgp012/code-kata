package dojo

object Partial extends App {
  val one: PartialFunction[Int, String] = { case 1 => "one"}

  println(one(1))
//  println(one(2)) exception occured

  println(one.isDefinedAt(1))
  println(one.isDefinedAt(2))
  val two: PartialFunction[Int, String] = { case 2 => "two"}
  val wildCard: PartialFunction[Int, String] = { case _ => "something else"}

  val partial = one orElse two orElse wildCard

  println(partial(1))
  println(partial(2))
  println(partial(3))

}
