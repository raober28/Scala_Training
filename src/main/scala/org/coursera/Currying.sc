object currying {


  def sumI(f: Int => Int, a: Int, b: Int) = {
    def loop(a: Int, acc: Int): Int = {
      if(a > b) acc
      else loop(a  + 1, f(a) + acc)
    }
    loop(a, 0)
  }


  sumI(x => x * x, 3, 5)




  def prod(f: Int => Int)(a: Int, b: Int): Int = {
    def pd(a: Int, acc: Int): Int ={
      if(a > b) acc
      else pd(a + 1, f(a) * acc)
    }
    pd(a, 1)
  }

  println("result = " + prod(x => x ) (3, 5))


  def mapReduce(f: Int => Int, combine: (Int, Int) => Int,
                zero: Int)(a: Int, b: Int): Int = {
    if (a > b) zero
    else combine(f(a), mapReduce(f, combine, zero)(a + 1, b))

  }



  def product(f: Int => Int)(a: Int, b: Int): Int =
    mapReduce(f, (x, y) => x * y, 1) (a, b)

  println("product :" + product(x => x )(3, 5))




  




  def fact(n: Int) = product(x => x)(1, n)

  fact(5)

  def sum(a: Int, b: Int) = mapReduce(x => x, (x, y) => x + y, 0)(a, b)

  sum(1, 5)

}