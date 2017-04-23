package org

import org.scalatest._

/**
  * Base Class that other Test Suites will extend
  */
abstract class UnitSpec extends FlatSpec with Matchers with
  OptionValues with Inside with Inspectors
