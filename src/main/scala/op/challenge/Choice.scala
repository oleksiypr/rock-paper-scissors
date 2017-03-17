package op.challenge

sealed trait Result {
  val how: String
}
case object Draw extends Result {
  val how: String = "Same"
}
case class Win(how: String) extends Result
case class Lose(how: String) extends Result

trait Choice {
  val name: String
  val strongerThan: Map[Choice, String]
  def >=>(other: Choice): Result = strongerThan.get(other) match {
    case Some(how) => Win(how)
    case None if this == other => Draw
    case None => Lose((other >=> this).how)
  }
}
object Paper extends Choice {
  val name = "Paper"
  val strongerThan = Map(Rock -> "covers", Spock -> "disproves")
}
object Rock extends Choice {
  val name = "Rock"
  val strongerThan = Map(Scissors -> "beats", Lizard -> "crushes")
}
object Scissors extends Choice {
  val name = "Scissors"
  val strongerThan = Map(Paper -> "cut", Lizard -> "decapitates")
}
object Spock extends Choice {
  val name = "Spock"
  val strongerThan = Map(Scissors -> "smashes", Rock -> "vaporizes")
}
object Lizard extends Choice {
  val name = "Lizard"
  val strongerThan = Map(Spock -> "poisons", Paper -> "eats")
}
