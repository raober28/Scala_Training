object intsets {
}


val t1 = new NonEmpty(3, new Empty, new Empty)
val t2 = t1 incl 4
val t3 = new NonEmpty(7,
  new NonEmpty(5, new Empty, new Empty),
  new NonEmpty(9, new Empty, new Empty)
)

val t4 = new NonEmpty(20,
  new NonEmpty(15, new Empty, new Empty),
  new NonEmpty(25, new Empty, new Empty)
)


val t5 = new NonEmpty(1,
  new NonEmpty(2, new Empty, new Empty),
  new NonEmpty(3, new Empty, new Empty)
)

val t6 = new NonEmpty(4, new Empty, new Empty)

t3 union t4

t5 union t6




abstract class IntSet {
  def incl(x: Int): IntSet

  def contains(x: Int): Boolean

  def union(other: IntSet): IntSet
}

class Empty extends IntSet {
  def contains(x: Int): Boolean = false

  def incl(x: Int) = new NonEmpty(x, new Empty, new Empty)

  override def toString = "."

  def union(other: IntSet) = other
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  def contains(x: Int): Boolean = {
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true
  }

  def incl(x: Int): IntSet = {
    if (x < elem) new NonEmpty(elem, left incl x, right)
    else if (x > elem) new NonEmpty(elem, left, right incl x)
    else this
  }

  override def toString() = "{" + left + elem + right + "}"

  def union(other: IntSet) =
    ((left union right) union other) incl elem

  def cool(a: Int) =
    if (a > 10) print("hello") else throw new Error("cool")
}