package com.linzhongyoushenjun.Traceability.model.bo;

import java.lang.Object;
import java.lang.String;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TraceabilityFactoryChangeTraceGoodsInputBO {
  private byte[] goodsGroup;

  private BigInteger goodsId;

  private BigInteger goodsStatus;

  private String remark;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(goodsGroup);
    args.add(goodsId);
    args.add(goodsStatus);
    args.add(remark);
    return args;
  }
}
