package op.challenge

import scala.annotation.tailrec

trait Player {
  val name: String
  def makeChoice(): Choice
}
trait Human extends Player {
  this: DialogContext =>

  @tailrec
  final def makeChoice(): Choice = {
    import dialog.{choicesMapping, choicesOption, wrongChoiceOption}

    dialog show choicesOption
    choicesMapping.get(dialog.choiceInput()) match {
      case Some(c) => c
      case None =>
        dialog show wrongChoiceOption
        makeChoice()
    }
  }
}
trait Computer extends Player {
  this: DialogContext =>
  import scala.util.Random._
  def makeChoice(): Choice = shuffle(dialog.choicesMapping.values).head
}

class Game(player1: Player, player2: Player) {
  def play(): String = {
    val choice1 = player1.makeChoice()
    val choice2 = player2.makeChoice()

    choice1 >=> choice2 match {
      case Win(how) => s"${player1.name} win. ${choice1.name} $how ${choice2.name}."
      case Lose(how) => s"${player1.name} lose. ${choice2.name} $how ${choice1.name}."
      case Draw => "Draw result."
    }
  }
}
