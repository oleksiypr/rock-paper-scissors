package op.challenge

import scala.annotation.tailrec

trait GamePlay extends DialogContext { self =>
  private[this] var humanName: Option[String] = None

  class HumanPlayer(val name: String) extends Human with DialogContext {
    val dialog = self.dialog
  }
  class ComputerPlayer(val name: String) extends Computer with DialogContext {
    val dialog = self.dialog
    def this() = this("Computer #1")
  }

  @tailrec
  final def play(continue: Boolean): Unit = if (continue) {
    import dialog.gameTypeOption

    dialog show gameTypeOption
    dialog.choiceInput() match {
      case "1" => dialog.show("Human vs computer chosen."); humanVsComputer()
      case "2" => dialog.show("Computer vs computer chosen."); computerVsComputer()
      case "3" => play(false)
      case _   => dialog.show("Wrong choice: '1', '2' or '3' only! Please try again."); play(true)
    }
  } else dialog.show("Good bye!")

  def humanVsComputer() {
    val name = inputOrGetHumanName()
    val game = new Game(new HumanPlayer(name), new ComputerPlayer)
    val result = game.play()
    dialog.show(s"$result Play again Y/N?")
    play(yesOrNo())
  }

  def computerVsComputer() {
    val game = new Game(new ComputerPlayer, new ComputerPlayer("Computer #2"))
    val result = game.play()
    dialog.show(s"$result Play again Y/N?")
    play(yesOrNo())
  }

  def inputOrGetHumanName(): String = {
    humanName match {
      case Some(s) => s
      case None =>
        dialog.show("Enter your name")
        humanName = Some(dialog.choiceInput())
        humanName.get
    }
  }
  @tailrec
  private def yesOrNo(): Boolean = dialog.choiceInput() match {
    case "Y" | "y" => true
    case "N" | "n" => false
    case _ => dialog.show(dialog.wrongYesOrNoOption)
              yesOrNo()
  }
}
