println("kksdfj")

var num = 234
val msg = 234 match {
  case 12 => "gotcha"
  case 15 => "you are near"
 // case any => "not in scope" + any
 // case num => "dasd"
  case _ => "default"
}

println("msg :" + msg)

class Animal { def breath = "breath"}
class Lion extends  Animal { def lionBreath = "lionBrteath"}
class Tiger extends Animal { def tigerBreath = "tigerBreat`h"}
class Dog extends  Animal { def dogBreath = "dogBreath"}
class Cat extends Animal


doSomethig(new Lion())


def doSomethig(animal: Animal) = {
  animal.breath
  animal match {
    case animal: Tiger if( 1> 2)=> animal.tigerBreath
    case animal: Lion => animal
    case animal : Dog => animal
  }
}


  val array = Array(1, 2, 3, 4)

array match {
  case Array(1, 2, _*) => "matched"
  case _ => "not matched"
}





val ch = '2'
ch match {
  case '+' =>  1
  case '-' =>  -1
  case _ if Character.isDigit(ch) => Character.digit(ch, 10)
  case _ =>  0
}



