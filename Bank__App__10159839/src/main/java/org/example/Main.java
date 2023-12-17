package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Main implements Runnable {

    BufferedReader buff;
    InputStreamReader isr;

    public Main() {
        if (isr == null)
            isr = new InputStreamReader(System.in);
        if (buff == null)
            buff = new BufferedReader(isr);

        customerAccount = new HashMap<>();
        bankNumberOfCustomer = new HashMap<>();
    }

    int selectedBank, selectedOperation;
    HashMap<String, ArrayList<Integer>> customerAccount;
    HashMap<Integer, ArrayList<Customer>> bankNumberOfCustomer;


    public void run() {
        Main obj = this;

        while (true) {

            System.out.println("Welcome to IBS\nPlease select your bank\n1. ICICI\n2. HDFC\n3. SBI\n4. AXIS\n5. IDFC");

            try {
                obj.selectedBank = Integer.parseInt(obj.buff.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Customer Selected " + obj.selectedBank);


            Customer cus1 = new Customer();
            System.out.println("Enter your Name: ");
            try {
                cus1.customerName = obj.buff.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Enter your Email: ");
            try {
                cus1.customerEmail = obj.buff.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Enter your Address: ");
            try {
                cus1.customerAddress = obj.buff.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Enter your Gender: ");
            try {
                cus1.customerGender = obj.buff.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Enter your Aadhar: ");
            try {
                cus1.customerAadhar = obj.buff.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Enter your Phone Number: ");
            try {
                cus1.customerPhone = obj.buff.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Enter your Balance: ");
            try {
                cus1.balance = Float.parseFloat(obj.buff.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            cus1.setCustomerName(cus1.customerName);
            cus1.setCustomerEmail(cus1.customerEmail);
            cus1.setCustomerAddress(cus1.customerAddress);
            cus1.setCustomerGender(cus1.customerGender);
            cus1.setCustomerAadhar(cus1.customerAadhar);
            cus1.setCustomerPhone(cus1.customerPhone);
            cus1.setBalance(cus1.balance);

            RBI bank=null;
            if (obj.selectedBank == 1) {
                float customer_balance = cus1.getBalance();
                bank = new ICICI(customer_balance);
            }

            else if(obj.selectedBank == 2)
            {
                float customer_balance = cus1.getBalance();
                bank = new HDFC(customer_balance);
            }

            else if(obj.selectedBank == 3)
            {
                float customer_balance = cus1.getBalance();
                bank = new SBI(customer_balance);
            }

            else if(obj.selectedBank == 4)
            {
                float customer_balance = cus1.getBalance();
                bank = new AXIS(customer_balance);
            }

            else if(obj.selectedBank == 5)
            {
                float customer_balance = cus1.getBalance();
                bank = new IDFC(customer_balance);
            }

            while (true) {

                System.out.println("Select your choice\n1. Deposit\n2. Withdrawl\n3. OpenFD\n4. Apply Loan\n5. Apply CC");

                try {
                    obj.selectedOperation = Integer.parseInt(obj.buff.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Customer Selected " + obj.selectedOperation);

                if (obj.selectedOperation == 1) {

                    System.out.println("How much money to deposit: ");
                    try {
                        float balanceToDeposit = Float.parseFloat(obj.buff.readLine());
                        cus1.balance += bank.depositMoney(balanceToDeposit);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Now your balance is " + cus1.balance);
                } else if (obj.selectedOperation == 2) {
                    System.out.println("How much money to withdraw: ");
                    try {
                        float balanceToWithdraw = Float.parseFloat(obj.buff.readLine());
                        cus1.balance -= bank.withdrawMoney(balanceToWithdraw, cus1.balance);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Your updated balance is " + cus1.balance);
                } else if (obj.selectedOperation == 3) {

                    try {
                        System.out.println("How much money to fixed deposit: ");
                        float amount = Float.parseFloat(obj.buff.readLine());
                        System.out.println("Enter the Rate of Interest per year");
                        float roi = Float.parseFloat(obj.buff.readLine());
                        System.out.println("Enter the number of years");
                        int years = Integer.parseInt(obj.buff.readLine());
                        bank.openFD(amount, roi, years);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else if (obj.selectedOperation == 4) {
                    System.out.println("What type of loan you need:\n1. Home\n2. Education\n3. Personal\n4. Car");
                    try {
                        int loan_type = Integer.parseInt(obj.buff.readLine());
                        System.out.println("Customer Selected " + loan_type);
                        System.out.println("Enter the loan amount you need: ");
                        float loan_amount = Float.parseFloat(obj.buff.readLine());
                        System.out.println("Enter the number of years you want the loan: ");
                        int year = Integer.parseInt(obj.buff.readLine());
                        bank.applyLoan(loan_type, loan_amount, year);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                System.out.println("Do you want to continue: ");
                System.out.println("Please select the operation:\n1. Yes\n2. No ");

                int selectChoice = 0;
                try {
                    selectChoice = Integer.parseInt(obj.buff.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (selectChoice == 2) {
                    break;
                }

            }
            if (obj.customerAccount.containsKey(cus1.customerAadhar)) {
                obj.customerAccount.get(cus1.customerAadhar).add(obj.selectedBank);
            } else {
                ArrayList<Integer> accountNumbers = new ArrayList<>();
                accountNumbers.add(obj.selectedBank);
                obj.customerAccount.put(cus1.customerAadhar, accountNumbers);
            }

            if (obj.bankNumberOfCustomer.containsKey(obj.selectedBank)) {
                obj.bankNumberOfCustomer.get(obj.selectedBank).add(cus1);
            } else {
                ArrayList<Customer> accountNumbers = new ArrayList<>();
                accountNumbers.add(cus1);
                obj.bankNumberOfCustomer.put(obj.selectedBank, accountNumbers);
            }

            System.out.println("Enter the server response");
            int x=0;
            try {
                x = Integer.parseInt(obj.buff.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(x==1)
                break;
        }

        System.out.println("Please tell the Aadhar number to know the details: ");
        System.out.println("Enter your Aadhar: ");
        try {
            String customerAadhar = obj.buff.readLine();
            System.out.println("Number of accounts of a particular Customer: " + obj.customerAccount.get(customerAadhar).size());
            System.out.println("Number of Customers of a particular bank: " + obj.bankNumberOfCustomer.get(obj.selectedBank).size());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Main obj = new Main();
        Thread t = new Thread(obj);
        t.start();
    }
}