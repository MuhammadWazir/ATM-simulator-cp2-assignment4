package ATM;

import java.util.Scanner;

public class Person {
	private String name;
	private int client_nb;
	private LinkedList accounts;
	private String password;
	
	//accessors
	public String getPassword() {
		return password;
	}
	public void setPassword(String p) {
		password = p;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getClient_nb() {
		return client_nb;
	}
	public void setClient_nb(int client_nb) {
		this.client_nb = client_nb;
	}
	public LinkedList getAccounts() {
		return accounts;
	}
	public void setAccounts(LinkedList accounts) {
		this.accounts = accounts;
	}
	//Constructor
	public Person(String name, int client_nb, String pw, LinkedList accounts) {
		this.name = name;
		this.client_nb = client_nb;
		this.accounts = accounts;
		this.password = pw;
	}
	//Overloaded constructor
	public Person(String name, int client_nb, String pw) {
		this.name = name;
		this.client_nb = client_nb;
		this.password = pw;
	}
	public void withdrawMoney() {
		//allows the user to withdraw an amount from one of his/her accounts. 
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your account number: ");
		int account_number = scan.nextInt();
		
		System.out.println("Enter the withdrawal amount: ");
		int withdrawal_amount= scan.nextInt();
		
		for(Node n = (getAccounts()).getHeader(); n.getNext()!=null;n=n.getNext()){
			if((n.getInfo()).getAcc_number()==account_number) {
				(n.getInfo()).setBalance((n.getInfo()).getBalance()-withdrawal_amount);
				return;
			}
		}
		System.out.println("Account not found.");
	}
	public void depositMoney() {
		//allows the user to deposit an amount to one of his/her accounts. 
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your account number: ");
		int account_number = scan.nextInt();
		
		System.out.println("Enter the deposit amount: ");
		int deposit_amount= scan.nextInt();
		
		for(Node n = (getAccounts()).getHeader(); n.getNext()!=null;n=n.getNext()){
			if((n.getInfo()).getAcc_number()==account_number) {
				(n.getInfo()).setBalance((n.getInfo()).getBalance()+deposit_amount);
				return;
			}
		}
		System.out.println("Account not found.");
	}
	public void transferMoney() {
		//allows the user to transfer money between two of his/her accounts
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of account to transfer money from: ");
		int account1_number = scan.nextInt();
		System.out.println("Enter the number of account to transfer money to: ");
		int account2_number = scan.nextInt();
		Account acc1,acc2;
		for(Node n = getAccounts().getHeader(); n.getNext()!=null;n=n.getNext()){
			if((n.getInfo()).getAcc_number()==account1_number) {
				acc1=n.getInfo();
				for(Node m = (getAccounts()).getHeader(); m.getNext()!=null;m=m.getNext()){
					if((m.getInfo()).getAcc_number()==account1_number) {
						acc2=m.getInfo();
						System.out.println("Enter transfer amount: ");
						int transfer_amount = scan.nextInt();
						acc1.setBalance(acc1.getBalance()-transfer_amount);
						acc2.setBalance(acc2.getBalance()+transfer_amount);
						return;
					}
				}
			}
		}
		System.out.println("Account not found.");
	}
}
