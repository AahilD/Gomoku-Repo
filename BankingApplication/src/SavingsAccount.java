/**
 *  * CPSC 219
 * TEAM ASSIGNMENT #04 
 * 
 * @author GROUP22
 * @author Aahil
 * @author Emily
 * @author Emmanuel
 * @author Leslie
 * @author Steven
 * 
 * 
 * This class represents an Object of type SavingsAccount. 
 */
public class SavingsAccount extends BankAccount
{
	// This variable will store the value for the annual interest rate
	private double annualInterestRate;
	// This variable will store the value for the minimum balance the savings account must maintain
	private double minimumBalance;

	/**
	 * Default constructor for SavingsAccount
	 * Calls the parent default constructor and
	 * sets the annual interest rate with a default value of 0.05
	 */
	public SavingsAccount()
	{
		super();
		annualInterestRate = 0.05;
	}
	
	/**
	 * Call this constructor to set the default value for
	 * the annual interest rate. Also calls the parent default constructor.
	 * 
	 * @param toAnnualInterestRate variable of type double for the annual interest rate
	 */
	public SavingsAccount(double toAnnualInterestRate)
	{
		super();
		setAnnualInterestRate(toAnnualInterestRate);
	}
	
	/**
	 * Call this constructor to set the default values for
	 * the account holder, balance, and annual interest rate.
	 * 
	 * @param accountHolder Object of type Customer for the account holder
	 * @param balance of type double to set the initial value for the account balance
	 * @param toAnnualInterestRate of type double to set the initial value for the annual interest rate
	 */
	public SavingsAccount(Customer accountHolder, double balance, double toAnnualInterestRate)
	{
		// Use parent constructor that takes in the Customer object and balance
		super(accountHolder, balance);
		setAnnualInterestRate(toAnnualInterestRate);
	}

	/**
	 * Call this method to get the value for the
	 * annual interest rate
	 * 
	 * @return double annualInterestRate
	 */
	public double getAnnualInterestRate()
	{
		return annualInterestRate;
	}

	/**
	 * Call this method to deposit the monthly interest rate
	 * into the savings account
	 */
	public void depositMonthlyInterest()
	{
		deposit((getBalance() * annualInterestRate) / 12);
	}

	/**
	 * Call this method to set the annual interest rate
	 * the interest rate must be a value between, and including, 0.00 and 1.00.
	 * Anything outside these values will not change the current annual interest rate.
	 * @param toAnnualInterestRate of type double (Any value from 0.00 to 1.00)
	 */
	public void setAnnualInterestRate(double toAnnualInterestRate)
	{
		if (toAnnualInterestRate >= 0.00 && toAnnualInterestRate <= 1.00)
			annualInterestRate = toAnnualInterestRate;
	}

	/**
	 * Call this method to set the minimum balance limit
	 * @param toMinimumBalance
	 */
	public void setMinimumBalance(double toMinimumBalance)
	{
		minimumBalance = toMinimumBalance;
	}

	/** 
	 * (non-Javadoc)
	 * @see BankAccount#withdraw(double)
	 * 
	 */
	public void withdraw(double amount)
	{
		if (getBalance() - amount >= minimumBalance)
			super.withdraw(amount);
	}
}
