package part1_recap

import scala.concurrent.Future
import scala.util.{Failure, Success}

object ScalaRecap extends App {
  println("hello")

  val aCondition: Boolean = true
  def myFunction(x: Int) = {
    if (x > 4) 42 else 65
  }

  class Animal
  trait Carnivore {
    def eat(a: Animal): Unit
  }
  object Carnivore

  // generics
  abstract class MyList[+A]

  // method notations
  1.+(2)

  // FP
  val anIncrementer: Int => Int = (x: Int) => x + 1
  anIncrementer(1)

  List(1,2,3).map(anIncrementer)

  val unknown: Any = 2
  val order = unknown match {
    case 1 => "First"
    case 2 => "Second"
    case _ => "unknown"
  }

  try {
    throw new RuntimeException
  } catch {
    case e: Exception => println("caught !")
  }

  // multithreading
  import scala.concurrent.ExecutionContext.Implicits.global
  val future = Future {
    42
  }

  future.onComplete {
    case Success(value) => println(s"good $value")
    case Failure(exc) => println(s"error $exc")
  }

  val partialFunction: PartialFunction[Int, Int] = {
    case 1 => 42
    case 2 => 65
    case _ => 999
  }

  // type aliases
  type AkkaReceive = PartialFunction[Any, Unit]
  def receive: AkkaReceive = {
    case 1=> println("Hello")
  }


  implicit val timeout = 3000
  def setTimeout(f: () => Unit)(implicit timeout: Int) = f()

  setTimeout(() => println("hello"))

  // conversions
  case class Person(name: String) {
    def greet: String = s"Hi $name"
  }
  implicit def fromStringToPerson(name: String) = Person(name)
  "Peter".greet

  implicit class Dog(name: String) {
    def bark = println("bark")
  }
  "Lassie".bark




}
