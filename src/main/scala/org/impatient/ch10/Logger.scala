package org.impatient.ch10

import java.util.Date

import org.impatient.ch5.BankAccount

/**
  * Created by rahul on 19/2/16.
  */


trait Logger {
  def log(msg: String)
  def info(  msg: String) { log("INFO:   " + msg) }
  def warn(  msg: String) { log("WARN:   " + msg) }
  def severe(msg: String) { log("SEVERE: " + msg) }
}


trait Logged {
  def log(msg: String) = {}
}

trait ConsoleLogger  extends Logged {
  override def log(msg: String) = println(msg)
}



trait TimestampLogger extends Logged {
  override def log(msg: String) = super.log(new Date() + " : " + msg )
}


trait ShortLogger extends Logged {
  val maxLength = 15
  override def log(msg: String): Unit = {
    super.log(
      if(msg.length<= maxLength) msg else msg.substring(0, maxLength -3) + "..."
    )
  }
}

class Account(balance : Int) {
  def Balance = balance

  def withdraw(amount: Int): Unit = balance - amount
}


class CurrentAccount(amount: Int ) extends Account(amount) with Logged {

   override def withdraw(amount: Int) : Unit = {
    if(amount > Balance) { log("Insufficient Funds") }
    else  super.withdraw(amount)
  }
}

class SavingAccount(amount: Int) extends Account(amount) with Logger {

  override def withdraw(amount : Int) = {
    if(amount > Balance)  severe("Insufficient Funds")
    else super.withdraw(amount)
  }

  override def log(msg: String) = println(msg)
}


object logger {

  def main(args: Array[String]) {
    val hdfc = new CurrentAccount(1000) with ConsoleLogger with TimestampLogger with ShortLogger
    val sbi  = new CurrentAccount(1000) with ConsoleLogger with ShortLogger with TimestampLogger
    hdfc.withdraw(100000)
    sbi.withdraw(100000)

  }

}
