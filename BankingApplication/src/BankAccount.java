/**
 * Group 22
 * @author Aahil
 * @author Emily
 * @author Emmanuel
 * @author Leslie
 * @author Steven
 * 
 * @version 1.0 CPSC 219 Assignment 2
 * 
 * This class is the implementation of an Object of type BankAccount
 * which which stores a Customer Object, accountNumber of type String and 
 * a balance of type double
 *
 */
public class BankAccount
{
	// Customer object associated with this bank account
	private Customer accountHolder;
	// the account number for this bank account
	private String accountNumber;
	// the balance amount for this bank account
	private double balance;

	/**
	 * default constructor with no arguments default account number will be set
	 * to: 0001 default balance will be set to: 0.0
	 */
	public BankAccount()
	{
		this.setAccountHolder(null);
		this.accountNumber = "0001";
		this.balance = 0.0;
	}

	/**
	 * This constructor takes in the value to assign to the account balance
	 * 
	 * @param balance will set the value of the balance
	 */
	public BankAccount(double balance)
	{
		this.accountNumber = "0001";
		this.balance = balance;
	}

	/**
	 * This constructor will initialize the account number and balance with the
	 * values of the provided arguments
	 * 
	 * @param balance will set the value of the balance
	 * @param accountNumber will set the value of the account number
	 */
	public BankAccount(double balance, String accountNumber)
	{
		this.accountNumber = accountNumber;
		this.balance = balance;
	}

	/**
	 * Use this constructor to initialize a BankAccount object
	 * with a pre-defined customer object and balance amount
	 * @param toCustomer Object of type Customer as default customer
	 * @param toBalance value of type double for the default balance
	 */
	public BankAccount(Customer toCustomer, double toBalance)
	{
		// PRIVACY LEAK
		// the following line passes a reference to the object; therefore, this is a privacy leak
		this.accountHolder = toCustomer;
		this.balance = toBalance;
	}

	/**
	 * Use this constructor to create a duplicate (copy)
	 * of an object of type BankAccount.
	 * @param bankAccount Object of type BankAccount from which to duplicate
	 */
	public BankAccount(BankAccount bankAccount)
	{
		this.accountNumber = bankAccount.getAccountNumber();
		this.balance = bankAccount.getBalance();
		// POSSIBLE PRIVACY LEAK
		// this could be a privacy leak if getAccountHolder()
		// and setAccountHolder() has a privacy leak
		// but if getAccountHolder has no privacy leak, this will be fine.
		this.setAccountHolder(bankAccount.getAccountHolder());
	}
	
	/**
	 * Call this method to set a Customer as the Account Holder
	 * @param toCustomer Object of type Customer to set as the Account Holder
	 */
	public void setAccountHolder(Customer toCustomer)
	{
		//PRIVACY LEAK
		// the following line contains a privacy leak;
		// this.accountHolder is storing a reference toCustomer
		this.accountHolder = toCustomer;
	}

	/**
	 * Returns the current value of the account number
	 * 
	 * @return String value of the current account number
	 */
	public String getAccountNumber()
	{
		return this.accountNumber;
	}

	/**
	 * Returns the current value of the account balance
	 * 
	 * @return Double: balance the current value of the account balance
	 */
	public double getBalance()
	{
		return this.balance;
	}
	
	/**
	 * Call this method to get the the Customer
	 * associated to this bank account.
	 * @return Customer customer currently associated to this account
	 */
	public Customer getAccountHolder()
	{
		// PRIVACY LEAK
		// the following line returns a reference to the Customer object
		// therefore, the following line poses as a privacy leak.
		return this.accountHolder;
	}

	/**
	 * This method will add the funds into the account balance. If amount being
	 * deposited is less than 0 then the transaction is cancelled (nothing will
	 * happen).
	 * 
	 * @param amount the amount of funds to deposit to the account
	 */
	public void deposit(double amount)
	{
		if (amount >= 0)
		{
			this.balance += amount;
		}
	}

	/**
	 * This method will subtract the amount from the account balance. If the
	 * amount being withdrawn is more than the available funds, or if the amount
	 * is less than 0, the transaction will be cancelled (nothing will happen).
	 * 
	 * @param amount the amount of funds to withdraw from the account
	 */
	public void withdraw(double amount)
	{
		if (amount <= this.balance && amount >= 0)
		{
			this.balance -= amount;
		}
	}


	/**
	 * This method will transfer funds from this account to 
	 * the specified bank account that is passed in as an argument.
	 * The specified amount will simultaneously be withdrawn from this account.
	 * @param amount int value of the amount to be transfered
	 * @param toAccount object of type BankAccount that will recieve the transfer amount
	 */
	public void transfer(int amount, BankAccount toAccount)
	{
		if (amount <= this.balance)
		{
			this.withdraw(amount);
			toAccount.deposit(amount);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString() returns a formatted string containing
	 * the details of this bank account.
	 */
	@Override
	public String toString()
	{
		return "(" + this.accountHolder.toString() + ") " + 
				this.accountNumber + ": " + this.balance;
	}

}
