package com.bit.BitcoinAnalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BlockChainAnalysisApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlockChainAnalysisApplication.class, args);
	}

}
