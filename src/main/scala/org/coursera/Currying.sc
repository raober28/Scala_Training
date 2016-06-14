object currying {


  def mapReduce(f: Int => Int, combine: (Int, Int) => Int,
                zero: Int)(a: Int, b: Int): Int = {
    if (a > b) zero
    else combine(f(a), mapReduce(f, combine, zero)(a + 1, b))

  }

  def product(f: Int => Int)(a: Int, b: Int): Int =
    mapReduce(f, (x, y) => x + y, 1) (a, b)

  product(x => x * x)(3, 3)



  def fact(n: Int) = product(x => x)(1, n)

  fact(5)


  def sum(a: Int, b: Int) = mapReduce(x => x, (x, y) => x + y, 0)(a, b)

  sum(1, 5)



}