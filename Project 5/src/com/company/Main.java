package com.company;

/**
 * @author Ian MacLeod
 * This program is designed to set up a one time account for a user. The program takes the user's first and last name, their age, a password which is needed to withdraw and
 * deposit money, and an intitial deposit in the user's main account. The user also has the option to set up a savings account. Once the account is set up, the user can withdraw, deposit
 * transfer, or check their balances for both their main account and savings account
 * @Final Version
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner stdin = new Scanner(System.in);
    	boolean openApp = true;
    	boolean numberBalance = false;
		boolean inApp = true;
		double amount;
		String option;
		String toSaveorNot;
    	while(openApp){
			System.out.println("Welcome to the Bank Application! First, let's get you set up with an account: ");
			Account user = new Account();
			System.out.println("Please provide your first and last name: ");
			String fn = stdin.next();
			String ln = stdin.next();
			System.out.println("Next, please provide your age: ");
			int age = stdin.nextInt();
			System.out.println("Now input a password that will give access to your account (Don't forget it!): ");
			String password = stdin.next();
			while(numberBalance == false){
				numberBalance = true;
				System.out.println("Please provide some money for your account balance: ");
				try{
					double balance = stdin.nextDouble();
					user.setBalance(balance);
				}
				catch(Exception e){
					System.err.println("You did not enter a number!");
					user.setBalance(0.0);
					numberBalance = false;
					stdin.nextLine();
				}
			}
			System.out.println("Would you like to put an additional amount into your savings? Type 'Y' or 'N': ");
			toSaveorNot = stdin.next();
			if(toSaveorNot.toUpperCase().equals("Y")){
				System.out.println("How much? ");
				double savings = stdin.nextDouble();
				user.setSavings(savings);
				System.out.println(" ");
			}else{
				System.out.println("No problem! You can always transfer money to your savings account via the homepage!");
				System.out.println(" ");
			}
			System.out.println("Creating your new account...Done!");
			System.out.println(" ");

			//Setting all the instance variables

			user.setFirstName(fn);
			user.setLastName(ln);
			user.setAge(age);
			//user.setBalance(balance);
			user.setPassword(password);

			while(inApp){
				System.out.println("Homepage: What would you like to do? Type 'W' for withdraw, 'B' to check your balance,'D' to deposit money, 'T' to transfer between accounts, or 'done' to exit the app: ");
				option = stdin.next();
				boolean tryAgain = true;
				String input;
				String userPass;
				if(option.toUpperCase().equals("W")){
					while(tryAgain) {
						System.out.println("Please provide your password before withdrawing: ");
						userPass = stdin.next();

						if (userPass.equals(user.getPassword())) {
							System.out.println("Which account would you like to withdraw from? Type 'M' for the main account or 'S' for savings: ");
							String whichAccount = stdin.next();
							if(whichAccount.toUpperCase().equals("M")){
								System.out.print("How much money would you like to withdraw? ");
								amount = stdin.nextDouble();
								boolean withdraw = user.withdraw(amount);
								System.out.println("");
								if (withdraw) {
									System.out.println("Withdrawing $" + amount + " from your account...");
									tryAgain = false;
								}else {
									System.out.println("Sorry, there are not enough funds in your account! Redirecting you back to the homepage.");
									tryAgain = false;
								}
							}else if(whichAccount.toUpperCase().equals("S")){
								System.out.print("How much money would you like to withdraw? ");
								amount = stdin.nextDouble();
								boolean withdraw = user.withdrawSavings(amount);
								System.out.println("");
								if(withdraw){
									System.out.println("Withdrawing $" + amount + " from your savings account...");
									tryAgain = false;
								}else{
									System.out.println("Sorry, there are not enough funds in your savings account! Redirecting you back to the homepage.");
									tryAgain = false;
								}
							}else{
								System.out.println("Unknown input, redirecting back to the homepage...");
								tryAgain = false;
							}
						} else {
							System.out.println("Incorrect Password");
							System.out.println("Would you like to try again? Type 'Y' to try again or 'N' to go back to the homepage: ");
							input = stdin.next();
							if (input.toUpperCase().equals("N")) {
								tryAgain = false;
							}
						}
					}
				}else if(option.toUpperCase().equals("B")){
						user.checkBalance();
				}else if(option.toUpperCase().equals("D")){
					while(tryAgain) {
						System.out.println("Please provide your password before depositing: ");
						userPass = stdin.next();
						if(userPass.equals(user.getPassword())){
							System.out.print("How much money would you like to deposit? ");
							amount = stdin.nextDouble();
							System.out.println(" ");
							System.out.println("Which account do you want to deposit your $" + amount +" dollars into? Type 'M' for main account or 'S' for savings: " );
							String whichAccount = stdin.next();
							if(whichAccount.toUpperCase().equals("M")){
								System.out.println("Depositing " + amount + " into your main account...");
								user.deposit(amount);
								tryAgain = false;
							}else if(whichAccount.toUpperCase().equals("S")){
								System.out.println("Depositing " + amount + " into your savings account...");
								user.depositSavings(amount);
								tryAgain = false;
							}else{
								System.out.println("Unknown input, redirecting you back to the homepage...");
								tryAgain = false;
							}
						}else{
							System.out.println("Incorrect Password");
							System.out.println("Would you like to try again? Type 'Y' to try again or 'N' to go back to the homepage: ");
							input = stdin.next();
							if(input.toUpperCase().equals("N")){
								tryAgain = false;
							}
						}
					}
				}else if(option.toUpperCase().equals("T")){
					System.out.println("Would you like to transfer money to your main account or savings? Type 'M' for main or 'S' for savings: ");
					String whichAccount = stdin.next();
					if(whichAccount.toUpperCase().equals("M")){
						System.out.println("How much would you like to transfer? ");
						double amt = stdin.nextDouble();
						user.transferToMain(amt);

					}else if(whichAccount.toUpperCase().equals("S")){
						System.out.println("How much would you like to transfer to your savings? ");
						double amt = stdin.nextDouble();
						user.transferToSave(amt);
					}else{
						System.out.println("Unknown input, redirecting you back to the homepage...");
						tryAgain = false;
					}

				} else if(option.equals("done")){
					System.out.println("Thank you for using the Bank Application!");
					openApp = false;
					inApp = false;
				}else{
					System.out.println("Unknown input. Please input a valid key!");
				}
			}//end inApp
    	}//end openApp loop

    }
}
