package com.example.digital_gold.controller;
import com.example.digital_gold.service.AssetOverviewBankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

    // retrieves list of all available assets + current prices
    @GetMapping ("/assetoverviewbank")
    public ResponseEntity<?> getAssetOverviewBank() {
        return new ResponseEntity(assetOverviewBankService.getAssetOverviewBank(LocalDate.now()), HttpStatus.OK);
/*        List<Map<String, Object>> assetList = assetOverviewBankService.getAssetOverviewBank(LocalDate.now());
        if (assetList != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Overview of assets is fetched.");
        } else {
            return ResponseEntity.badRequest().body("An overview of assets could not be fetched at the moment.");
        }*/
    }

/*    @GetMapping ("/assetOverviewBank/{today}")
    public ResponseEntity<?> getAssetOverviewBank(@PathVariable String today) {

    }*/

}
