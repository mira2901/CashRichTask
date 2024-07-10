package com.cashrish.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cashrish.Entity.CoinData;
import com.cashrish.Repository.CoinDataRepository;

@Service
public class CoinDataService {
	
	@Autowired
    private CoinDataRepository coinDataRepository;

    public CoinData saveCoinData(CoinData coinData) {
        return coinDataRepository.save(coinData);
    }

}
