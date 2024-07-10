package com.cashrish.Controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cashrish.Entity.CoinData;
import com.cashrish.Services.CoinDataService;
import com.cashrish.Services.ThirdPartyService;

@RestController
@RequestMapping("/api/coin")
public class CoinDataController {
	Logger log = LoggerFactory.getLogger(CoinDataController.class);
	
	@Autowired
    private CoinDataService coinDataService;
	
	@Autowired
    private ThirdPartyService thirdPartyService;
	
	@GetMapping("/fetch")
    public ResponseEntity<CoinData> fetchCoinData(@RequestParam String symbol, @RequestParam Long userId) {
        
		System.out.println("Fetching coin data for symbol: " + symbol + " and userId: " + userId);
        String apiResponse = thirdPartyService.getCoinData(symbol);

        CoinData coinData = new CoinData();
        coinData.setUserId(userId);
        coinData.setSymbol(symbol);
        coinData.setResponse(apiResponse);
        coinData.setTimestamp(LocalDateTime.now());

        return ResponseEntity.ok(coinDataService.saveCoinData(coinData));
    }
}
