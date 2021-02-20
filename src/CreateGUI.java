/**
 * 			Documentation 
 *   
 *   Possible Improvements
 *   
 *  1. The appearance of the GUI. The negative space of the JFrame is wasteful. I
 *     would have liked to add additional components. Such as an instruction menu
 *     to guide the user through operation. Time become the enemy unfortunately. 
 *     See Point #3 from Lessons Learned.
 *   
 *  2. Improving the Account class. The class functions properly, but I think 
 *  	   there are better ways to achieve the same result. For example, when 
 *  	   calculating the number of withdrawals, I use a counter from CreateGUI class
 *  	   that is passed to Account along with the user input. The setWithdraw method
 *  	   then needs to have double and int arguments to match. Therefore, "Transfer
 *  	   To" button also withdraws so the counter is passed with the input again. 
 *  	   I think it's unnecessary and I just used there as a sort of placeholder 
 *  	   to match the parameters of setWithdraw. 
 *  
 *  3. Displaying the balance in JOptionPane. I wanted to use a method to display
 *     the balance to minimize code re-usage, but couldn't properly get the feature
 *     to function.
 * 
 * 		Test Case 1 - Verifying Withdraw from Savings
 * 
 * 	1. Select savings button.
 * 	2. Type in 40.5 in text field and select withdraw button
 * 	3. Verify JOptionPane appears with message that withdrawals are only 
 * 	   allowed in increments in 20.
 * 	4. Select balance button 
 * 	5. Confirm balance is larger than 20.
 * 	6. Type 20 into text field and select withdraw button.
 * 	7. Confirm that JOptionPane message appears and confirms the successful
 * 	   withdrawal.
 * 	8. Click balance button to confirm 20 was successfully added to the 
 * 	   savings balance.
 * 				
 * 		Test Case 2 - Transfer Funds from Checking to Savings
 * 
 * 	1. Select checking button.
 * 	2. Select balance button.
 * 	3. Confirm JOptionPane window appears and shows the balance in checking is 
 * 	   larger than 5. 
 * 	4. Take note of the balance from step 3, it will be needed to confirm a 
 * 	   successful transfer.
 * 	5. Unselect checking button.
 * 	6. Select savings button and balance button.
 * 	7. Confirm JOptionPane window appears and shows the balance of savings.
 * 	8. Take note of the balance from step 7, it will be needed to confirm a
 * 	   successful transfer.
 * 	9. Type, "Email", into the text field and select the Transfer To button.
 * 	10. Confirm JOptionPane window appears with the message that only numeric
 * 	    input is accepted.
 * 	11. Type, 5, into the text field and select the Transfer To button.
 * 	12. Confirm JOptionPane window appears confirming the transaction. 
 * 	13. With savings button still selected, select balance. 
 * 	14. Confirm JOptionPane window appears showing the balance in savings 
 * 		is the balance from step 8 after adding 5.
 * 	15. Unselect savings button.
 * 	16. Select checking button and balance.
 * 	17. Confirm JOptionPane window appears showing the balance in checking is
 * 		the balance from step 4 minus 5.
 * 	
 * 		Test Case 3 - Withdraw Funds from Checking When Balance is Insufficient
 * 
 * 	1. Select checking button.
 * 	2. Select balance button.
 * 	3. Confirm JOptionPane window appears and shows the balance of the checking.
 * 	4. Take note of the balance from step 3. 
 * 	5. Type an amount into the text field that is larger than the balance shown 
 * 	   from steps 3 and 4.
 * 	6. With checking still selected, select the withdraw button. 
 * 	7. Confirm JOptionPane window appears and displays a message that there are not
 * 	   sufficient funds to complete the withdrawal. 
 * 
 * 		Test Case 4 - Verify Service Charge Deduction from Checking Account 
 * 
 * 	1. Select checking button.
 * 	2. Type, 200, into text field.
 * 	3. Select deposit button.
 * 	4. Type, 20, into text field.
 * 	5. Select withdraw button.
 * 	6. Repeat steps 4 through 5 four times. 
 * 	7. With checking button still selected, select balance button. 
 * 	8. Take note of the balance. 
 * 	9. Type, 20, into text field. 
 * 	10. Select withdraw button.
 * 	11. Select balance button.
 * 	12. Confirm balance in checking account is the balance from step 8 minus 21.50.
 */			

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

/**
* This program creates a GUI that will act as an ATM machine. The user 
* will be able to interact with the application by withdrawing, depositing, 
* transferring, and checking the balance of two accounts, checking and savings. 
*
* @author Brian LeProwse
* @since 09.25.2017
*/
public class CreateGUI extends InsufficentFunds {
	
	// Objects of Account class for checking & savings balance
	private Account accCheck, accSave;
	// Hold user input from text field
	private double input;
	// create JFrame
	private JFrame box;
	// Create JPanel to hold buttons and textField
	private JPanel buttons, textField;
	// Buttons for ATM operation 
	private JButton withdraw, deposit, transfer, balance;
	// Radio buttons for checking and savings
	private JRadioButton checking, savings;
	// Text field for user input
	private JTextField userInput;
	// Format decimal values
	private DecimalFormat decimalOutput; 
	// String to hold decimal formatted numbers
	private String outputDecFormat;
	// JOptionPane error messages
	private String invalidWithdraw;
	private String confirmTransaction;
	private String balanceOutput;
	// number of withdrawals 
	private int countWithdraw;

	/**
	 * Constructor builds the GUI. 
	 * @param accCheck, object from Account class for checking
	 * @param accSave, object from Account class for savings
	 */
	public CreateGUI(Account accCheck, Account accSave) {
		this.accCheck = accCheck;
		this.accSave = accSave;
	
	// create JFrame 
	box = new JFrame("ATM Machine");
		
	// Create layout of frame
	box.setLayout(new BorderLayout());
		
	// JPanel to hold buttons and text field with grid and flow layouts
	buttons = new JPanel(new GridLayout(4,0,10,10));
	textField = new JPanel(new FlowLayout(1,0,0));
		
	// Set panels background color
	buttons.setBackground(Color.GRAY);
	textField.setBackground(Color.GRAY);
		
	// Add panels to frame and define their location 
	box.add(buttons, BorderLayout.NORTH);
	box.add(textField, BorderLayout.CENTER);
		
	// Set non specific location of JFrame
	box.setLocationRelativeTo(null);
	// Set size of JFrame
	box.setPreferredSize(new Dimension(400,300));
	// Stop JFrame run when closed 
	box.setDefaultCloseOperation(box.EXIT_ON_CLOSE);
		
	// JButtons for assignment 
	withdraw = new JButton("Withdraw");
	deposit = new JButton("Deposit");
	transfer = new JButton("Transfer To");
	balance = new JButton("Balance");
	// JRadio buttons for checking and savings
	checking = new JRadioButton("Checking");
	savings = new JRadioButton("Savings");
	// Text field for user input
	userInput = new JTextField(20);

	// add buttons to buttons panel
	buttons.add(withdraw);
	buttons.add(deposit);
	buttons.add(transfer);
	buttons.add(balance);
	buttons.add(checking);
	buttons.add(savings);
		
	// add text field to panel
	textField.add(userInput);
		
	// Output error messages to one string
	invalidWithdraw = "Withdrawals only allowed in increments of 20.";
	confirmTransaction = "Transaction Successful.";
	balanceOutput = "Current Balance: " + "$";
		
	// format double values to two decimal places
	decimalOutput = new DecimalFormat("#,###.00");
		
	// show GUI
	box.pack();
	box.setVisible(true);
		
	/**
	 * withdraw button is being added to an ActionListener to be 
	 * functional. actionPerformed method performs various checks 
	 * once the withdraw button is selected.
	 * @param e, ActionEvent when withdraw button is selected
	 */
	withdraw.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		
	try {
		// Parse user input from text field and set it to var input
		input = Double.parseDouble(userInput.getText());
		
		// Check if checking and withdraw buttons are selected
		if (checking.isSelected() && e.getSource() == withdraw) {
				
			// Check if input is in increments of 20
			if (input % 20 == 0) {

				// Count number of withdrawals 
				countWithdraw++;
				
				// Withdraw user input from checking, pass # of withdrawals 
				accCheck.setWithdraw(input, countWithdraw);
				
				// Method to display confirmation of transaction
				confirmTrans();
				
			} else {
				// Method to display invalid withdraw
				invalidWith();
			}
				
		// Check if savings and withdraw buttons are selected
		} else if (savings.isSelected() && e.getSource() == withdraw) {
			
			// Check if input is in increments of 20
			if (input % 20 == 0) {
					
			// Count number of withdrawals 
			countWithdraw++;
			
			// Withdraw user input from savings, pass # of withdrawals
			accSave.setWithdraw(input, countWithdraw);
				
			// Confirm transaction message
			confirmTrans();
				
			} else {
				// Method to display invalid withdraw
				invalidWith();
			}		
		}
		// Catch exception if user input is not numeric
		} catch (IllegalArgumentException ex) {
			
			// Display IllegalArgumentException message
			exceptionOut();
				
		// Catch exception if insufficient funds in account
		} catch (InsufficentFunds ex) {
			JOptionPane.showMessageDialog(box, ex);
		}
			// Clear the text field 
			clearTextField();
		}
	});
	
	/**
	 * deposit button added to an ActionListener to be functional.
	 * actionPerformed method goes through various checks once the 
	 * deposit button is selected.
	 * @param e, ActionEvent when deposit is selected.
	 */
	deposit.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		
		try {
			// Parse user input from text field and set it to var input
			input = Double.parseDouble(userInput.getText());
		
			// Check if checking and deposit buttons are selected
			if(checking.isSelected() && e.getSource() == deposit) {
				
				// Deposit input to checking account
				accCheck.setDeposit(input, countWithdraw);
				
				// Confirm transaction
				confirmTrans();
					
			// check if savings and deposit buttons are selected
			}  else if(savings.isSelected() && e.getSource() == deposit) {
							
				// Deposit input to savings account
				accSave.setDeposit(input, countWithdraw);
				
				// Confirm transaction
				confirmTrans();
				
			}
		// Catch exception if user input is not numeric
		} catch (IllegalArgumentException ex) {
		
			// Display IllegalArgumentException message
			exceptionOut();
		}
			// Clear the text field 
			clearTextField();
		}
	});
	
	/**
	 * balance button added to an ActionListener to be functional.
	 * actionPerformed method goes through various checks once the
	 * balance button is selected.
	 * @param e, ActionEvent when balance is selected
	 */
	balance.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		
			// Check if checking and balance buttons are selected 
			if (checking.isSelected() && e.getSource() == balance) {
		
				// Message with balance formatted and condensed
				outputDecFormat = 
					balanceOutput + decimalOutput.format(accCheck.getBalance());
			
				// Display JOptionPane formatted balance
				JOptionPane.showMessageDialog(box, outputDecFormat);
			
			// Check if savings and balance buttons are selected
			} else if (savings.isSelected() && e.getSource() == balance) {
			
				// Message with balance formatted and condensed
				outputDecFormat = 
					balanceOutput + decimalOutput.format(accSave.getBalance());
			
				// Display JOptionPane formatted balance
				JOptionPane.showMessageDialog(box, outputDecFormat);
			}
			// Clear the text field 
			clearTextField();
		}
	});
	
	/**
	 * transfer button added to an ActionListener to be functional.
	 * actionPerformed method goes through various checks once the
	 * transfer button is selected
	 * @param e, ActionEvent when transfer is selected
	 */
	transfer.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		
		try {
			// Parse user input from text field and set it to var input
			input = Double.parseDouble(userInput.getText());
		
				// Check if checking and transfer buttons are selected
				if (checking.isSelected() && e.getSource() == transfer) {
				
					/* Transfer user input to checking. Use savings balance 
					 to ensure funds are sufficient for transfer */
					accCheck.setTransfer(input, accSave.getBalance(), countWithdraw);
				
					// Withdraw transfer amount from savings, pass counter to Account 
					accSave.setWithdraw(input, countWithdraw);
				
					// Display confirmation message
					confirmTrans();
				
				// Check if savings and transfer buttons are selected
				} else if (savings.isSelected() && e.getSource() == transfer) {
				
					/* Transfer user input to checking. Use savings balance 
					 to ensure funds are sufficient for transfer */
					accSave.setTransfer(input, accCheck.getBalance(), countWithdraw);
				
					// Withdraw transfer amount from checking, pass counter to Account
					accCheck.setWithdraw(input, countWithdraw);
				
					// Display confirmation message
					confirmTrans();
				}	
				
			// Catch exception if user input is not numeric	
			} catch (IllegalArgumentException ex) {
				
				// Display IllegalArgumentException message
				exceptionOut();
				
			// Catch exception if insufficient funds in account		
			} catch (InsufficentFunds ex) {
				JOptionPane.showMessageDialog(box, ex);
			}
			// Clear the text field 
			clearTextField();
		}			
	});
} 
	
	/**
 	* Method clears the textField after any button is pressed.
	*/
	public void clearTextField() {
		// set textField blank
		userInput.setText("");
	}
	
	/**
	* Method displays JOptionPane message for an invalid withdrawal
	*/
	public void invalidWith() {
		JOptionPane.showMessageDialog
			(box, invalidWithdraw, "ALERT", JOptionPane.OK_CANCEL_OPTION);
	}
	
	/**
	* Method displays JOptionPane message confirming transaction
	*/
	public void confirmTrans() {
		JOptionPane.showMessageDialog
			(box, confirmTransaction, "", JOptionPane.CLOSED_OPTION);	
	}
	
	/**
	* Method displays JOptionPane message displaying the balance of selected
	* account. The balance will formatted to two decimal places.
	* @param in, in is used as placeholder to display formatted balance	
	* 
 	*/  
	public void checkBalance(double in) {
		// format string for balance output
		outputDecFormat = 
				balanceOutput + decimalOutput.format(accCheck.getBalance());
		
		JOptionPane.showMessageDialog(box, outputDecFormat);
	}
	
	/**
	* Method displays JOptionPane error message if user input is not numeric
 	*/
	public void exceptionOut() {
		JOptionPane.showMessageDialog
			(box, "Values must be numeric!", "ALERT", JOptionPane.OK_CANCEL_OPTION);
	}
	
	/**
	 * main method creates two instances of Account class, checkAccnt for checking
	 * and saveAccnt for the savings. Also an instance of createGUI class with
	 * the two Account objects as arguments.
	 * @param args 
	 */
	public static void main(String[] args) {
		
		// Account objects for checking and savings
		Account checkAccnt = new Account();
		Account saveAccnt = new Account();
		// create object of createGUI to run 
		CreateGUI gui = new CreateGUI(checkAccnt, saveAccnt);
	}
} 
