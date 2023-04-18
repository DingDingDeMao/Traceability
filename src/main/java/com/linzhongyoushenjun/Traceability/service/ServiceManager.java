package com.linzhongyoushenjun.Traceability.service;

import com.linzhongyoushenjun.Traceability.config.SystemConfig;
import java.lang.Exception;
import java.lang.String;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@Slf4j
public class ServiceManager {
  @Autowired
  private SystemConfig config;

  @Autowired
  private Client client;

  List<String> hexPrivateKeyList;

  @PostConstruct
  public void init() {
    hexPrivateKeyList = Arrays.asList(this.config.getHexPrivateKey().split(","));
  }

  /**
   * @notice: must use @Qualifier("TraceabilityFactoryService") with @Autowired to get this Bean
   */
  @Bean("TraceabilityFactoryService")
  public Map<String, TraceabilityFactoryService> initTraceabilityFactoryServiceManager() throws Exception {
    Map<String, TraceabilityFactoryService> serviceMap = new ConcurrentHashMap<>(this.hexPrivateKeyList.size());
    for (int i = 0; i < this.hexPrivateKeyList.size(); i++) {
    	String privateKey = this.hexPrivateKeyList.get(i);
    	if (privateKey.startsWith("0x") || privateKey.startsWith("0X")) {
    		privateKey = privateKey.substring(2);
    	}
    	if (privateKey.isEmpty()) {
    		continue;
    	}
    	org.fisco.bcos.sdk.crypto.CryptoSuite cryptoSuite = new org.fisco.bcos.sdk.crypto.CryptoSuite(this.client.getCryptoType());
    	org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair cryptoKeyPair = cryptoSuite.createKeyPair(privateKey);
    	String userAddress = cryptoKeyPair.getAddress();
    	log.info("++++++++hexPrivateKeyList[{}]:{},userAddress:{}", i, privateKey, userAddress);
    	TraceabilityFactoryService traceabilityFactoryService = new TraceabilityFactoryService();
    	traceabilityFactoryService.setAddress(this.config.getContract().getTraceabilityFactoryAddress());
    	traceabilityFactoryService.setClient(this.client);
    	org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor txProcessor = 
    		org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, cryptoKeyPair);
    	traceabilityFactoryService.setTxProcessor(txProcessor);
    	serviceMap.put(userAddress, traceabilityFactoryService);
    }
    log.info("++++++++TraceabilityFactoryService map:{}", serviceMap);
    return serviceMap;
  }

  /**
   * @notice: must use @Qualifier("TraceabilityService") with @Autowired to get this Bean
   */
  @Bean("TraceabilityService")
  public Map<String, TraceabilityService> initTraceabilityServiceManager() throws Exception {
    Map<String, TraceabilityService> serviceMap = new ConcurrentHashMap<>(this.hexPrivateKeyList.size());
    for (int i = 0; i < this.hexPrivateKeyList.size(); i++) {
    	String privateKey = this.hexPrivateKeyList.get(i);
    	if (privateKey.startsWith("0x") || privateKey.startsWith("0X")) {
    		privateKey = privateKey.substring(2);
    	}
    	if (privateKey.isEmpty()) {
    		continue;
    	}
    	org.fisco.bcos.sdk.crypto.CryptoSuite cryptoSuite = new org.fisco.bcos.sdk.crypto.CryptoSuite(this.client.getCryptoType());
    	org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair cryptoKeyPair = cryptoSuite.createKeyPair(privateKey);
    	String userAddress = cryptoKeyPair.getAddress();
    	log.info("++++++++hexPrivateKeyList[{}]:{},userAddress:{}", i, privateKey, userAddress);
    	TraceabilityService traceabilityService = new TraceabilityService();
    	traceabilityService.setAddress(this.config.getContract().getTraceabilityAddress());
    	traceabilityService.setClient(this.client);
    	org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor txProcessor = 
    		org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, cryptoKeyPair);
    	traceabilityService.setTxProcessor(txProcessor);
    	serviceMap.put(userAddress, traceabilityService);
    }
    log.info("++++++++TraceabilityService map:{}", serviceMap);
    return serviceMap;
  }

  /**
   * @notice: must use @Qualifier("GoodsService") with @Autowired to get this Bean
   */
  @Bean("GoodsService")
  public Map<String, GoodsService> initGoodsServiceManager() throws Exception {
    Map<String, GoodsService> serviceMap = new ConcurrentHashMap<>(this.hexPrivateKeyList.size());
    for (int i = 0; i < this.hexPrivateKeyList.size(); i++) {
    	String privateKey = this.hexPrivateKeyList.get(i);
    	if (privateKey.startsWith("0x") || privateKey.startsWith("0X")) {
    		privateKey = privateKey.substring(2);
    	}
    	if (privateKey.isEmpty()) {
    		continue;
    	}
    	org.fisco.bcos.sdk.crypto.CryptoSuite cryptoSuite = new org.fisco.bcos.sdk.crypto.CryptoSuite(this.client.getCryptoType());
    	org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair cryptoKeyPair = cryptoSuite.createKeyPair(privateKey);
    	String userAddress = cryptoKeyPair.getAddress();
    	log.info("++++++++hexPrivateKeyList[{}]:{},userAddress:{}", i, privateKey, userAddress);
    	GoodsService goodsService = new GoodsService();
    	goodsService.setAddress(this.config.getContract().getGoodsAddress());
    	goodsService.setClient(this.client);
    	org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor txProcessor = 
    		org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, cryptoKeyPair);
    	goodsService.setTxProcessor(txProcessor);
    	serviceMap.put(userAddress, goodsService);
    }
    log.info("++++++++GoodsService map:{}", serviceMap);
    return serviceMap;
  }
}
