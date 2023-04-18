package com.linzhongyoushenjun.Traceability.service;

import com.linzhongyoushenjun.Traceability.model.bo.GoodsChangeStatusInputBO;
import java.lang.Exception;
import java.lang.String;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.transaction.model.dto.CallResponse;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@Data
public class GoodsService {
  public static final String ABI = com.linzhongyoushenjun.Traceability.utils.IOUtil.readResourceAsString("abi/Goods.abi");

  public static final String BINARY = com.linzhongyoushenjun.Traceability.utils.IOUtil.readResourceAsString("bin/ecc/Goods.bin");

//  public static final String SM_BINARY = com.linzhongyoushenjun.Traceability.utils.IOUtil.readResourceAsString("bin/sm/Goods.bin");

  @Value("${system.contract.goodsAddress}")
  private String address;

  @Autowired
  private Client client;

  AssembleTransactionProcessor txProcessor;

  @PostConstruct
  public void init() throws Exception {
    this.txProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, this.client.getCryptoSuite().getCryptoKeyPair());
  }

  public CallResponse getTraceInfo() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "getTraceInfo", Arrays.asList());
  }

  public CallResponse getStatus() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "getStatus", Arrays.asList());
  }

  public TransactionResponse changeStatus(GoodsChangeStatusInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "changeStatus", input.toArgs());
  }
}
