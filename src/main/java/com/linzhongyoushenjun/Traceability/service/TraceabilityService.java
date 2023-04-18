package com.linzhongyoushenjun.Traceability.service;

import com.linzhongyoushenjun.Traceability.model.bo.TraceabilityChangeGoodsStatusInputBO;
import com.linzhongyoushenjun.Traceability.model.bo.TraceabilityCreateGoodsInputBO;
import com.linzhongyoushenjun.Traceability.model.bo.TraceabilityGetGoodsInputBO;
import com.linzhongyoushenjun.Traceability.model.bo.TraceabilityGetStatusInputBO;
import java.lang.Exception;
import java.lang.String;
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
public class TraceabilityService {
  public static final String ABI = com.linzhongyoushenjun.Traceability.utils.IOUtil.readResourceAsString("abi/Traceability.abi");

  public static final String BINARY = com.linzhongyoushenjun.Traceability.utils.IOUtil.readResourceAsString("bin/ecc/Traceability.bin");

//  public static final String SM_BINARY = com.linzhongyoushenjun.Traceability.utils.IOUtil.readResourceAsString("bin/sm/Traceability.bin");

  @Value("${system.contract.traceabilityAddress}")
  private String address;

  @Autowired
  private Client client;

  AssembleTransactionProcessor txProcessor;

  @PostConstruct
  public void init() throws Exception {
    this.txProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, this.client.getCryptoSuite().getCryptoKeyPair());
  }

  public CallResponse getGoods(TraceabilityGetGoodsInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "getGoods", input.toArgs());
  }

  public CallResponse getStatus(TraceabilityGetStatusInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "getStatus", input.toArgs());
  }

  public TransactionResponse createGoods(TraceabilityCreateGoodsInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "createGoods", input.toArgs());
  }

  public TransactionResponse changeGoodsStatus(TraceabilityChangeGoodsStatusInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "changeGoodsStatus", input.toArgs());
  }
}
