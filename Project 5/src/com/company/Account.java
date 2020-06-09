package com.company;

import java.util.Date;

public class Account {
    //instance variables
    private String firstName;
    private String lastName;
    private int age;
    private double balance;
    private String password;
    private double savings;

    /**
     * Default Constructor that creates a new bank account with a default main account balance of $0.00 and a default savings balance of 0
     */
    public Account(){
        this.balance = 0.00;
        this.savings = 0.00;
    }


    //setter methods

    /**
     * Assigns first name to user
     * @param firstName first name to be assigned
     */
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    /**
     * Assigns last name to user
     * @param lastName last name to be assigned
     */
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    /**
     * Assigns age to user
     * @param age age to be assigned
     */
    public void setAge(int age){
        this.age = age;
    }

    /**
     * Assigns balance to account
     * @param balance balance to be assigned
     */
    public void setBalance(double balance){
        this.balance = balance;
    }

    /**
     * Assigns password to user
     * @param password password to be assigned
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * Assigns intitial balance to users savings account
     * @param savings
     */
    public void setSavings(double savings){
        this.savings = savings;
    }


    //Getter methods
    /**
     * Returns the user's password
     * @return the user's password
     */

    public String getPassword(){
        return password;
    }


    //Other methods

    /**
     * Prints the most recent summary of both a user's main account and savings account
     */
    public void checkBalance(){
        //code for instantiating a Date object to get the current time was copied from
        //https://www.javatpoint.com/java-get-current-date
        Date currentTime = new Date();
        System.out.println("Account balance as of " + currentTime + ": $"  + balance + " dollars");
        System.out.println("Savings balance as of " + currentTime + ": $" + savings + " dollars");
    }



    /**
     * Subtracts the specified amount of money from the user's balance
     * @param amt amount to be withdrawn
     * @return boolean representing whether or not there are sufficient funds in the account
     */
    public boolean withdraw(double amt){
        boolean canWithdraw = true;
        if(amt > this.balance){
            canWithdraw = false;
        }else{
            this.balance = this.balance - amt;
        }
        return canWithdraw;

    }

    /**
     *Subtracts the specified amount of money from the user's savings account
     * @param amt amout to be taken out
     * @return boolean representing whether or not there are sufficient funds in the account
     */
    public boolean withdrawSavings(double amt){
        boolean canWithdraw = true;
        if(amt > this.savings){
            canWithdraw = false;
        }else{
            this.savings = this.savings - amt;
        }
        return canWithdraw;
    }

    /**
     * Deposits a specified amount into the user's main account
     * @param amt amount to be deposited
     */
    public void deposit(double amt){
        this.balance = this.balance + amt;
    }

    /**
     * Deposits a specified amount into the user's savings account
     * @param amount
     */
    public void depositSavings(double amount){
        this.savings = this.savings + amount;
    }

    /**
     * Transfers money from savings to main account
     * @param amt money to be transferred
     */
    public void transferToMain(double amt){

        if(amt > this.savings){
            System.out.println("Sorry, there is not enough money in your savings account to transfer");
        }else{
            System.out.println("Transferring " + amt + " from your savings account to your main account...");
            this.savings = this.savings - amt;
            this.balance = this.balance + amt;
        }
    }

    /**
     * Transfers money from the main account to the savings account
     * @param amt amount to be transferred
     */
    public void transferToSave(double amt){
        if(amt > this.balance){
            System.out.println("Sorry, there is not enough money in your main account to transfer");
        }else{
            System.out.println("Transferring " + amt + " from your main account to your savings account...");
            this.balance = this.balance - amt;
            this.savings = this.savings + amt;
        }
    }

}
