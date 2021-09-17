package com.example.digital_gold.controller;
import com.example.digital_gold.domain.AssetPriceDto;
import com.example.digital_gold.service.AssetOverviewBankService;
import com.example.digital_gold.service.AuthenticatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 * @author Fiona Gray
* */

@RestController
public class AssetOverviewBankController {

    private AssetOverviewBankService assetOverviewBankService;
    private AuthenticatorService authenticatorService;
    private final Logger logger = LoggerFactory.getLogger(AssetOverviewBankController.class);

    @Autowired
    public AssetOverviewBankController(AssetOverviewBankService assetOverviewBankService, AuthenticatorService authenticatorService) {
        this.assetOverviewBankService = assetOverviewBankService;
        this.authenticatorService = authenticatorService;
        logger.info("New AssetOverviewBankController");
    }

    @GetMapping ("/assetoverviewbank")
    public ResponseEntity<List<AssetPriceDto>> getAssetOverviewBank(@RequestHeader("Authorization") String token) throws IOException, InterruptedException {
        if (!(authenticatorService.authenticateUsername(token)== null)) {
            return new ResponseEntity<>(assetOverviewBankService.getAssetPricesFromDatabase(), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
