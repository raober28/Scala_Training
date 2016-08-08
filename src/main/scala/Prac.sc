import scala.concurrent.Future
import scala.util.{Failure, Success}

val f = Future {
  "hello there"
}

f onComplete{
  case Success(msg) => println(msg)
  case Failure(e) => e.printStackTrace()
}


