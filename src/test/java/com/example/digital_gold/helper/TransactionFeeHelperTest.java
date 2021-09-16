package com.example.digital_gold.helper;

import com.example.digital_gold.domain.Transaction;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class TransactionFeeHelperTest {


   Transaction testTransaction1 =  new Transaction(1, LocalDate.now(),"BTC",
                0.0345,40123.25,0.01,"IBANseller","IBANbuyer");

    Transaction testTransaction2 =  new Transaction(1, LocalDate.now(),"BTC",
            0.0345,40123.25,0.01,"NL00DIGO0000000001","IBANbuyer");

    Transaction testTransaction3 =  new Transaction(1, LocalDate.now(),"BTC",
            0.0345,40123.25,0.01,"IBANseller","NL00DIGO0000000001");

    Transaction testTransaction4 =  new Transaction(1, LocalDate.now(),"BTC",
            0.0345,40123.25,0.01,"NL00DIGO0000000001","NL00DIGO0000000001");


    @Test
    void splitTransactionFeeTestCustomerVsCustomer() {
        TransactionFeeHelper feeHelper = TransactionFeeHelper.splitTransactionFee(testTransaction1);
        double shareBuyer = TransactionFeeHelper.getShareBuyer();
        double feeBuyer = shareBuyer * testTransaction1.getTransactionFee() * testTransaction1.getAssetPrice()
                * testTransaction1.getAssetAmount();
        double feeSeller = (1 - shareBuyer) * testTransaction1.getTransactionFee() * testTransaction1.getAssetPrice()
                * testTransaction1.getAssetAmount();
        assertEquals(feeBuyer,feeHelper.getFeeBuyer());
        assertEquals(feeSeller,feeHelper.getFeeSeller());
    }

    @Test
    void splitTransactionFeeTestBankSeller() {
        TransactionFeeHelper feeHelper = TransactionFeeHelper.splitTransactionFee(testTransaction2);
        double feeBuyer = testTransaction2.getTransactionFee() * testTransaction2.getAssetPrice()
                * testTransaction2.getAssetAmount();
        assertEquals(feeBuyer,feeHelper.getFeeBuyer());
        assertEquals(0,feeHelper.getFeeSeller());
    }

    @Test
    void splitTransactionFeeTestBankBuyer() {
        TransactionFeeHelper feeHelper = TransactionFeeHelper.splitTransactionFee(testTransaction3);
        double feeBuyer = testTransaction3.getTransactionFee() * testTransaction3.getAssetPrice()
                * testTransaction3.getAssetAmount();
        assertEquals(0,feeHelper.getFeeBuyer());
        assertEquals(feeBuyer,feeHelper.getFeeSeller());
    }

    @Test
    void splitTransactionFeeTestBankBoth() {
        TransactionFeeHelper feeHelper = TransactionFeeHelper.splitTransactionFee(testTransaction4);
        double feeBuyer = testTransaction4.getTransactionFee() * testTransaction3.getAssetPrice()
                * testTransaction4.getAssetAmount();
        assertEquals(feeBuyer,feeHelper.getFeeBuyer());
        assertEquals(0,feeHelper.getFeeSeller());
    }
}