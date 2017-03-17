package op.challenge

trait ConsoleContext extends DialogContext {
  val dialog: Dialog = new ConsoleLike
}
object CliAppGamePlay extends App with GamePlay with ConsoleContext {
  play(true)
}