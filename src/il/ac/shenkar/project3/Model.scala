package il.ac.shenkar.project3
/*
* Designed and developed by Tom Goldberg (ID : 302815279) and Lishai Asaraf (ID : 307934414)
*/

/*
* imports dictionaries
*/
import org.w3c.dom.NodeList
import java.io._
/**
* This Model is the heart of the project.
* It implements three main functions which has been declared int the trait : readFromFile , writeToFile , readLastUpdate
 */
class Model extends IModel {
  private val coinsArray: Array[Coin] = new Array[Coin](14)                         // Array of 14 Coins
  private var lineCounter: Int = -1                                                 // Declaration of lineCounter
  private var name: String = null                                                   // Declaration of file name
  def this(nameVal: String) {
    this()
    name = nameVal }
/**
/ readFromFile function reads from a known file and parses the data within String which declared below
 */
  def readFromFile: Array[Coin] = {
    var fstream1 : FileReader = null                                                // Declarations of File readers
    var fstream2 : FileReader = null
    var in1: BufferedReader = null                                                  // Declarations of File Buffer readers
    var in2: BufferedReader = null
    var coinName: String = null                                                     // Declarations of String which contains
    var coinCode: String = null                                                     //   the parsed data
    var coinCountry: String = null
    var coinUnit: String = null
    var coinRate: String = null
    var coinChange: String = null
    val line: String = null
    try {
      fstream1 = new FileReader(name)
      in1 = new BufferedReader(fstream1)
      while (in1.readLine != null) ({ lineCounter += 1; lineCounter - 1})           // Counting file lines
      fstream2 = new FileReader(name)
      in2 = new BufferedReader(fstream2)
      for (j<-0 to ((lineCounter - 1) / 6)) {                                       // Parsing with a loop
          { coinName = in2.readLine
            coinCode = in2.readLine
            coinCountry = in2.readLine
            coinUnit = in2.readLine
            coinRate = in2.readLine
            coinChange = in2.readLine
            coinsArray(j) = new Coin(coinName, coinCode, coinCountry,               // Making new Coin objects
                                     coinUnit, coinRate, coinChange)
          }
        }
    }
    catch { case e: FileNotFoundException => e.printStackTrace                      // Catch exceptions if needed
            case e: IOException => e.printStackTrace
    } finally { if (in1 != null) { try { in1.close }
                                   catch { case e: IOException => e.printStackTrace } }
                if (in2 != null) { try { in2.close }
                                   catch { case e: IOException => e.printStackTrace } }
    }
    return coinsArray                                                               // Return the array of Coins
  }
  /**
  * writeToFile function writes to a known file the data that has been parsed from the web
  * into different lines each time.
  */
  def writeToFile(nameList: NodeList, unitList: NodeList, currencyCodeList: NodeList, countryList: NodeList, rateList: NodeList, changeList: NodeList, lastUpdate: NodeList) {
    var out: BufferedWriter = null                                                  // Declaration of BufferedWriter
    try {
      val fstream: FileWriter = new FileWriter(name, false)                         // New FileWriter (true means to append data)
      out = new BufferedWriter(fstream)                                             // New BufferedWriter object
      var i: Int = 0
        i = 0
        while (i < nameList.getLength) {                                            // Loop that writes to file any data that has been parsed
          { out.write(nameList.item(i).getFirstChild.getNodeValue)                  // in separate lines
            out.newLine
            out.write(unitList.item(i).getFirstChild.getNodeValue)
            out.newLine
            out.write(currencyCodeList.item(i).getFirstChild.getNodeValue)
            out.newLine
            out.write(countryList.item(i).getFirstChild.getNodeValue)
            out.newLine
            out.write(rateList.item(i).getFirstChild.getNodeValue)
            out.newLine
            out.write(changeList.item(i).getFirstChild.getNodeValue)
            out.newLine
          }
          ({ i += 1; i - 1})
        }
      out.write(lastUpdate.item(0).getFirstChild.getNodeValue)                      // Writes the last update to file
      out.newLine
    }
    catch { case e: IOException => {e.printStackTrace                               // Catch exceptions of needed
                                    System.err.println("Error: " + e.getMessage)}
    } finally { if (out != null) { try { out.close }
                                   catch { case e: IOException => e.printStackTrace
        }
      }
    }
  }
  /**
  * readLastUpdate function reads the last line of a known file and insert it into var
  */
  @throws(classOf[IOException])
  def readLastUpdate: String = {
    var fstream: FileReader = null                                                  // Declaration of file reader
    var in: BufferedReader = null                                                   // Declaration of file buffer reader
    var line: String = null                                                         // Declaration of a String
    try {
      fstream = new FileReader(name)
      in = new BufferedReader(fstream)                                              // New BufferedWriter object
      val temp: Int = 0
      var counter1: Int = -1
      while (counter1 <= lineCounter) {
        if (counter1 == lineCounter) { return line }
        ({counter1 += 1; counter1 - 1})
        line = in.readLine
        }
    } catch { case e: FileNotFoundException => e.printStackTrace
    } finally { if (in != null) try { in.close }
                                catch { case e: IOException => e.printStackTrace
      }
    }
    return null                                                                     // if not succeed return null
  }
}