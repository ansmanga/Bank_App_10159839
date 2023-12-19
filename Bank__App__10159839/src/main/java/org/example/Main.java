package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Main implements Runnable {

    static {
        LogManager lgmngr = LogManager.getLogManager();
        Logger log = lgmngr.getLogger(Logger.GLOBAL_LOGGER_NAME);
        log.log(Level.INFO, "This is a log message for Config Level");
    }

    private final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

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

            LOGGER.info("Welcome to IBS\nPlease select your bank\n1. ICICI\n2. HDFC\n3. SBI\n4. AXIS\n5. IDFC");

            try {
                obj.selectedBank = Integer.parseInt(obj.buff.readLine());
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Error reading selected bank", e);
                e.printStackTrace();
            }
            LOGGER.info("Customer Selected " + obj.selectedBank);


            Customer cus1 = new Customer();
            LOGGER.info("Enter your Name: ");
            try {
                cus1.customerName = obj.buff.readLine();
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Error reading customer name", e);
                throw new RuntimeException(e);
            }
            LOGGER.info("Enter your Email: ");
            try {
                cus1.customerEmail = obj.buff.readLine();
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Error reading customer email", e);
                throw new RuntimeException(e);
            }
            LOGGER.info("Enter your Address: ");
            try {
                cus1.customerAddress = obj.buff.readLine();
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Error reading customer address", e);
                throw new RuntimeException(e);
            }
            LOGGER.info("Enter your Gender: ");
            try {
                cus1.customerGender = obj.buff.readLine();
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Error reading customer gender", e);
                throw new RuntimeException(e);
            }
            LOGGER.info("Enter your Aadhar: ");
            try {
                cus1.customerAadhar = obj.buff.readLine();
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Error reading customer Aadhar", e);
                throw new RuntimeException(e);
            }
            LOGGER.info("Enter your Phone Number: ");
            try {
                cus1.customerPhone = obj.buff.readLine();
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Error reading customer phone number", e);
                throw new RuntimeException(e);
            }
            LOGGER.info("Enter your Balance: ");
            try {
                cus1.balance = Float.parseFloat(obj.buff.readLine());
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Error reading customer balance", e);
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

                LOGGER.info("Select your choice\n1. Deposit\n2. Withdrawal\n3. OpenFD\n4. Apply Loan\n5. Apply CC");

                try {
                    obj.selectedOperation = Integer.parseInt(obj.buff.readLine());
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, "Error reading selected operation", e);
                    e.printStackTrace();
                }
                LOGGER.info("Customer Selected " + obj.selectedOperation);

                if (obj.selectedOperation == 1) {

                    LOGGER.info("How much money to deposit: ");
                    try {
                        float balanceToDeposit = Float.parseFloat(obj.buff.readLine());
                        cus1.balance += bank.depositMoney(balanceToDeposit);
                    } catch (IOException e) {
                        LOGGER.log(Level.SEVERE, "Error depositing money", e);
                        e.printStackTrace();
                    }

                    LOGGER.info("Now your balance is " + cus1.balance);
                } else if (obj.selectedOperation == 2) {
                    LOGGER.info("How much money to withdraw: ");
                    try {
                        float balanceToWithdraw = Float.parseFloat(obj.buff.readLine());
                        cus1.balance -= bank.withdrawMoney(balanceToWithdraw, cus1.balance);
                    } catch (IOException e) {
                        LOGGER.log(Level.SEVERE, "Error withdrawing money", e);
                        e.printStackTrace();
                    }
                    LOGGER.info("Your updated balance is " + cus1.balance);
                } else if (obj.selectedOperation == 3) {

                    try {
                        LOGGER.info("How much money to fixed deposit: ");
                        float amount = Float.parseFloat(obj.buff.readLine());
                        LOGGER.info("Enter the Rate of Interest per year");
                        float roi = Float.parseFloat(obj.buff.readLine());
                        LOGGER.info("Enter the number of years");
                        int years = Integer.parseInt(obj.buff.readLine());
                        bank.openFD(amount, roi, years);

                    } catch (IOException e) {
                        LOGGER.log(Level.SEVERE, "Error opening FD", e);
                        e.printStackTrace();
                    }

                } else if (obj.selectedOperation == 4) {
                    LOGGER.info("What type of loan you need:\n1. Home\n2. Education\n3. Personal\n4. Car");
                    try {
                        int loan_type = Integer.parseInt(obj.buff.readLine());
                        LOGGER.info("Customer Selected " + loan_type);
                        LOGGER.info("Enter the loan amount you need: ");
                        float loan_amount = Float.parseFloat(obj.buff.readLine());
                        LOGGER.info("Enter the number of years you want the loan: ");
                        int year = Integer.parseInt(obj.buff.readLine());
                        bank.applyLoan(loan_type, loan_amount, year);
                    } catch (IOException e) {
                        LOGGER.log(Level.SEVERE, "Error applying for a loan", e);
                        e.printStackTrace();
                    }

                }

                LOGGER.info("Do you want to continue: ");
                LOGGER.info("Please select the operation:\n1. Yes\n2. No ");

                int selectChoice = 0;
                try {
                    selectChoice = Integer.parseInt(obj.buff.readLine());
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, "Error reading user choice", e);
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

            LOGGER.info("Enter the server response");
            int x=0;
            try {
                x = Integer.parseInt(obj.buff.readLine());
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Error reading server response", e);
                e.printStackTrace();
            }

            if(x==1)
                break;
        }

        LOGGER.info("Please tell the Aadhar number to know the details: ");
        LOGGER.info("Enter your Aadhar: ");
        try {
            String customerAadhar = obj.buff.readLine();
            LOGGER.info("Number of accounts of a particular Customer: " + obj.customerAccount.get(customerAadhar).size());
            LOGGER.info("Number of Customers of a particular bank: " + obj.bankNumberOfCustomer.get(obj.selectedBank).size());

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading Aadhar or retrieving customer details", e);
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Main obj = new Main();
        Thread t = new Thread(obj);
        t.start();
    }
}