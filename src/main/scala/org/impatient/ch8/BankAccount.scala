package org.impatient.ch8

/**
  * Created by rahul on 18/2/16.
  */
class BankAccount(initialBalance: Double) {
  private var balance = initialBalance
  def currentBalance = balance
  def deposit(amount: Double)  =  { balance += amount; balance }
  def withdraw(amount: Double) =  { balance -= amount; balance }

}


class CheckingAccount(initialBalance: Double) extends BankAccount(initialBalance)
{
  override def deposit(amount: Double) = super.deposit(amount - 1)
  override def withdraw(amount: Double) = super.withdraw(amount + 1)
}


class SavingAccount(initialBalance: Double) extends BankAccount(initialBalance)
{
  private val interest = 0.02
  private val freeTransactions = 3
  private var transactionCount = 0
  private val transactionFee = 1.0

  override def deposit(amount: Double) = {
    if(transactionCount < freeTransactions) {
      transactionCount += 1
      super.deposit(amount)
    }
    else {
      super.deposit(amount - transactionFee)
    }
  }

  override def withdraw(amount: Double) = {
    if(transactionCount < freeTransactions) {
      transactionCount += 1
      super.withdraw(amount)
    }
    else {
      super.withdraw(amount + transactionFee)
    }
  }

  def earnMonthlyInterest = {
    transactionCount = 0
    super.deposit(currentBalance * interest)
  }
}

object cool {
  def main(args: Array[String]) = {
    val axis = new SavingAccount(100)

    println(axis.currentBalance)

    println("First Deposit   : "    +  axis.deposit(100))
    println("Second Withdraw : "    +  axis.withdraw(100))
    println("Third Deposit   : "    +  axis.deposit(100))
    println("Fourth Withdraw : "   +   axis.withdraw(100))
    println("New Month Cycle Redeem Awards: " + axis.earnMonthlyInterest)


    println(axis.currentBalance)
  }
}
