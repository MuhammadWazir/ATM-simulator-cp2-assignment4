package ATM;

public class Account {
	private int acc_number, balance;
	private String type;
	//accessors
	public int getAcc_number() {
		return acc_number;
	}
	public void setAcc_number(int acc_number) {
		this.acc_number = acc_number;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	//constructor
	public Account(int acc_number, int balance, String type) {
		this.acc_number = acc_number;
		this.balance = balance;
		this.type = type;
	}
}
