package op.challenge

import scala.io.StdIn._

trait DialogContext {
  trait Dialog {
    val choices = Vector(Rock, Paper, Scissors, Spock, Lizard)
    val choicesMapping: Map[String, Choice] = Map(
      "1" -> choices(0),
      "2" -> choices(1),
      "3" -> choices(2),
      "4" -> choices(3),
      "5" -> choices(4)
    )
    val gameTypeOption =
      """Please chose game type (or 3 for exit):
        |1. Human vs computer
        |2. Computer vs computer
        |3. Exit
      """.stripMargin

    val choicesOption = s"Please make a choice:\n${
      (1 to choices.size zip choices)
        .map(t => s"${t._1} ${t._2.name}")
        .mkString("\n")
    }"

    val wrongChoiceOption = s"Wrong choice: ${
      choicesMapping.keys.toList.sorted.mkString(" ")
    } only! Please try again."

    val wrongYesOrNoOption = "Wrong choice: 'Y' or 'N' allowed. Please try again"

    def choiceInput(): String
    def show(msg: String): Unit
  }
  val dialog: DialogContext#Dialog

  class ConsoleLike extends Dialog {
    def choiceInput(): String = readLine()
    def show(msg: String) = println(msg)
  }
}
