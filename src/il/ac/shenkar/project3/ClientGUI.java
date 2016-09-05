package il.ac.shenkar.project3;
/*
* Designed and developed by Tom Goldberg (ID : 302815279) and Lishai Asaraf (ID : 307934414)
*/

/*
* imports dictionaries
*/
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;


/**
* Class "ClientGUI" represent the model interface (view) and makes calculations as well
*/
public class ClientGUI  {
/**
* fields declarations for class "ClientGUI"
*/
    private static Logger logger = Logger.getLogger(ClientGUI.class.getName());                 // log4j declaration
    private JFrame mainFrame, tableFrame;
    private JButton btSubmit, btTable;
    private JPanel panelFrom, panelTo, panelResult, panelBt, panelSum;
    private JList fromList , toList;
    private JTextArea fromTextArea, toTextArea ;
    private JTextField textFieldResult, amountTextField;
    private JLabel result,lastUpdate, sum;
    private ActionListener listener;                                                            // ActionListener declaration
    private JTable table;                                                                       // JTable declaration
    /**
    * "Start" Causes this thread to begin execution; the Java Virtual Machine calls the run method of this thread.
    * The result is that two threads are running concurrently: the current thread (which returns from the call to the start method)
    *  and the other thread (which executes its run method).
    * It is never legal to start a thread more than once. In particular, a thread may not be restarted once it has completed execution
    * This actually adds components into the interface with specifics properties which were selected by the authors
     */
    public void start() throws IllegalThreadStateException{
        PropertyConfigurator.configure("temp/log4j.properties");                                // Configure log4j from a file
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                               // Determine program when close button pressed
        fromList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);                         // Enable to choose only one rate from fromList
        JScrollPane JSfromlist = new JScrollPane(fromList);                                     // Makes the fromList visible
        JSfromlist.setPreferredSize(new Dimension(120, 280));                                   // Sets the dimensions of white bix which contains fromList
        toList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);                           // Enable to choose only one rate from toList
        JScrollPane JStolist = new JScrollPane(toList);                                         // Makes the toList visible
        JStolist.setPreferredSize(new Dimension(120, 280));                                     // Sets the dimensions of white bix which contains toList
        mainFrame.setLayout(new BorderLayout());                                                // Set manager Layout for mainFrame
        panelBt.add(btSubmit);                                                                  // Add the "Submit" button
        panelBt.add(btTable);                                                                   // Add the "Updated rates table"
        panelSum.add(sum, BorderLayout.NORTH);                                                  // Add the JLabel "Amount:"
        panelSum.add(amountTextField, BorderLayout.NORTH);                                      // Add the amountTextField
        panelSum.add(result, BorderLayout.SOUTH);                                               // Add the result JLabel
        panelSum.add(textFieldResult, BorderLayout.SOUTH);                                      // Add the textFieldResult TextField
        panelFrom.setLayout(new FlowLayout());                                                  // Set manager Layout for panelFrom
        panelTo.setLayout(new FlowLayout());                                                    // Set manager Layout for panelTo
        panelTo.add(JStolist);                                                                  // Add white box to attach the fromList
        panelFrom.add(JSfromlist);                                                              // Add white box to attach the toList
        amountTextField.setText("1");                                                           // Sets the default value to exchange to "1"
        amountTextField.setFont(new java.awt.Font("Tahoma", 0, 15));                            //      with new design
        mainFrame.add(panelTo, BorderLayout.EAST);                                              // Sets toList panel at EAST (right)
        mainFrame.add(panelFrom,BorderLayout.WEST);                                             // Sets fromList panel at WEST (left)
        mainFrame.add(panelBt,BorderLayout.SOUTH);                                              // Sets buttons panel at SOUTH (bottom)
        mainFrame.add(panelSum, BorderLayout.CENTER);                                           // Sets main panel at CENTER (middle interface)
        mainFrame.add(lastUpdate,BorderLayout.NORTH);                                           // Sets lastUpdate label at NORTH (up)
        mainFrame.setSize(500, 400);                                                            // Sets dimensions to the main window
        mainFrame.setVisible(true);                                                             // Sets interface visible
        btSubmit.addActionListener(listener);                                                   // Activating ActionListener on "Submit" button
        btTable.addActionListener(listener);                                                    // Activating ActionListener on "Updated rates table" button
    }
    /**
    * Constructor using fields
    */
    public ClientGUI (final Coin[] coinsArrayVal, String LastUpdate){
        /*
        * New fields objects
        */
        DefaultListModel model = new DefaultListModel();
        mainFrame = new JFrame("Currency Converter");                                           // Main window
        btSubmit = new JButton("Submit");                                                       // Submit button
        btSubmit.setFont(new java.awt.Font("Tahoma", 1, 13));                                   //    with font design
        btTable = new JButton("Updated Rates table");                                           // Updated Rates table
        btTable.setFont(new java.awt.Font("Tahoma", 1, 13));                                    //    with font design
        lastUpdate = new JLabel("Last update : " + LastUpdate);                                 // "Last update" text
        lastUpdate.setFont(new java.awt.Font("Tahoma", 1, 12));                                 //     - new design
        sum = new JLabel("Amount:");                                                            // "Amount:" text
        sum.setFont(new java.awt.Font("Tahoma", 0, 15));                                        //     - new design
        result = new JLabel("Result:  ");                                                       // "Result:" text
        result.setFont(new java.awt.Font("Tahoma", 0, 15));                                     //     - new design

        panelFrom = new JPanel();                                                               // JPanel new object
        panelTo = new JPanel();
        panelResult = new JPanel();
        panelBt = new JPanel();
        panelSum = new JPanel(new FlowLayout(0, 60, 15));                                       // JPanel with FlowLayout custom manager

        fromList = new JList(model);                                                            // JList of convert from currencies
        fromList.setFont(new java.awt.Font("Tahoma", 0, 13));                                   //    with new AMAZING font
        toList = new JList(model);                                                              // JList of convert to currencies
        toList.setFont(new java.awt.Font("Tahoma", 0, 13));                                     //    with new AMAZING font

        fromTextArea = new JTextArea();                                                         // JTextArea new objects
        toTextArea = new JTextArea();
        amountTextField = new JTextField(7);                                                    // JTextField new sized objects
        textFieldResult = new JTextField(8);

        for (int i = 0; i < 14 ; i++) {                                                         // Adding elements which represent coins
            try { model.addElement(coinsArrayVal[i].getCURRENCYCODE() + "           "           // into model type using a loop
                    + coinsArrayVal[i].getRATE());
            } catch (NullPointerException e){
                    e.printStackTrace();
                    System.out.println("Null Pointer Exception about connectivity -> (" + e.getMessage() + ")");
            }catch (ArrayIndexOutOfBoundsException e){
                e.printStackTrace();
                System.out.println("Null Pointer Exception about connectivity -> (" + e.getMessage() + ")");
            }
        }
        model.addElement("NIS" + "           " + 1);                                            // Adding element which represent Shekel

        /**
        * Defining a new listener object which represent "ActionListener" function
        * The listener interface for receiving action events. The class that is interested in processing an action event implements this interface,
        * and the object created with that class is registered with a component, using the component's "addActionListener" method.
        * When the action event occurs, that object's "actionPerformed" method is invoked.
        */
        listener = new ActionListener() {
        /**
        * Overrides "actionPerformed" Invoke when an action occurs.
        * This implementation suggest for action to occur and dealing with it as specified
        *   the program will wait until the user clicks on "Submit" or "Updated Rates table"
        */
            @Override
            public void actionPerformed(ActionEvent evt) throws ArrayIndexOutOfBoundsException{
                Object src = evt.getSource();                                                   // Declaration of source event handling object
                float rateFrom, rateTo;                                                         // Declaration of floats which takes the rates
                if (src == btSubmit) try {                                                      // If "Submit" button was pressed
                    try { rateFrom = Float.parseFloat(coinsArrayVal[fromList.getSelectedIndex()].getRATE()); }
                    catch (ArrayIndexOutOfBoundsException ex) {
                        new ArrayIndexOutOfBoundsException("test");
                        ex.printStackTrace();
                        rateFrom = (float) 1.0;
                        JFrame frame = new JFrame();
                        JOptionPane.showMessageDialog(frame, "No currency from convert was chosen\n" +
                                "Or NIS was chosen\n" + "Turning default currency -> Shekel");
                        logger.info("Turning default currency to convert from Shekel");         // log4j : new ERROR message
                    }
                    catch (NullPointerException ex) {
                        ex.printStackTrace();
                        rateFrom = (float) 1.0;
                        JFrame frame = new JFrame();
                        JOptionPane.showMessageDialog(frame, "No currency from convert was chosen\n" +
                                "Or NIS was chosen\n" + "Turning default currency -> Shekel");
                        logger.info("Turning default currency to convert from Shekel");         // log4j : new ERROR message
                    }
                    try { rateTo = Float.parseFloat(coinsArrayVal[toList.getSelectedIndex()].getRATE()); }
                    catch (ArrayIndexOutOfBoundsException e) {
                        e.printStackTrace();
                        rateTo = (float) 1.0;
                        JFrame frame = new JFrame();
                        JOptionPane.showMessageDialog(frame, "No currency to convert was chosen\n" +
                                "Or NIS was chosen\n" + "Turning default currency -> Shekel");
                        logger.info("Turning default currency to convert to Shekel");
                    }
                    float sum = Float.parseFloat(amountTextField.getText());                    // Declaration of float object "sum"
                    float result = sum * rateFrom / rateTo;                                     // The Calculation result into float object "result"
                    textFieldResult.setText(String.valueOf(result));                            // Putting result into Result JTextField
                    textFieldResult.setFont(new java.awt.Font("Tahoma", 0, 15));                //      with new font design
                    logger.info("Submit button was pressed. The exchange is :" + result);       // log4j : new INFO message
                } catch (NumberFormatException ex) {                                            // Exception :
                    JFrame frame = new JFrame();                                                // New JFrame (new window)
                    JOptionPane.showMessageDialog(frame, "Please insert valid decimal amount\n" + ex.getMessage() + " is not valid"); // Error message
                                    //      with new font design
                    logger.error("Wrong input -> (" + ex.getMessage() + ")");                   // log4j : new ERROR message
                    amountTextField.setText("");                                                // Clearing Amount JTextField
                } catch (ArrayIndexOutOfBoundsException ex) {                                   // Exception :
                    JFrame frame = new JFrame();                                                // new JFrame(new window)
                    JOptionPane.showMessageDialog(frame, "Please choose currencies to convert");// Error message
                    logger.error("Wrong input -> (The currencies has not been chosen)");        // log4j : new ERROR message
                    amountTextField.setText("");                                                // Clearing Amount JTextField
                }
                if (src == btTable) {                                                           // If "Updated Rates table" button was pressed
                    tableFrame = new JFrame("Currencies rate table");                           // New JFrame object referenced to tableFram
                    tableFrame.setLayout(new BorderLayout());                                   // Layout manager
                    tableFrame.setSize(650, 300);                                               // Dimensions of JFrame

                    Object[] columnNames = {"NAME","COUNTRY","CURRENCY CODE","RATE","CHANGE"};  // Columns Names
                    Object[][] data = new Object[14][5];                                        // Inserting data from coinsArray into matrix of data
                    for (int row = 0; row <= 13; row++){                                        //    which will be shown as a JTable with a loop
                        for (int col = 0; col <= 4; col++) {
                            switch (col) {
                                case 0:data[row][col] = coinsArrayVal[row].getNAME(); break;
                                case 1:data[row][col] = coinsArrayVal[row].getCOUNTRY();break;
                                case 2:data[row][col] = coinsArrayVal[row].getCURRENCYCODE();break;
                                case 3:data[row][col] = coinsArrayVal[row].getRATE();break;
                                case 4:data[row][col] = coinsArrayVal[row].getCHANGE();break;
                                }
                        }
                    }
                    table = new JTable(data, columnNames);                                      // New JTable object with data and the columns names
                    table.setFont(new java.awt.Font("Tahoma", 0, 15));                          //      with new design

                    tableFrame.add(new JScrollPane(table));                                     // Set it visible
                    tableFrame.setVisible(true);                                                // Set the JTable Frame visible (true)
                    }
                }
        };
    }
}