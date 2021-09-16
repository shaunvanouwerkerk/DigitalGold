package com.example.digital_gold.service;
import com.example.digital_gold.domain.Transaction;
import com.example.digital_gold.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

/**
 * @author Sandra Turina
 */
@Service
public class OrderService {
    private RootRepository rootRepository;
    private TransactionService transactionService;
    private static String ibanBank = "NL00DIGO0000000001"; //get from transActionFeeHelper

    @Autowired
    public OrderService(RootRepository rootRepository, TransactionService transactionService) {
        this.rootRepository = rootRepository;
        this.transactionService = transactionService;
    }


    public Transaction processOrder(Order requestOrder){
        LocalDate transactionDate = LocalDate.now();
        String assetCode = requestOrder.getAssetCode();
        double assetAmount = requestOrder.getAmountOfAsset();
        double assetPrice = rootRepository.findAssetPriceByAssetCode(assetCode).getPrice();
        double transactionFee = 0.5; // transactionFeeHelper.get...

        String ibanSell;
        if (requestOrder.getType().equals("sell")) {
            ibanSell = rootRepository.findIbanByUsername(requestOrder.getUsername());
        } else { ibanSell = ibanBank; }

        String ibanBuy;
        if (requestOrder.getType().equals("buy")) {
            ibanBuy = rootRepository.findIbanByUsername(requestOrder.getUsername());
        } else { ibanBuy = ibanBank; }

        Transaction orderToTransaction = new Transaction(transactionDate, assetCode, assetAmount, assetPrice,
                transactionFee, ibanSell, ibanBuy);

        Transaction confirmedTransaction = transactionService.processTransaction(orderToTransaction);
        return confirmedTransaction;
    }

}
