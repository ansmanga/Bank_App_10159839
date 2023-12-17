package org.example;

interface RBI
{

    float rbiLoanInterest = 1.0f;
    float rbiCreditCardInterest = 2.0f;

    float depositMoney(float balanceToDeposit);
    float withdrawMoney(float balanceToWithdraw, float currAmount);
    void openFD(float amount, float ROI, int years);
    void applyLoan(int loanType, float amount, int years);
//    void applyCreditCard();
}
