package op.challenge

import org.scalatest._

trait FakeDialogContext extends DialogContext {
  var gameHistory: List[String] = List.empty
  var fakeInput: String =_

  class FakeDialog extends Dialog {
    def choiceInput(): String = fakeInput
    def show(msg: String): Unit = gameHistory :+= msg
  }
  val dialog = new FakeDialog
}

class GamePlaySpec extends WordSpecLike with Matchers  {
  "GamePlay" should {
    "not play" in {
      val gamePlay = new GamePlay with FakeDialogContext
      gamePlay.play(false)
      gamePlay.gameHistory shouldBe List("Good bye!")
    }
    "stop playing when exit chosen" in {
      val gamePlay = new GamePlay with FakeDialogContext
      gamePlay.fakeInput = "3"
      gamePlay.play(true)
      gamePlay.gameHistory shouldBe List(
        gamePlay.dialog.gameTypeOption,
        "Good bye!"
      )
    }
    "play human vs computer" in {
      val gamePlay = new GamePlay with FakeDialogContext {
        override def humanVsComputer() = gameHistory :+= "humanVsComputer"
        override def computerVsComputer() = ()
      }
      gamePlay.fakeInput = "1"
      gamePlay.play(true)
      gamePlay.gameHistory shouldBe List(
        gamePlay.dialog.gameTypeOption,
        "Human vs computer chosen.",
        "humanVsComputer"
      )
    }
    "play with computer vs computer" in {
      val gamePlay = new GamePlay with FakeDialogContext {
        override def humanVsComputer() = ()
        override def computerVsComputer() = gameHistory :+= "computerVsComputer"
      }
      gamePlay.fakeInput = "2"
      gamePlay.play(true)
      gamePlay.gameHistory shouldBe List(
        gamePlay.dialog.gameTypeOption,
        "Computer vs computer chosen.",
        "computerVsComputer"
      )
    }
  }
}
