public class BankAccount {

	private double balance;
	private String accountNumber;
	

// Constructors //	

// Default	
	public BankAccount () {
		
		balance = 0;
		accountNumber = "0001";
		
	}
	
// Takes start balance 
	public BankAccount (double startBalance) {
		
		if (startBalance >= 0) {
			balance = startBalance;
		
		} else {
			balance = startBalance;
			System.out.println("Unexpected balance"); 
		}
		accountNumber = "0001";
	} 
	
		
// Takes start balance and account number	
	public BankAccount (double startBalance, String accNum) {
		
		balance = startBalance;
		accountNumber = accNum;
	
	}	


// Mutators (Setters) //

// Subtracts withdraw amount from balance		
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

// Adds deposit amount to balance	
	public void deposit(double deposit) {
		
		if (deposit >= 0) {
			this.balance += deposit;
		} else {
			System.out.println("Can not withdraw a negative number.");
		}
	}

	
// Accessor (Getters) //	

// Returns instance variable "balance"	
	public double getBalance() {
		
		return balance;	
		
	}
	
// Returns instance variable "accountNumber"	
	public String getAccountNumber() {
		
		return accountNumber;
		
	}

// Returns the "accountNumber" and "balance"	
	public String toString() {
		
		return String.format(accountNumber + ": " + balance);
	}

	
// Main function //
	public static void main(String[] args) {	
		
	}
}
