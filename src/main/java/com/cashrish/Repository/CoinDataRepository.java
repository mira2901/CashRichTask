package com.cashrish.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cashrish.Entity.CoinData;

public interface CoinDataRepository extends JpaRepository<CoinData, Long> {

}
