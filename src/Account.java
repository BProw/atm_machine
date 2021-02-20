// import Math.random()
import java.lang.Math;

public class Account extends InsufficentFunds {
	// values for each button from CreateGUI
	private double withdraw, deposit, transfer;
	// set balance in each account to random double number, 0 to 99.
	private double balance = (Math.random() * 100);
	// count number of withdraws
	private int countWithdraw;
	// service charge for more than 4 total withdraws
	private double serviceCharge;
	
	/**
	 * setWithdraw is the setter for withdraw. Method will withdraw 
	 * funds from the balance. The account being withdrawn from is 
	 * determined from createGUI class. The method will also count the 
	 * number of withdraws and if this count exceeds 4, a service charge 
	 * of 1.5 will be deducted from the balance of the selected account.
	 * If funds are not sufficient, the InsufficentFunds exception will 
	 * produce an error.
	 * @param input user entered number from createGUI class
	 * @throws InsufficentFunds if withdraw > than funds in account
	 */
	
	public void setWithdraw(double input, int count) throws InsufficentFunds {
	// set input to withdraw
	this.withdraw = input;	
	this.countWithdraw = count;
	// set service charge
	serviceCharge = 1.5; 

		/* Check that input isn't greater than the balance in account
		and check number of withdrawals hasn't exceeded 4 */
		if(input < balance && countWithdraw <= 4) {
			
			// Subtract user input from balance
			balance -= input;	
			
		// After 4 withdraws, subtract service charge
		} else if (input < (balance - serviceCharge)) {
		
			balance -= input;	
			
			// Subtract service charge from balance
			balance -= serviceCharge;	
			
		} else {
			// Error if withdraw > amount in selected account
			throw new InsufficentFunds(); 
		}
	}
	
	/**
	 * setDeposit is the setter for deposit. Accepts amount to deposit then 
	 * adds that amount to the balance of the selected account determined from
	 * the CreateGUI class
	 * @param input, user input to be deposited from CreateGUI
	 */
	public void setDeposit(double input, int count) {
		this.deposit = input;
		balance += input; 	
	}
	
	/**
	 * This method transfers funds from one account to the other. if input is less
	 * than the balance in the account to be transferred from, the input is deposited
	 * with the setDeposit() method. Funds are withdrawn from the transfer account in the
	 * CreateGUI class. Method will also confirm number of withdrawals is not greater than 4,
	 * if withdrawals are greater than 4 (including transfers) and funds are sufficient, 
	 * transfer will process and service charge will be deducted from account being transfered. 
	 * @param input, user transfer amount
	 * @param otherAccBalance, balance of account to be withdrawn from
	 * @throws InsufficentFunds
	 */
	public void setTransfer(double input, 
						    double otherAccBalance, 
						    int count) throws InsufficentFunds {
		
		// Set transfer to user input
		this.transfer = input;
		this.countWithdraw = count;
		
		// Check for sufficient funds and withdrawals wont incur service charge
		if (input < otherAccBalance && countWithdraw <= 4) {
			// Deposit input into selected account
			setDeposit(input, countWithdraw);
			
		// Check if funds are sufficient with service charge deduction 
		} else if (input < otherAccBalance) {
			setDeposit(input, countWithdraw);
		} else {
			// Error if insufficient funds in account
			throw new InsufficentFunds(this);
		}
	}
	
	/**
	 * Method returns the balance in selected account	
	 * @return balance of selected account
	 */
	public double getBalance() {
		return balance;
	}
}