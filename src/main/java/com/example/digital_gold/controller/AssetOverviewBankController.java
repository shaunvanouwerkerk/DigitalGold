package com.example.digital_gold.controller;
import com.example.digital_gold.service.AssetOverviewBankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

/**
 * @author Fiona Gray
* */

@RestController
public class AssetOverviewBankController {

    private AssetOverviewBankService assetOverviewBankService;

    private final Logger logger = LoggerFactory.getLogger(AssetOverviewBankController.class);

    @Autowired
    public AssetOverviewBankController(AssetOverviewBankService assetOverviewBankService) {
        this.assetOverviewBankService = assetOverviewBankService;
        logger.info("New AsserOverviewBankController");
    }

    // Todo: deliver message if request failed
    // retrieves list of all available assets + current prices

    @GetMapping ("/assetOverviewBank")
    public ResponseEntity<?> getAssetOverviewBank() {
        return new ResponseEntity(assetOverviewBankService.getAssetOverviewBank(LocalDate.now()), HttpStatus.OK);
    }

    /*@GetMapping ("/assetOverviewBank/{today}")
    public ResponseEntity<?> getAssetOverviewBank(@PathVariable String today) {
        return new ResponseEntity(assetOverviewBankService.getAssetOverviewBank(LocalDate.parse(today)), HttpStatus.OK);
    }
*/




}
