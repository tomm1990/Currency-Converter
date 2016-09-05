package il.ac.shenkar.project3
/*
* Designed and developed by Tom Goldberg (ID : 302815279) and Lishai Asaraf (ID : 307934414)
*/

/**
* Object Main is the main thread
*/
object Main {
  def main(args: Array[String]) {
    val xml: ParsingXML = new ParsingXML                             // creating a new ParsingXML object
    val xmlThread: Thread = new Thread(xml)                          // creating new Thread object
    xmlThread.start                                                  // start(run) on the Thread object
  }
}