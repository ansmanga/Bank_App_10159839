package org.example;

import java.util.HashMap;
import java.util.Map;

public class ICICI implements RBI{

    float balance = 0.0f;
    int counter = 3;

    float minBalance = 1000.0f;

    Map<String, Float> loanTypeROIHashMap = new HashMap<>();

    public ICICI(float balance)
    {
        this.balance = balance;
        loanTypeROIHashMap.put("Home", 5.0f);
        loanTypeROIHashMap.put("Car", 3.0f);
        loanTypeROIHashMap.put("Personal", 6.0f);
        loanTypeROIHashMap.put("Education", 8.0f);
    }

    public float depositMoney(float balanceToDeposit)
    {
        return balanceToDeposit;
    }

    public float withdrawMoney(float balanceToWithdraw, float currAmount) {
        float penalty_amount = 0.0f, total_withdraw_amount;


        if (counter == 0) {
            penalty_amount = (float) (0.01 * balanceToWithdraw);
        }

        total_withdraw_amount = penalty_amount + balanceToWithdraw;

        if ((currAmount - total_withdraw_amount) <= 1000) {
            System.out.println("You do not have enough balance to withdraw");
        } else {
            currAmount -= total_withdraw_amount;
        }

        if (counter > 0)
            counter--;

        return total_withdraw_amount;
    }

    public void openFD(float amount, float ROI, int years) {



        float tempAmount = amount;
        int year = 1;
        while(years-- > 0)
        {
            tempAmount += tempAmount*(ROI/100);
            System.out.println("Year:" + year + " " + tempAmount);
            year++;
        }

        System.out.println("Total Profit: " + (tempAmount - amount));

    }


    public void applyLoan(int loanType, float amount, int years) {

        float ROI = 0.0f;

        if(loanType == 1)
        {
            ROI = loanTypeROIHashMap.get("Home");

        }

        else if(loanType == 2)
        {
            ROI = loanTypeROIHashMap.get("Car");
        }

        else if(loanType == 3)
        {
            ROI = loanTypeROIHashMap.get("Personal");
        }

        else if(loanType == 4)
        {
            ROI = loanTypeROIHashMap.get("Education");
        }

        float loan = amount;
        float total_interest = 0.0f;
        while(years-- > 0)
        {
            amount = amount + (ROI/100 *amount);

        }

        System.out.println("Your interest on loan is: ");
        System.out.println(amount-loan);

        float bankTotalProfit = amount - loan;

        float amountPaidToRBI = rbiLoanInterest/100*bankTotalProfit;

        float amountRemaining = bankTotalProfit - amountPaidToRBI;

        System.out.println("The amount remaining");
        System.out.println(amountRemaining);

        balance += bankTotalProfit;

        System.out.println("Now the bank balance is: " + balance);

    }

    public void applyCreditCard(float currBalance)
    {
        if(currBalance < (2*minBalance))
        {
            System.out.println("You are not eligible for credit card");
        }
        else
        {
            System.out.println("You are eligible for the credit card");
        }
    }
}
