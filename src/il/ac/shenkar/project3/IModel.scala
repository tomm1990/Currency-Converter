package il.ac.shenkar.project3
/*
* Designed and developed by Tom Goldberg (ID : 302815279) and Lishai Asaraf (ID : 307934414)
*/

/*
* imports dictionaries
*/
import org.w3c.dom.NodeList
/**
* Similar to interfaces in Java, traits are used to define object types by specifying the signature of the supported methods.
* Unlike Java, Scala allows traits to be partially implemented; i.e. it is possible to define default implementations for some methods.
* In contrast to classes, traits may not have constructor parameters.
*
* This IModel is the main trait which declaring the methods that implemented in the other files
*/
trait IModel {                                                                          // IModel trait
  /**
   * writeToFile method receives 7 node lists and write the parsed data into on single file
   * This is implemented in the Model class
   */
  def writeToFile(list1: NodeList,                                                      // writeToFile declaration
                  list2: NodeList,
                  list3: NodeList,
                  list4: NodeList,
                  list5: NodeList,
                  list6: NodeList,
                  list7: NodeList)
  /**
   * readFromFile method reads any single line within a known file document
   */
  def readFromFile : AnyRef                                                              // readFromFile declaration
}