package il.ac.shenkar.project3
/*
* Designed and developed by Tom Goldberg (ID : 302815279) and Lishai Asaraf (ID : 307934414)
*/

/*
* imports dictionaries
*/
import javax.xml.transform.TransformerException
import org.apache.log4j.{PropertyConfigurator, Logger}
import org.w3c.dom.Document
import org.w3c.dom.NodeList
import org.xml.sax.SAXException
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException
import java.io.{File, PrintWriter, IOException, InputStream}
import java.net.{MalformedURLException, HttpURLConnection, URL, UnknownHostException}


/**
* class ParsingXML parses the content from the site of the bank of Israel
 * into NodeLists which contains any single row from the xml and categorized
 * This runs as a Runnable object
*/
class ParsingXML extends Runnable{                                                        // declaration of the class
  private val logger: Logger = Logger.getLogger(classOf[ParsingXML].getName)              // log4j declaration
  PropertyConfigurator.configure("temp/log4j.properties")                                 // log4j new configuration
  private var nameList: NodeList = null                                                   //
  private var unitList: NodeList = null                                                   //
  private var currencyCodeList: NodeList = null                                           //
  private var countryList: NodeList = null                                                // declarations of fields
  private var rateList: NodeList = null                                                   // of class ParsingXML
  private var changeList: NodeList = null                                                 //
  private var lastUpdate: NodeList = null                                                 //
  private var lastUpdateTime: String = null                                               //
  private var file : Model = null                                                          //
  private var is: InputStream = null                                                      //
  private var con: HttpURLConnection = null                                               //
  private var i = 0
  /**
   * If this thread was constructed using a separate Runnable run object, then that Runnable object's run method is called;
   * otherwise, this method does nothing and returns.
   * Subclasses of Thread should override this method.
   */
  def run {                                                                               // run object
    var coinArray: Array[Coin] = null                                                     // Array of Coins
    while(true){                                                                          // While(true) loop
    try {
      file = new Model("Currency.txt")                                                    // Makes a new stream
      val url: URL = new URL("http://www.boi.org.il/currency.xml")
      con = url.openConnection.asInstanceOf[HttpURLConnection]
      con.setRequestMethod("GET")
      con.connect
      is = con.getInputStream
      val factory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance
      val builder: DocumentBuilder = factory.newDocumentBuilder
      val doc: Document = builder.parse(is)
      nameList = doc.getElementsByTagName("NAME")                                         // Parsing data into nodelists
      unitList = doc.getElementsByTagName("UNIT")
      currencyCodeList = doc.getElementsByTagName("CURRENCYCODE")
      countryList = doc.getElementsByTagName("COUNTRY")
      rateList = doc.getElementsByTagName("RATE")
      changeList = doc.getElementsByTagName("CHANGE")
      lastUpdate = doc.getElementsByTagName("LAST_UPDATE")                                // Inserting the lastUpdate
      file.writeToFile(nameList, unitList, currencyCodeList,                              // Writing the nodelists to File
                        countryList, rateList, changeList, lastUpdate)
      coinArray = file.readFromFile                                                       // Read from the File into array of coins
      lastUpdateTime = file.readLastUpdate                                                // Read from the File the last update
      System.out.println("The last update is: " + lastUpdateTime)
    }
    catch {                                                                               // Exceptions cases :
      case e: UnknownHostException => {                                                   // ? Unable to connect
        e.printStackTrace
        System.out.println("Unable to connect the server. taking data from the last update.")
        coinArray = file.readFromFile                                                     // ! Takes data from the last update
        lastUpdateTime = file.readLastUpdate+"  (Offline mode)"
        logger.error("Problem with parsing from net -> Offline mode")                     // log4j: new ERROR message
      }
      case e : TransformerException => {                                                  // ? Unable to transform
        e.printStackTrace
        println("Something went wrong-> ("+e.getMessage+")")
        coinArray = file.readFromFile                                                     // ! Takes data from the last update
        lastUpdateTime = file.readLastUpdate+"  (Offline mode)"
        logger.error("Problem with parsing from net -> Offline mode")                     // log4j: new ERROR message

      }
      case e : SAXException => {                                                          // ? Module problems
        e.printStackTrace
        println("Something went wrong-> ("+e.getMessage+")")
        coinArray = file.readFromFile                                                     // ! Takes data from the last update
        lastUpdateTime = file.readLastUpdate+"  (Offline mode)"
        logger.error("Problem with parsing from net -> Offline mode")                     // log4j: new ERROR message

      }
      case e : IOException => {                                                           // ? Files I/O streams problem
        e.printStackTrace
        println("Something went wrong-> ("+e.getMessage+")")
        coinArray = file.readFromFile                                                     // ! Takes data from the last update
        lastUpdateTime = file.readLastUpdate+"  (Offline mode)"
        logger.error("Problem with parsing from net -> Offline mode")                     // log4j: new ERROR message
      }
      case e : ParserConfigurationException => {                                          // ? Parser Problems
        e.printStackTrace
        println("Something went wrong-> ("+e.getMessage+")")
        coinArray = file.readFromFile                                                     // ! Takes data from the last update
        lastUpdateTime = file.readLastUpdate+"  (Offline mode)"
        logger.error("Problem with parsing from net -> Offline mode")                     // log4j: new ERROR message
      }
      case e : MalformedURLException => {                                                 // ? URL connectivity problem
        e.printStackTrace
        println("Something went wrong-> ("+e.getMessage+")")
        coinArray = file.readFromFile                                                     // ! Takes data from the last update
        lastUpdateTime = file.readLastUpdate+"  (Offline mode)"
        logger.error("Problem with parsing from net -> Offline mode")                     // log4j: new ERROR message
      }
      case e : NullPointerException => {                                                  // ? NullPointerException
        e.printStackTrace
        println("Something went wrong-> ("+e.getMessage+")")
        coinArray = file.readFromFile                                                     // ! Takes data from the last update
        lastUpdateTime = file.readLastUpdate+"  (Offline mode)"
        logger.error("Problem with parsing from net -> Offline mode")                     // log4j: new ERROR message
      }
    }
    finally {                                                                             // Finally always occurs
      /*
      * This while loop iterates once in order to prevent opening several interfaces at the same time
       */
      while (i!=1){
      val gui : ClientGUI = new ClientGUI(coinArray, lastUpdateTime)                      // New ClientGUI object
      gui.start                                                                           // Start the GUI operations
      i+=1
      if (is != null) { try { is.close }                                                  // Close parser condition
        catch { case e: IOException =>  e.printStackTrace  }
      }
      if (con != null) { try {con.disconnect}                                             // Close connection condition
        catch { case e : UnknownHostException=> e.printStackTrace }
      }}
      println("After the update")                                                         // Indicates data was updated
      Thread.sleep(600000)                                                                // Wait 10 minutes till the next loop
      println("Updating...")                                                              // Indicates data is being updating
    }
  }
  }
}