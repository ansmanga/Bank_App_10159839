import org.example.RBI_TEST_METHODS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class RBI_TEST {


    RBI_TEST_METHODS rbiTest;

    public RBI_TEST()
    {
        rbiTest = new RBI_TEST_METHODS();
    }

    @Test
    public void depositMoney(){
        Assertions.assertEquals(3000F, rbiTest.depositMoney(1000F,2000F));
        Assertions.assertEquals(8000F,rbiTest.depositMoney(3000F,5000F));
    }

    @Test
    public void withrawMoney(){
        Assertions.assertEquals(2000F,rbiTest.withdrawMoney(1000F,3000F,1,2,1000));
        Assertions.assertEquals(3970F,rbiTest.withdrawMoney(3000F,7000f,4,3,3000));
    }

    @Test
    public void openFD(){
        Assertions.assertEquals("Total maturity value is 1210.0",rbiTest.openFD(1000,2,0.1f));
        Assertions.assertEquals("Total maturity value is 2205.0",rbiTest.openFD(2000,2,0.05f));

    }

    @Test
    public void applyLoan(){
        Assertions.assertEquals("Total loan value is 1210.0",rbiTest.applyLoan(1000,2,0.1f));
        Assertions.assertEquals("Total loan value is 2205.0",rbiTest.applyLoan(2000,2,0.05f));
    }

    @Test
    public void applyCreditCard(){
        Assertions.assertEquals("You are eligible for credit card",rbiTest.applyCredit(2000,800));
        Assertions.assertEquals("You are not eligible for credit card",rbiTest.applyCredit(1500,1000));
    }

}
