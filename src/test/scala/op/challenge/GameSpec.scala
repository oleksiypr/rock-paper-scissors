package op.challenge

import org.scalatest.{Matchers, WordSpecLike}

class FakePlayer(val name: String, choice: Choice) extends Player {
  def makeChoice() = choice
}

class GameSpec extends WordSpecLike with Matchers  {
  "Game" should {
    "play scenario 1" in {
      val winner = new FakePlayer("Player1", Paper)
      val loser = new FakePlayer("Player2", Rock)
      val game = new Game(winner, loser)
      val result = game.play()
      result shouldBe "Player1 win. Paper covers Rock."
    }

    "play scenario 2" in {
      val loser = new FakePlayer("Player1", Scissors)
      val winner = new FakePlayer("Player2", Rock)
      val game = new Game(loser, winner)
      val result = game.play()
      result shouldBe "Player1 lose. Rock beats Scissors."
    }

    "play scenario 3" in {
      val winner = new FakePlayer("Player1", Scissors)
      val loser = new FakePlayer("Player2", Paper)
      val game = new Game(winner, loser)
      val result = game.play()
      result shouldBe "Player1 win. Scissors cut Paper."
    }
  }
}
