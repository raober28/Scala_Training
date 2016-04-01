package org.future

/**
  * Created by rahul on 1/4/16.
  */

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Random, Try}


object Cappuccino extends App {
  type CoffeeBeans = String
  type GroundCoffee = String
  case class Water(temperature: Int)
  type Milk = String
  type FrothedMilk = String
  type Espresso = String
  type Cappuccino = String

  def grind(beans: CoffeeBeans): Future[GroundCoffee] = Future{

    println("starting grinding...")
    Thread.sleep(Random.nextInt(2000))
    if(beans == "baked beans") throw GrindingException("are you joking? ")
    println("finished grinding...")
    s"ground coffee of $beans"
  }

  def heatWater(water: Water): Future[Water] =  Future {
    println("heating the water now")
    Thread.sleep(Random.nextInt(2000))
    println("hot, it's hot! ")
    water.copy(temperature = 85)
  }


  def frothMilk(milk: Milk): Future[FrothedMilk] = Future {

    println("milk frothing system engaged")
    Thread.sleep(Random.nextInt(2000))
    println("shutting down milk frothing system")
    s"frothed $milk"
  }

  def brew(coffee: GroundCoffee, heatedWater: Water) : Future[Espresso] = Future {
    println("happy brewing :)")
    Thread.sleep(Random.nextInt(2000))
    println("it's brewed!")
    "espresso"
  }

  def combine(espresso: Espresso, frothedMilk: FrothedMilk): Cappuccino = "cappuccino"


  case class GrindingException(msg: String) extends Exception(msg)
  case class FrothingException(msg: String) extends Exception(msg)
  case class WaterBoilingException(msg: String) extends Exception(msg)
  case class BrewingException(msg: String) extends Exception(msg)

  println("Cappuccino starting")
  def prepareCappuccino(): Future[Cappuccino] = {
    val groundCoffee = grind("arabic beans")
    val heatedWater = heatWater(Water(20))
    val frothedMilk = frothMilk("milk")
    for {
      ground <- groundCoffee
      water  <- heatedWater
      espresso <- brew(ground, water)
      foam   <- frothedMilk
    } yield combine(espresso, foam)
  }

  val capo = prepareCappuccino()
  Await.ready(capo, 1 minutes)
  println("Cappuccino ending")
}




