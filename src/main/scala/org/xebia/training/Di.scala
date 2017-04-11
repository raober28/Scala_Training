package org.xebia.training

/**
  * Created by rahul on 18/3/17.
  */


abstract class Dao {
  def save[T](t: T)

  def delete[T](t: T)
}

object JDBCDao extends Dao {

  def save[T](t: T) = print("Saving entity to RDBMS")

  def delete[T](t: T) = print("Deleting entity from Database")
}


object MockDao extends Dao {

  def save[T](t: T) = print("Mocking the save process")

  def delete[T](t: T) = print("Mocking the delete process")
}

class Service[T](dao: Dao) {

  def create(t: T) = dao.save(t)

  def delete(t: T) = dao.delete()
}

object RealService extends Service(JDBCDao)

object MockService extends Service(MockDao)
