
public class InsufficentFunds extends Exception {
	// Instance of Account class
	private Account accnt;
	/**
	 * default constructor
	 */
	public InsufficentFunds() {
		
	}
	/**
	 * class constructor with Account parameter
	 * @param accnt
	 */
	public InsufficentFunds(Account accnt) {
		this.accnt = accnt;
	}
	
	/**
	 * Method displays message when there are insufficient funds in the account 
	 * to be transfered or withdrawn from. 
	 */
	public String toString() {
		// error message
		String error = 
			"There are insufficent funds in the account to complete this transaction";
		return error;
	}	
}