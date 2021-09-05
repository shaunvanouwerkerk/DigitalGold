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

    //Todo: front-end? automatically get today's date when a customer requests this page
    // retrieves list of all available assets + current prices
    @GetMapping ("/assetOverviewBank/{today}")
    public ResponseEntity<?> getAssetOverviewBank(@PathVariable String today) {
        return new ResponseEntity(assetOverviewBankService.getAssetOverviewBank(LocalDate.parse(today)), HttpStatus.OK);

       /* List<Map<String, Object>> assetOverview = assetOverviewBankService.getAssetOverviewBank(today);

        if (assetOverview != null) {
            return ResponseEntity.created(URI.create("/assetOverviewBank")).build();
        } else {
            return ResponseEntity.badRequest().body("Overview for date not found");
        }*/
    }




}
