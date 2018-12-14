package examplepkg

object Main {
  def main(args: Array[String]): Unit = {
    System.err.println("Running!")
    // call Java code to capture screen.
    ScreenRecorder.record()
  }
}
