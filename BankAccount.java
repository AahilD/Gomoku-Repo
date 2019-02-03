/**
 * CPSC 219 GROUP 22 Team Assignment 2: Bank Account
 * 
 * @author Aahil
 * @author Emily
 * @author Emmanuel
 * @author Leslie
 * @author Steven
 */

public class BankAccount {

// Initializes the instance variables "balance" and "accountNumber".
private double balance;
private String accountNumber;
	
// Constructors //
	
/**
 * This constructor creates a new account with the balance equal to 0
 * and the accountNumber set to "0001".
 * 
 * @param none
 */
public BankAccount () {

	balance = 0;
	accountNumber = "0001";

}

/**
 * This constructor takes a single @param and creates a new account with the 
 * balance equal to the @param from user and sets the accountNumber to "0001".
 * 
 * @param startBalance
 */
public BankAccount (double startBalance) {

	balance = startBalance;
	accountNumber = "0001";
} 

/**
 * This constructor takes two @param and creates a new account with the 
 * balance equal to the first @param and the accountNumber equal to the
 * second @param.
 * 
 * @param startBalance
 * @param accNum
 */
public BankAccount (double startBalance, String accNum) {

	balance = startBalance;
	accountNumber = accNum;

}	

// Mutators (Setters) //

/**
 * This setter method subtracts the @param withdraw from the balance.
 * If the withdraw amount is greater than the balance amount or is
 * a negative number, an error message is sent.
 * 
 * @param withdraw
 */
public void withdraw(double withdraw) {

	if (withdraw >= 0) {
		if (balance >= withdraw) {
			this.balance -= withdraw;
		} else {
			System.out.println("Insufficient funds.");
		}
	} else {
		System.out.println("Can not withdraw a negative number.");
	}	
}

/**
 * This setter method adds the @param deposit from the balance.
 * If the deposit amount is less than 0, an error message is 
 * printed.
 * 
 * @param deposit
 */
public void deposit(double deposit) {

	if (deposit >= 0) {
		this.balance += deposit;
	} else {
		System.out.println("Can not withdraw a negative number.");
	}
}

// Accessor (Getters) //	

/**
 * This getter method pulls the balance and returns it.
 * 
 * @return balance
 */
public double getBalance() {

	return balance;	
		
}

/**
 * This getter method pulls the accountNumber and returns it.
 * 
 * @return accountNumber
 */
public String getAccountNumber() {

	return accountNumber;
		
}

/* 
 * This method returns accountNumber and balance as a string
 * 
 * @return accountNumber: balance
 */
public String toString() {

	return String.format(accountNumber + ": " + balance);
}

// Main function //

/**
 * This is the main method.
 * 
 * @param args
 */
public static void main(String[] args) {	

}
}
