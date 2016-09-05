package il.ac.shenkar.project3
/*
* Designed and developed by Tom Goldberg (ID : 302815279) and Lishai Asaraf (ID : 307934414)
*/

/**
* Class "Coin" represent the main object of this project - Currency coin
* Every constructed object from this has fields and functions
*/
class Coin {
/**
* String casted fields declarations
*/
  private var NAME: String = null
  private var UNIT: String = null
  private var CURRENCYCODE: String = null
  private var COUNTRY: String = null
  private var RATE: String = null
  private var CHANGE: String = null
  /**
   * Constructor of "Coin" using fields
   */
  def this(NAME: String, UNIT: String, CURRENCYCODE: String, COUNTRY: String, RATE: String, CHANGE: String) {
    this()
    setNAME(NAME)                                 //  set the Name field
    setUNIT(UNIT)                                 //  set the Unit field
    setCURRENCYCODE(CURRENCYCODE)                 //  set the Currency Code field
    setCOUNTRY(COUNTRY)                           //  set the Country field
    setRATE(RATE)                                 //  set the Rate field
    setCHANGE(CHANGE)                             //  set the Change field
    System.out.println("Default C'tor -> " + this.toString)
  }
  /**
  * String casted getters
  */
  def getNAME: String = { return NAME }
  def getCURRENCYCODE: String = { return CURRENCYCODE }
  def getCOUNTRY: String = { return COUNTRY }
  def getRATE: String = { return RATE }
  def getCHANGE: String = { return CHANGE }
  /**
  * String casted setters
  */
  def setNAME(NAME: String) { this.NAME = NAME }
  def setUNIT(UNIT: String) { this.UNIT = UNIT }
  def setCURRENCYCODE(CURRENCYCODE: String) { this.CURRENCYCODE = CURRENCYCODE }
  def setCOUNTRY(COUNTRY: String) { this.COUNTRY = COUNTRY }
  def setRATE(RATE: String) { this.RATE = RATE }
  def setCHANGE(CHANGE: String) { this.CHANGE = CHANGE }

  /**
  * String casted toString function ; This prints the details of the object from class "Coin"
  */
  override def toString: String = {
    return "Coin: {" + "NAME=" + NAME + ",\t\t UNIT=" + UNIT + ",\t\t CURRENCYCODE=" + CURRENCYCODE +
      ",\t\t COUNTRY=" + COUNTRY + ",\t\t RATE=" + RATE + ",\t\t CHANGE=" + CHANGE + "  }"
  }
}