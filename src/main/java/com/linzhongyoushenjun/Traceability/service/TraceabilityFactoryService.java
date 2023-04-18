package com.linzhongyoushenjun.Traceability.service;

import com.linzhongyoushenjun.Traceability.model.bo.TraceabilityFactoryChangeTraceGoodsInputBO;
import com.linzhongyoushenjun.Traceability.model.bo.TraceabilityFactoryCreateTraceGoodsInputBO;
import com.linzhongyoushenjun.Traceability.model.bo.TraceabilityFactoryCreateTraceabilityInputBO;
import com.linzhongyoushenjun.Traceability.model.bo.TraceabilityFactoryGetGoodsGroupInputBO;
import com.linzhongyoushenjun.Traceability.model.bo.TraceabilityFactoryGetStatusInputBO;
import com.linzhongyoushenjun.Traceability.model.bo.TraceabilityFactoryGetTraceInfoInputBO;
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
public class TraceabilityFactoryService {
  public static final String ABI = com.linzhongyoushenjun.Traceability.utils.IOUtil.readResourceAsString("abi/TraceabilityFactory.abi");

  public static final String BINARY = com.linzhongyoushenjun.Traceability.utils.IOUtil.readResourceAsString("bin/ecc/TraceabilityFactory.bin");

//  public static final String SM_BINARY = com.linzhongyoushenjun.Traceability.utils.IOUtil.readResourceAsString("bin/sm/TraceabilityFactory.bin");

  @Value("${system.contract.traceabilityFactoryAddress}")
  private String address;

  @Autowired
  private Client client;

  AssembleTransactionProcessor txProcessor;

  @PostConstruct
  public void init() throws Exception {
    this.txProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, this.client.getCryptoSuite().getCryptoKeyPair());
  }

  public TransactionResponse createTraceGoods(TraceabilityFactoryCreateTraceGoodsInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "createTraceGoods", input.toArgs());
  }

  public CallResponse getGoodsGroup(TraceabilityFactoryGetGoodsGroupInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "getGoodsGroup", input.toArgs());
  }

  public TransactionResponse createTraceability(TraceabilityFactoryCreateTraceabilityInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "createTraceability", input.toArgs());
  }

  public TransactionResponse changeTraceGoods(TraceabilityFactoryChangeTraceGoodsInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "changeTraceGoods", input.toArgs());
  }

  public CallResponse getStatus(TraceabilityFactoryGetStatusInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "getStatus", input.toArgs());
  }

  public CallResponse getTraceInfo(TraceabilityFactoryGetTraceInfoInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "getTraceInfo", input.toArgs());
  }
}
